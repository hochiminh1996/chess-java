package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//CLASSE REI. É UMA SUBCLASSE DE PEÇA DE XADREZ
public class King extends ChessPiece {

	// OBRIGATORIAMENTE, POR SER ENVOLVER HERANÇA, É NECESSÁRIO IMPLEMENTAR O
	// CONSTRUTOR DA CLASSE MÃE
	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}

	// vai validar se o rei pode movimentar para uma posição
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		
		// pode se mover para tal posição se a posição for null ou for uma
		// peça inimiga
		return p == null || p.getColor() != getColor();
	}

	// Polimorfismo de sobreposição (mesma assinatura, implementação diferente)

	// implementando o método abstrato da classe mãe piece. Embora a classe mãe mais
	// próxima seja chesspiece, chesspiece é filha da classe piece. Portanto, é
	// necessário
	// implementar
	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// Cria uma matriz, do tipo boolean, com as dimensões definidas no tabuleiro :
		// linha/coluna. Por padrão, todas as posições da matriz começam com false.

		Position p = new Position(0, 0);

		// testando a posição acima
		p.setValues(position.getRow() - 1, position.getColumn());

		if (getBoard().positionExists(p) && canMove(p)) {
			// se a peça do tabuleiro existir e for possível mover, move.

			mat[p.getRow()][p.getColumn()] = true;
			// seta true na posição que é possível mover
		}

		// verificando posição abaixo
		p.setValues(position.getRow() + 1, position.getColumn());

		if (getBoard().positionExists(p) && canMove(p)) {
			// se a peça do tabuleiro existir e for possível mover, move.

			mat[p.getRow()][p.getColumn()] = true;
			// seta true na posição que é possível mover
		}

		// verificando a posição à esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			// se a peça do tabuleiro existir e for possível mover, move.

			mat[p.getRow()][p.getColumn()] = true;
			// seta true na posição que é possível mover
		}

		// verificando a posição à direita
		p.setValues(position.getRow(), position.getColumn() + 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			// se a peça do tabuleiro existir e for possível mover, move.

			mat[p.getRow()][p.getColumn()] = true;
			// seta true na posição que é possível mover
		}

		// verificando a posição noroeste do rei
		p.setValues(position.getRow() - 1, position.getColumn() - 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			// se a peça do tabuleiro existir e for possível mover, move.

			mat[p.getRow()][p.getColumn()] = true;
			// seta true na posição que é possível mover
		}

		// verificando a posição nordeste do rei
		p.setValues(position.getRow() - 1, position.getColumn() + 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			// se a peça do tabuleiro existir e for possível mover, move.

			mat[p.getRow()][p.getColumn()] = true;
			// seta true na posição que é possível mover
		}

		// verificando a posição sudoeste do rei
		p.setValues(position.getRow() + 1, position.getColumn() - 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			// se a peça do tabuleiro existir e for possível mover, move.

			mat[p.getRow()][p.getColumn()] = true;
			// seta true na posição que é possível mover
		}

		// verificando a posição sudeste do rei
		p.setValues(position.getRow() + 1, position.getColumn() + 1);

		if (getBoard().positionExists(p) && canMove(p)) {
			// se a peça do tabuleiro existir e for possível mover, move.

			mat[p.getRow()][p.getColumn()] = true;
			// seta true na posição que é possível mover
		}

		return mat;
	}

}
