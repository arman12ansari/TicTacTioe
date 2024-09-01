package model;

import service.botPlayingStrategy.BotPlayingStrategy;
import service.botPlayingStrategy.BotPlayingStrategyFactory;

/**
 * @author mdarmanansari
 */
public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(int id, char Symbol, BotDifficultyLevel botDifficultyLevel) {
        super(id, "CHITTI", Symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {
        return BotPlayingStrategyFactory.getBotPlayingStrategy().makeMove(board, this);
    }
}
