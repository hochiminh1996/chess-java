package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	// será o coração da partida. Aqui será pensando a regra de negócio da partida

	private int turn;// turno
	private Color currentPlayer;// jogador atual

	private Board board;// composição : uma partida necessita de um tabuleiro
	// Além disso, esse obj será utilizado para inserir as peças no tabuleiro

	private boolean check;// verifica se a peça está em check/por padrao vem false
	private boolean check_mate;

	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	// lista para as peças que ainda estão no tabuleiro. Usamos o tipo mais genérico
	// PPiece

	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);// instanciando nosso tabuleiro. Um tabuleiro 8x8
		// que será utilizado dentro da partida

		turn = 1;// turno inicial = 1
		currentPlayer = Color.RED;// começa com as peças vermelhas

		initialSetup();
		// iniciando o game
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return check_mate;
	}

	public boolean[][] possibleMoves(ChessPosition sourceposition) {
		Position position = sourceposition.toPosition();
		// converte uma posição de xadrez para uma posição de matriz

		validateSourcePosition(position);// valida a posição de origem

		return board.piece(position).possibleMoves();// retorna as posições possíveis (as q estiverem true)
	}

	// vai retornar uma matriz de peças de xadrez correspondente a partida informada
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		// matriz criada com a quantidade de linha e coluna definida no tabuleiro

		for (int i = 0; i < board.getRows(); i++) {// linha
			for (int j = 0; j < board.getColumns(); j++) {// coluna
				mat[i][j] = (ChessPiece) board.piece(i, j);//
				// percorre linhas e colunas e atribui valor(na 1º vez será armazenado null
				// nas posições de linha e coluna). Observe que está sendo realizando um
				// downcasting
				// Afinal, o método board.piece retorna um Piece (peça genérica), sendo
				// necessáriro converter p/ ChessPiece (peça de xadrez).
				// Aqui entra a divisão de camadas da aplicação. Afinal, não queremos que o
				// usuário tenha "acesso" em si da peça, mas sim uma parte especifica do sistema
				// q fará uso

			}

		}
		return mat;// retorna nossa matriz de peças

	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();// convertendo para posições de matriz
		Position target = targetPosition.toPosition();

		validateSourcePosition(source);// validando posição de origem
		validateTargetPosition(source, target);// validando a posição de destino

		Piece capturaPiece = makeMove(source, target);
		// caso o jogador movimente o rei para o raio de alcance das peças inimigas
		if (testCheck(currentPlayer)) {

			undoMove(source, target, capturaPiece);// desfazendo o movimento

			throw new ChessException("Vc nao pode se colocar em check...");
		}

		check = (testCheck(opponent(currentPlayer))) ? true : false;
		// se o oponente está em check

		if (testCheckMate(opponent(currentPlayer))) {
			// testa se o rei do oponente ficou em checkmate
			check_mate = true;
			// coloca true e finaliza
		} else {
			// caso contrário, troca de turno e prossegue o game
			nextTurn();// troca o turno do jogador

		}

		return (ChessPiece) capturaPiece;

	}

	public Piece makeMove(Position source, Position target) {

		ChessPiece p = (ChessPiece)board.removePiece(source); // retirando a peça da posição de origem.
		p.increaseMoveCount();
		
		// contagem de mov

		Piece capturedPiece = board.removePiece(target); // remover a possivel peça na posição de destino.

		board.placePiece(p, target);// colocando a peça no destino.

		if (capturedPiece != null) {
			// se for diferente de null significa q havia uma peça aqui
			piecesOnTheBoard.remove(capturedPiece);
			// remove a peça capturada da lista de peças do tabuleiro

			capturedPieces.add(capturedPiece);
			// adiciona a peça na lista de peças capturadas
		}

		return capturedPiece; // retorna a peça capturada
	}

	// desfazendo um movimento
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target);// retira a peça que foi movimentado para o destino
		
		p.decreaseMoveCount();//decrementa
		
		
		board.placePiece(p, source);// coloca a peça na origem

		if (capturedPiece != null) {// se teve uma peça capturada, colocamos ela na posição dela
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);// tirando ela da lista de peças capturas
			piecesOnTheBoard.add(capturedPiece);// adicionado ela novamente na lista das peças do tab
		}

	}

	// valida se há peça na posição de origem
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe peça na posição de origem.");
		}

		// se a cor da peça atual for diferente da peça que estiver sendo movimentada no
		// tabuleiro. foi realizado um downcast para poder ter acesso aoa método
		// getColor,
		// já que getColor é um método do chesspiece. Se cair aqui, significa que o
		// jogador
		// está tentando movimentar uma peça do adversário
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("Tentativa de mover a peca do adversario...");
		}

		// verificar se NÃO existe movimentos possíveis para a peça solicitada
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Nao existe movimentos possiveis para a peca escolhida");
		}
	}

	// validando a posição de destino.
	public void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibileMove(target)) {
			// verificando se o movimento da peça origem ao destino não for possível
			// significa que não podemos mexer a peça
			throw new ChessException("A peca escolhida nao pode ser movimentada para o destino");
		}
	}

	// função para trocar de turno
	private void nextTurn() {
		turn++;// incrementador
		System.out.println(currentPlayer);
		currentPlayer = (currentPlayer == Color.RED) ? Color.BLUE : Color.RED;
		// ternário que identificará qual será o próximo jogador
	}

	// Verifica a cor do oponente
	private Color opponent(Color color) {
		return (color == Color.RED ? Color.BLUE : Color.RED);
	}

	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		// filtrando uma lista usando expressão lambda.

		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}

		throw new IllegalStateException("Nao existe o rei " + color + " no tabuleiro");

	}

	// varrer todas as peças do oponente e validar se há alguma peça dele ameaçando
	// o rei
	private boolean testCheck(Color color) {
		Position kingPostion = king(color).getChessPosition().toPosition();
		// pega a posição do rei no formato matriz linha/coluna

		List<Piece> opponentPiece = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
		// vai varrear as peças do oponente

		// verificando se há alguma peça que possui posição para dar check no rei
		for (Piece p : opponentPiece) {
			boolean[][] mat = p.possibleMoves();// matriz de movimentos possíveis de cada peça

			// se mat tiver posições que dão na direção do rei, significa que o rei está em
			// check
			if (mat[kingPostion.getRow()][kingPostion.getColumn()]) {
				return true;
			}
		}
		return false;// o rei não está em check

	}

	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}

		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		// pegando todas as peças da cor definida.

		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			// um array com posições de movimentos possíveis

			for (int i = 0; i < board.getRows(); i++) {
				// linhas
				for (int j = 0; j < board.getColumns(); j++) {
					// colunas
					if (mat[i][j]) {
						// verificando se o movimento é possível com base nas posições da matriz
						Position source = ((ChessPiece) p).getChessPosition().toPosition();// posição de origem
						// transforma para uma posição no formato xadrez p/ matriz. Feito um downcast
						// para acessar o método getChessPosition.

						Position target = new Position(i, j);// posição de destino

						Piece capturedPiece = makeMove(source, target);// movimentando da origem p/ destino

						boolean testCheck = testCheck(color);
						// testa se o rei da minha cor ainda está em check

						undoMove(source, target, capturedPiece);
						// desfazendo o movimento. Afinal, queriamos simular se o movimento de outras
						// peças podem desfezer o checkMate feito pelas peças inimigas.

						if (!testCheck) {
							// se não estiveer em check, significa que algum movimento pode tirar o rei do
							// check
							return false;// não está mais em checkmate
						}

					}
				}

			}

		}
		return true;
	}

	// vai receber as coordenadas passadas pelo usuario e a peça
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		// passando uma peça e instanciando um novo objeto chessposition que vai passar
		// coluna e linha
		// que está sendo convertido para posições de matriz no toPosition()

		piecesOnTheBoard.add(piece);// adicionado a peça na lista de peças do tabuleiro
	}

	// MÉTODO RESPONSÁVEL POR INICIALIZAR O JOGO COM AS PEÇAS
	private void initialSetup() {
//		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1))

		// ESSE MÉTODO PEGA NOSSO TABULEIRO E ADICIONA (INSTANCIA) PEÇAS. NO CASO, VAI
		// INSTANCIAR UMA TORRE. NO ARG DA TORRE VAMOS REFERENCIAR O TABULEIRO (BOARD
		// (CONCEITO DE COMPOSIÇÃO)) E SETAR UMA COR. NO SEGUNDO ARGUMENTO, POSITION,
		// VAMOS INSTANCIAR UMA POSIÇÃO NO TABULEIRO QUE A TORRE ESTARÁ.

//		board.placePiece(new King(board, Color.BLACK), new Position(2, 0));
//		board.placePiece(new King(board, Color.WHITE), new Position(3, 0));

		// iniciando as peças usando as posições de coordenada. Diferente da de cima que
		// usa posições de matriz
		placeNewPiece('h', 7, new Rook(board, Color.RED));
		placeNewPiece('d', 1, new Rook(board, Color.RED));
		placeNewPiece('e', 1, new King(board, Color.RED));
		placeNewPiece('b', 8, new Rook(board, Color.BLUE));
		placeNewPiece('a', 8, new King(board, Color.BLUE));

//		8 = quantidade linhas. A=1, B=2, C=3, D=4 (...) letra digitada - a = valor correspondente a coluna correta na matriz

//		System.out.println(new ChessPosition('b', 8)); retorna a posição de matriz 0,1 => 8-8=0, b-a=1
//		System.out.println(new ChessPosition('b', 1));// retorna a posição de matriz 7,1 => 8-1=7, b-a=1
//		System.out.println(new ChessPosition('a', 1));//retorna a pos de matriz: 7,0 => 8-1=7, a-a=0
//		System.out.println(new ChessPosition('d', 4));//retorna a pos de matriz: 4,3 => 8-4=4, d-a = 4-1 = 3
//		System.out.println(new ChessPosition('d', 4));
	}

}
