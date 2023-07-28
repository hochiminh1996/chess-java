package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//CLASSE DA TORRE. É UMA SUBCLASSE DE PEÇA DE XADREZ
public class Rook extends ChessPiece {

	// OBRIGATORIAMENTE, POR SER ENVOLVER HERANÇA, É NECESSÁRIO IMPLEMENTAR O
	// CONSTRUTOR
	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "R";
	}

	// Polimorfismo de sobreposição (mesma assinatura, implementação diferente)

	// implementando o método abstrato da classe mãe piece. Embora a classe mãe mais
	// próxima seja chesspiece, chesspiece é filha da classe piece. Portanto, é
	// necessário implementar
	@Override
	public boolean[][] possibleMoves() {
		// Por padrão, todas as posições da matriz [mat] começa automaticamente com
		// false.
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// Uma matriz, do tipo boolean, com as dimensões definidas no tabuleiro :
		// linha/coluna
		return mat;
	}

}
