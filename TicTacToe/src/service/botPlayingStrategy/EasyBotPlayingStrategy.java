package service.botPlayingStrategy;

import exception.GameOverException;
import model.*;

import java.util.List;

/**
 * @author mdarmanansari
 */
public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player bot) {
        List<List<Cell>> matrix = board.getMatrix();

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    board.getMatrix().get(i).get(j).setCellState(CellState.FILLED);
                    board.getMatrix().get(i).get(j).setPlayer(bot);

                    return new Move(board.getMatrix().get(i).get(j), bot);
                }
            }
        }

        throw new GameOverException("There is no empty cell in the board");
    }
}
