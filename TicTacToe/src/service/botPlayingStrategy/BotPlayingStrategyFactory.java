package service.botPlayingStrategy;

/**
 * @author mdarmanansari
 */
public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy() {
        return new RandomBotPlayingStrategy();
    }
}
