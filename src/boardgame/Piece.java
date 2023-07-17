package boardgame;

public class Piece {
	// classe peça. Voltanda para as especificidades de peça

	protected Position position;//acessível apenas dentro do pacote e subclasses
	private Board board; // é nossa associação com a classe tabuleiro

	public Piece() {
	}

	public Piece(Board board) {
		this.board = board;
		position = null;// posição de uma peça recém criada será NULL
	}

	protected Board getBoard() {
		return board;
	}

}
