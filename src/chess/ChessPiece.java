package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	// observe que piece é uma classe genérica. O que vamos utilizar de fato é a
	// ChessPiece
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);// passando um arg para o construtor da classe mãe.
		this.color = color;
	}

//contagem do movimento
	public Color getColor() {
		return color;
	}

	public int getMoveCount() {
		return moveCount;
	}

//	
	public void increaseMoveCount() {
		moveCount++;
	}

	public void decreaseMoveCount() {
		moveCount--;
	}

	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	// verifica se há uma peça oponente na posição que a peça será deslocada
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);

		return p != null && p.getColor() != color;
		// verifica se p é diferente de null e a cor da peça é diferente da cor da peça
		// movimentada
	}

}
