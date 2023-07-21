package application;

import chess.ChessPiece;

public class UI {
	// Classe responsável por apresentar o tabuleiro e as peças
	public static void printBoard(ChessPiece[][] pieces) {
		for(int i=0;i<pieces.length;i++) {
			System.out.print((8 - i) + " ");
			for(int j=0;j<pieces.length;j++) {
				printPiece(pieces[i][j]);//chamando o método e passando um valor como argumento
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}
	
	//aqui será realizado uma verificação individual de uma única peça. Se for null, imprimi (-)
	//se for uma peça, irá imprimir uma peça
	private static void printPiece(ChessPiece piece) {
		if(piece == null) {
			System.out.print("-");
		}else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
