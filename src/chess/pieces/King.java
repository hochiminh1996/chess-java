package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//CLASSE REI. É UMA SUBCLASSE DE PEÇA DE XADREZ
public class King extends ChessPiece {

	// OBRIGATORIAMENTE, POR SER ENVOLVER HERANÇA, É NECESSÁRIO IMPLEMENTAR O
	// CONSTRUTOR DA CLASSE MÃE
	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}

	// Polimorfismo de sobreposição (mesma assinatura, implementação diferente)

	// implementando o método abstrato da classe mãe piece. Embora a classe mãe mais
	// próxima seja chesspiece, chesspiece é filha da classe piece. Portanto, é
	// necessário
	// implementar
	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// Cria uma matriz, do tipo boolean, com as dimensões definidas no tabuleiro :
		// linha/coluna. Por padrão, todas as posições da matriz começam com false.
		return mat;
	}

}
