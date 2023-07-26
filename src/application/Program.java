package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			UI.clearScreen();
			
			
			UI.printBoard(chessMatch.getPieces());
			System.out.print("Origem [a1-h8]: "); 
			ChessPosition source = UI.readChessPosition(sc);
			
			System.out.println();
			System.out.print("Destino [a1-h8]: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPiece capturePiece = chessMatch.performChessMove(source, target);

		}

	}

}
