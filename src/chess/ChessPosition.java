package chess;

import boardgame.Position;

//classe voltada para o sistema de coordenada baseada em coluna (letra) e linha(número)
public class ChessPosition {
	private char column;
	private int row;

	public ChessPosition(char column, int row) {
		// programação defensiva

		if (column < 'a' || column > 'h' || row < 1 || row > 8) {// os operadores relacionais aceitam comparações com
																	// letras
			throw new ChessException("Error ao instanciar uma posição de xadrez. Valores válidos são:" + "A1 e H8.");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	protected Position toPosition() {
		// vai pegar uma coordenada que foi inserida com letra (coluna) e linha (número) e
		// converter para uma posição que tenha apenas números na linha e coluna (posição de matriz)
		return new Position(8 - row, column - 'a');
		
			// Se o usuário digitar b8
			// 8 - 8 = 0, b - a = 1
			// posição na matriz do b8 : linha 0, coluna 1 [0,1]
		
			//Se o usuario digitar d4
			//4 - 4 = 4, d - a = 4 - 1 => 3
			//posição na matriz d4 : linha 4, coluna 3 [4,3]

		/*
		 * Position recebe dois argumentos : linha e coluna. No entanto, precisamos
		 * converter para o formato de matriz. Logo, quando o usuário Digitar linha 8 e
		 * coluna B, a função tem que passar os parâmetros de posições corretas. Nesse
		 * caso será: 8 - 8(qtd de linhas da matrzi) = 0 (linha 0). E coluna B. B - A =
		 * 1. (A =1, B=2, C=3... no unicode)
		 * 
		 * Logo, as coordenadas : b8 retornará as posições de matriz : linha 0 e coluna 1.
		 * Lembre-se: as coordenadas começam do zero
		 * 
		 * 
		 * Ex:
		 * 2. COLUNA A, LINHA 1 => 8-1 = 7(LINHA), A-A  = 0 (COLUNA)
		 */

	}
	
	
	//agora vai pegar uma posição de matriz e converte-lá para uma posição de xadrez (com letras e colunas)
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a'-position.getColumn()), 8 - position.getRow());
		
		//ele vai chamar o construtor e passar os parâmetros já convertidos para valores de matriz.
		//O (char) é para obrigar a conversão para char
		 
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub

		
		return toPosition().toString();
	}
	
	
	
}
