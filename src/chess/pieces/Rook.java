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

}
