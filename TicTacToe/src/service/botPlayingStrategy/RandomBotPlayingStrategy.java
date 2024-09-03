package service.botPlayingStrategy;

import exception.GameOverException;
import model.*;

import java.util.List;
import java.util.Random;

/**
 * @author mdarmanansari
 */
public class RandomBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player bot) {
        List<List<Cell>> matrix = board.getMatrix();
        Random random = new Random();

        List<Cell> emptyCells = board.getEmptyCells();

        if (emptyCells.isEmpty()) {
            throw new GameOverException("There is no empty cell in the board");
        }

        Cell randomCell = emptyCells.get(random.nextInt(emptyCells.size()));
        randomCell.setCellState(CellState.FILLED);
        randomCell.setPlayer(bot);

        return new Move(randomCell, bot);
    }
}
