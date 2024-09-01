package model;

import java.util.Scanner;

/**
 * @author mdarmanansari
 */
public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;
    private int row;
    private int col;

    public Player(int id, String name, char symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move makeMove(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Player " + this.getName() + " turn");
        userInput(board);

        Cell playedCell = board.getMatrix().get(row).get(col);
        playedCell.setCellState(CellState.FILLED);
        playedCell.setPlayer(this);

        return new Move(playedCell, this);

    }

    public void userInput(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row for the target cell:");
        row = sc.nextInt();
        System.out.println("Enter the column for the target cell:");
        col = sc.nextInt();

        validateMove(board, row, col);
    }

    private void validateMove(Board board, int row, int col) {
        if (row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()) {
            System.out.println("Invalid move. Please try again.");
            userInput(board);
            return;
        }

        Cell playedCell = board.getMatrix().get(row).get(col);
        if (playedCell.getCellState() == CellState.FILLED) {
            System.out.println("Cell already filled. Please try again.");
            userInput(board);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
