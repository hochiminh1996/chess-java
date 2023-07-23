package chess;

import boardgame.Board;
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

	// MÉTODO RESPONSÁVEL POR INICIALIZAR O JOGO COM AS PEÇAS
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		// ESSE MÉTODO PEGA NOSSO TABULEIRO E ADICIONA (INSTANCIA) PEÇAS. NO CASO, VAI
		// INSTANCIAR UMA TORRE. NO ARG DA TORRE VAMOS REFERENCIAR O TABULEIRO (BOARD
		// (CONCEITO DE COMPOSIÇÃO)) E SETAR UMA COR. NO SEGUNDO ARGUMENTO, POSITION,
		// VAMOS INSTANCIAR UMA POSIÇÃO NO TABULEIRO QUE A TORRE ESTARÁ.

		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));

	}

}
