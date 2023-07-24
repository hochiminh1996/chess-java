package chess;

//Classe voltada para exceções personalizadas do xadrez
public class ChessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChessException(String msg) {
		super(msg);
	}

}
