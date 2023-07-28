package boardgame;

public abstract class Piece {
	// classe peça. Voltanda para as especificidades de peça

	protected Position position;// acessível apenas dentro do pacote e subclasses
	private Board board; // é nossa associação (composição) com a classe tabuleiro

	public Piece() {
	}

	public Piece(Board board) {
		this.board = board;
		position = null;// posição de uma peça recém criada será NULL
	}

	protected Board getBoard() {
		return board;
	}

	// método abstrato que precisa de implementação nas classes especifica.
	// Nesse caso, cada sub-classe (peças -> torre, rei, rainha) tem sua regra
	// especifica de movimentação. Esse método valida todas as movimentações
	// possíveis
	public abstract boolean[][] possibleMoves();

	// Verifica se uma peça pode se mover para uma determinada posição
	public boolean possibileMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
		// está chamando o método abstrato, que cada classe filha implementa.
		// Afinal, cada peça tem sua regra de movimentação. Esse método apenas está
		// verificando
		// se o movimento solicitado é plausível dentro da regra da peça

	}

	// verifica se há pelo PELO MENOS UMA possição na matriz para realizar a
	// movimentação. Esse movimento é importante para validar se a peça está
	// encurralada e não pode se movimentar mais
	//
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();// novamente chamando o nosso método que será implementado
		// e cada peça
	}

}
