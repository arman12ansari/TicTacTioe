package controller;

import model.Game;
import model.GameStatus;
import model.Move;
import model.Player;
import service.winningStrategy.WinningStrategyFactory;
import service.winningStrategy.WinningStrategyName;

import java.util.List;

/**
 * @author mdarmanansari
 */
public class GameController {
    public Game createGame(int dimension, List<Player> players, WinningStrategyName name) {
        return Game.builder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategy(WinningStrategyFactory.getWinningStrategy(name, dimension))
                .build();
    }

    public void displayBoard(Game game) {
        game.getCurrentBoard().displayBoard();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public Move executeMove(Game game, Player player){
        return player.makeMove(game.getCurrentBoard());
    }

    public Player checkWinner(Game game, Move lastMovePlayed){
        return game.getWinningStrategy().checkWinner(game.getCurrentBoard(), lastMovePlayed);
    }

    public void replayGame(Game game) {
        game.replayGame(game);
    }
}
