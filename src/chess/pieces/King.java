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

}
