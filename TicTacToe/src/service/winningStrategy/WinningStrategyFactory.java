package service.winningStrategy;

/**
 * @author mdarmanansari
 */
public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(WinningStrategyName winningStrategyName, int dimension) {
        return switch (winningStrategyName) {
            case ORDER_ONE_WINNING_STRATEGY -> new OrderOneWinningStartegy(dimension);
        };
    }
}
