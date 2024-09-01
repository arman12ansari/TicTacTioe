package service.botPlayingStrategy;

import model.Board;
import model.Move;
import model.Player;

/**
 * @author mdarmanansari
 */
public interface BotPlayingStrategy {
    Move makeMove(Board board, Player player);
}
