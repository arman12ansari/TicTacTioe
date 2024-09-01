package service.winningStrategy;

/**
 * @author mdarmanansari
 */
public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(WinningStrategyName winningStrategyName, int dimension) {
        return new OrderOneWinningStartegy(dimension);
    }
}
