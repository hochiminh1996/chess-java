package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();

		while (true) {

			try {
				UI.clearScreen();

				UI.printMatch(chessMatch, captured);

				System.out.print("Origem [a1-h8]: ");
				ChessPosition source = UI.readChessPosition(sc);

				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				// matriz de booleano com os movimentos possíveis.
				UI.clearScreen();

				UI.printBoard(chessMatch.getPieces(), possibleMoves);

				System.out.println();
				System.out.print("Destino [a1-h8]: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturePiece = chessMatch.performChessMove(source, target);

				// se for diferente de null, significa que uma peça foi capturada e ela é
				// adicionada na lista
				if (captured != null) {
					captured.add(capturePiece);
				}
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}

		}

	}

}
