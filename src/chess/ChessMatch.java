package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	// será o coração da partida. Aqui será pensando a regra de negócio da partida

	private Board board;// composição : uma partida necessita de um tabuleiro
	// Além disso, esse obj será utilizado para inserir as peças no tabuleiro

	public ChessMatch() {
		board = new Board(8, 8);// instanciando nosso tabuleiro. Um tabuleiro 8x8
		// que será utilizado dentro da partida
		initialSetup();
		// iniciando o game
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
		return (ChessPiece) capturaPiece;

	}

	public Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); // retirando a peça da posição de origem.

		Piece capturedPiece = board.removePiece(target); // remover a possivel peça na posição de destino.

		board.placePiece(p, target);// colocando a peça no destino.

		return capturedPiece; // retorna a peça capturada
	}

	// valida se há peça na posição de origem
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe peça na posição de origem.");
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

	// vai receber as coordenadas passadas pelo usuario e a peça
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		// passando uma peça e instanciando um novo objeto chessposition que vai passar
		// coluna e linha
		// que está sendo convertido para posições de matriz no toPosition()
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
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));

//		8 = quantidade linhas. A=1, B=2, C=3, D=4 (...) letra digitada - a = valor correspondente a coluna correta na matriz

//		System.out.println(new ChessPosition('b', 8)); retorna a posição de matriz 0,1 => 8-8=0, b-a=1
//		System.out.println(new ChessPosition('b', 1));// retorna a posição de matriz 7,1 => 8-1=7, b-a=1
//		System.out.println(new ChessPosition('a', 1));//retorna a pos de matriz: 7,0 => 8-1=7, a-a=0
//		System.out.println(new ChessPosition('d', 4));//retorna a pos de matriz: 4,3 => 8-4=4, d-a = 4-1 = 3
//		System.out.println(new ChessPosition('d', 4));
	}

}
