package service.botPlayingStrategy;

import model.BotDifficultyLevel;

import static model.BotDifficultyLevel.*;

/**
 * @author mdarmanansari
 */
public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        return switch (botDifficultyLevel) {
            case EASY, MEDIUM -> new EasyBotPlayingStrategy();
            case HARD -> new RandomBotPlayingStrategy();
        };
    }
}
