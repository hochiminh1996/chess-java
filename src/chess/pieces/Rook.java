package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//CLASSE DA TORRE. É UMA SUBCLASSE DE PEÇA DE XADREZ
public class Rook extends ChessPiece {

	// OBRIGATORIAMENTE, POR SER ENVOLVER HERANÇA, É NECESSÁRIO IMPLEMENTAR O
	// CONSTRUTOR
	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "R";
	}

	// Polimorfismo de sobreposição (mesma assinatura, implementação diferente)

	// implementando o método abstrato da classe mãe piece. Embora a classe mãe mais
	// próxima seja chesspiece, chesspiece é filha da classe piece. Portanto, é
	// necessário implementar
	@Override
	public boolean[][] possibleMoves() {
		// Por padrão, todas as posições da matriz [mat] começa automaticamente com
		// false.
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// Uma matriz, do tipo boolean, com as dimensões definidas no tabuleiro :
		// linha/coluna

		
		// ------------------------------------------------------------------------------
		// posiçaõ auxiliar
		Position p = new Position(0, 0);

		// verificação se a posição acima(vertical) está disponíveis
		p.setValues(position.getRow() - 1, position.getColumn());
		// position é o atributo(protected) posição da nossa peça.
		// Estamos passando uma posição acima da peça atual (-1). Essa variável p
		// será usada como referência para verificar todas as posições superiores

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// enquanto a posição p existir e não tiver uma peça na posição informada
			mat[p.getRow()][p.getColumn()] = true;
			// a posição receberá o valor true. Ou seja, true sigifica que vc pode se mover
			// para lá.

			p.setRow(p.getRow() - 1);// aqui ele vai decrementando a coluna para n ficar na mesma e
			// validar todas as posições possiveis

		}

		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// verifica se a posição desejada e se há uma peça por lá.

			mat[p.getRow()][p.getColumn()] = true;
			// definite como true
		}

		// ------------------------------------------------------------------------------
		// [VAI VERIFICAR SE AS POSIÇÕES DO LADO ESQUERDO ESTÃO DISPONÍVEIS /
		// HORIZONTAL]
		p.setValues(position.getRow(), position.getColumn() - 1);
		// Vai analisar as colunas, mas na mesma linha

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

			p.setColumn(p.getColumn() - 1);
			// vai decrementar a coluna para validar posições de coluna diferentes

		}

		// verificando se tem uma peça adversária
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// verifica se a posição desejada e se há uma peça por lá.

			mat[p.getRow()][p.getColumn()] = true;
			// definite como true
		}

		// ------------------------------------------------------------------------------
		// [VAI VERIFICAR SE AS POSIÇÕES DO LADO DIREITA ESTÃO DISPONÍVEIS /
		// HORIZONTAL]
		p.setValues(position.getRow(), position.getColumn() + 1);// Avançando à direita
		// Vai analisar as colunas, mas na mesma linha

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

			p.setColumn(p.getColumn() + 1);
			// vai decrementar a coluna para validar posições de coluna diferentes

		}

		// verificando se tem uma peça adversária
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// verifica se a posição desejada e se há uma peça por lá.

			mat[p.getRow()][p.getColumn()] = true;
			// definite como true
		}

		// ------------------------------------------------------------------------------
		// [VAI VERIFICAR SE AS POSIÇÕES ABAIXO ESTÃO DISPONÍVEIS /

		p.setValues(position.getRow() + 1, position.getColumn());
		// +1 porque o valor da linha começa 0, a próxima posição é linha_atual+1

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// enquanto a posição p existir e não tiver uma peça na posição informada
			mat[p.getRow()][p.getColumn()] = true;
			// a posição receberá o valor true. Ou seja, true sigifica que vc pode se mover
			// para lá.

			p.setRow(p.getRow() + 1);// aqui ele vai decrementando a coluna para n ficar na mesma.

		}

		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// verifica se a posição desejada e se há uma peça por lá.

			mat[p.getRow()][p.getColumn()] = true;
			// definite como true
		}
		
		return mat;
	}

}
