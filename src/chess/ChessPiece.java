package chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece {
	// observe que piece é uma classe genérica. O que vamos utilizar de fato é a
	// ChessPiece
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);// passando um arg para o construtor da classe mãe.
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}
