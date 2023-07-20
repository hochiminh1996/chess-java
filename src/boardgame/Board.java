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

	// retorna uma matriz pieces com o número de linhas e colunas definidos
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}

	// sobrecarga do método (polimorfismo de sobrecarga - estático)
	// retornando a posição da peça
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
}
