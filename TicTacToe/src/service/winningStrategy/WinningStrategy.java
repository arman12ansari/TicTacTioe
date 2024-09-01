package service.winningStrategy;

import model.Board;
import model.Move;
import model.Player;

/**
 * @author mdarmanansari
 */
public interface WinningStrategy {
    Player checkWinner(Board board, Move lastMove);
}
