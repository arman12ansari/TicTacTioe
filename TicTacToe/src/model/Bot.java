package model;

import service.botPlayingStrategy.BotPlayingStrategyFactory;

/**
 * @author mdarmanansari
 */
public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(int id, char Symbol, BotDifficultyLevel botDifficultyLevel) {
        super(id, "CHITTI", Symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {
        System.out.println("Bot " + this.getName() + " turn");
        return BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel).makeMove(board, this);
    }
}
