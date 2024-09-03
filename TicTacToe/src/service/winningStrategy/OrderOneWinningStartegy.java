package service.winningStrategy;

import model.Board;
import model.Cell;
import model.Move;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author mdarmanansari
 */
public class OrderOneWinningStartegy implements WinningStrategy {
    private int dimension;
    private List<HashMap<Character, Integer>> rowHashMapList;
    private List<HashMap<Character, Integer>> colHashMapList;
    private HashMap<Character, Integer> leftDiagonal;
    private HashMap<Character, Integer> rightDiagonal;
    private HashMap<Character, Integer> cornerHashMap;

    public OrderOneWinningStartegy(int dimension) {
        this.dimension = dimension;
        rowHashMapList = new ArrayList<>();
        colHashMapList = new ArrayList<>();
        leftDiagonal = new HashMap<>();
        rightDiagonal = new HashMap<>();
        cornerHashMap = new HashMap<>();
        for (int i = 0; i < dimension; i++) {
            rowHashMapList.add(new HashMap<>());
            colHashMapList.add(new HashMap<>());
        }
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player player = lastMove.getPlayer();
        char symbol = player.getSymbol();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        boolean winnerResult = (checkCorner(row, col) && winnerCheckForCorners(board.getMatrix(), symbol))
                || checkAndUpdateForRowHashMap(row, symbol)
                || checkAndUpdateForColHashMap(col, symbol)
                || (checkLeftDiagonal(row, col) && checkAndUpdateForLeftDiagonal(symbol))
                || (checkRightDiagonal(row, col) && checkAndUpdateForRightDiagonal(symbol));

        if (winnerResult) {
            return player;
        } else {
            return null;
        }
    }

    private boolean checkCorner(int row, int col) {
        return (row == 0 && col == 0)
                || (row == 0 && col == dimension - 1)
                || (row == dimension - 1 && col == 0)
                || (row == dimension - 1 && col == dimension - 1);
    }

    private boolean winnerCheckForCorners(List<List<Cell>> matrix, char symbol) {
        if (cornerHashMap.containsKey(symbol)) {
            cornerHashMap.put(symbol, cornerHashMap.get(symbol) + 1);
            return cornerHashMap.get(symbol) == 4;
        } else {
            cornerHashMap.put(symbol, 1);
        }
        return false;
    }

    private boolean checkAndUpdateForRowHashMap(int row, char symbol) {
        return checkRowCol(row, symbol, rowHashMapList);
    }

    private boolean checkAndUpdateForColHashMap(int col, char symbol) {
        return checkRowCol(col, symbol, colHashMapList);
    }

    private boolean checkRowCol(int rowCol, char symbol, List<HashMap<Character, Integer>> rowColHashMapList) {
        HashMap<Character, Integer> rowColHashMap = rowColHashMapList.get(rowCol);

        if (rowColHashMap.containsKey(symbol)) {
            rowColHashMap.put(symbol, rowColHashMap.get(symbol) + 1);
            return rowColHashMap.get(symbol) == dimension;
        } else {
            rowColHashMap.put(symbol, 1);
        }
        return false;
    }

    private boolean checkLeftDiagonal(int row, int col) {
        return row == col;
    }

    private boolean checkAndUpdateForLeftDiagonal(char symbol) {
        if (leftDiagonal.containsKey(symbol)) {
            leftDiagonal.put(symbol, leftDiagonal.get(symbol) + 1);
            return leftDiagonal.get(symbol) == dimension;
        } else {
            leftDiagonal.put(symbol, 1);
        }
        return false;
    }

    private boolean checkRightDiagonal(int row, int col) {
        return row + col == dimension - 1;
    }

    private boolean checkAndUpdateForRightDiagonal(char symbol) {
        if (rightDiagonal.containsKey(symbol)) {
            rightDiagonal.put(symbol, rightDiagonal.get(symbol) + 1);
            return rightDiagonal.get(symbol) == dimension;
        } else {
            rightDiagonal.put(symbol, 1);
        }
        return false;
    }
}
