package boardgame;

public class Board {
	// classe board : tabuleiro de xadrez
	private int rows;
	private int columns;
	private Piece[][] pieces;// uma matriz de peças :)

	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];// instanciando uma peça nas posições informadas
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	// retorna os valores das posições definidas a partir de linha e coluna
	public Piece piece(int row, int column) {
		return pieces[row][column];//na primeira vez vai retornar null. Já que não há nada 
	}

	// sobrecarga do método (polimorfismo de sobrecarga - estático)
	// Retorna o valor de uma posição passada (a8, a7 e etc)
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
}
