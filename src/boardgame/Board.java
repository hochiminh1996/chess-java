package boardgame;

public class Board {
	// classe board : tabuleiro de xadrez
	private int rows;
	private int columns;
	private Piece[][] pieces;// uma matriz de peças :)

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro ao criar o tabuleiro. É necessário ter ao menos uma linha e coluna.");
		}

		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];// instanciando uma peça nas posições informadas
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	// retorna os valores das posições definidas a partir de linha e coluna
	public Piece piece(int row, int column) {

		if (!positionExists(row, column)) {
			throw new BoardException("Posição [" + row + "][" + column + "] não existe!");
		}

		return pieces[row][column];// na primeira vez vai retornar null. Já que não há nada
	}

	// sobrecarga do método (polimorfismo de sobrecarga - estático)
	// Retorna o valor de uma posição passada.
	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição [" + position.getRow() + "][" + position.getColumn() + "] não existe!");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	// Aqui iremos receber uma peça e a colocaremos na posição informada
	public void placePiece(Piece piece, Position position) {

		if (thereIsAPiece(position)) {
			throw new BoardException("Há uma peça nessa posição: ");
		}

		pieces[position.getRow()][position.getColumn()] = piece;
		// acessa a matriz pieces e na linha/coluna informada será colocada uma peça.
		// Essa peça, por ter um toString, vai retornar a letra da mesma. Por exemplo,
		// torre retorna R e Rei retorna K
		piece.position = position;
	}

	// irá validar se uma posição existe conforme as dimensões do tabuleiro
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
		// chama nosso método privado e passa as posições para conferir
	}

	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição [" + position.getRow() + "][" + position.getColumn() + "] não existe!");
		}

		return piece(position) != null;
		// se for diferente de null, significa que há uma peça nessa posição. E ele
		// retorna a peça

	}

}
