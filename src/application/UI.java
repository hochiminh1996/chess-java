package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	// Classe responsável por apresentar o tabuleiro e as peças

	// códigos especiais das cores a serem imprimidas no console (texto).
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// cores do plano de fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {// função para limpar a tela
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// lê uma posição
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);// primeira letra da coluna

			int row = Integer.parseInt(s.substring(1));// recorda a String a partir do 1 e converte para int

			return new ChessPosition(column, row);// retorna a coluna e linha
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro lendo as posições de xadrez. Valores válidos de a1-h8");
		}
	}

	// vai imprimir a partida
	public static void printMatch(ChessMatch chessMatch) {
		printBoard(chessMatch.getPieces());// imprimi as peças do tabuleiro

		System.out.println();

		System.out.println("Turno: " + chessMatch.getTurn());//mostra o turno
		System.out.println("Aguardando o jogador: " + chessMatch.getCurrentPlayer());//mostra qual jogador de deve jogar
		

	}

	public static void printBoard(ChessPiece[][] pieces) {
		System.out.println(ANSI_BLUE_BACKGROUND + ANSI_WHITE + "   DEEP BLUE     " + ANSI_RESET + ANSI_RESET);
		System.out.println("-----------------");
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
				// vai imprimir as peças. O segundo parâmetro, false, é para não colorir o fundo
				// o fundo colorido é usado apenas para movimentos possíveis de uma peça
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");// colunas
		System.out.println("-----------------");
		System.out.println(ANSI_RED_BACKGROUND + ANSI_WHITE + "    KASPAROV     " + ANSI_RESET + ANSI_RESET);

	}

	// movimentos possíveis com o fundo colorido
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		System.out.println(ANSI_BLUE_BACKGROUND + ANSI_WHITE + "   DEEP BLUE     " + ANSI_RESET + ANSI_RESET);
		System.out.println("-----------------");
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
				// vai imprimir as peças. O segundo parâmetro é para colorir o fundo
				// dos movimentos possíveis
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");// colunas
		System.out.println("-----------------");
		System.out.println(ANSI_RED_BACKGROUND + ANSI_WHITE + "    KASPAROV     " + ANSI_RESET + ANSI_RESET);

	}

	// aqui será realizado uma verificação individual de uma única peça. Se for
	// null, imprimi (-)
	// se for uma peça, irá imprimir uma peça
	private static void printPiece(ChessPiece piece, boolean background) {
		// background serve para definir se devemos colorir ou não o fundo de uma peça.
		// Será usado para movimentos possíveis
		if (background == true) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.RED) {
				System.out.print(ANSI_RED + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_BLUE + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

}
