package boardgame;

//boardgame = tabuleiro
public class Position {
	// Classe voltada para as posições informadas pelo jogador (linha, coluna)

	private int row;
	private int column;

	public Position() {
	}

	public Position(int row, int column) {
		this.column = column;
		this.row = row;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {

		return row + ", " + column;
	}

}
