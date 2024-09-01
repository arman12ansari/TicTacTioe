import controller.GameController;
import model.*;
import service.winningStrategy.WinningStrategyName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author mdarmanansari
 */
public class TicTacToe {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        int id = 1;
        List<Player> players = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe Game");
        System.out.println("Please enter the dimension for the board");
        int dimension = sc.nextInt();
        System.out.println("Do you want a bot in the game ? Y or N");
        String botAns = sc.next();

        if (botAns.equalsIgnoreCase("Y")) {
            Player bot = new Bot(id++, '$', BotDifficultyLevel.HARD);
            players.add(bot);
            System.out.println("Bot " + bot.getName() + " added to the game with symbol " + bot.getSymbol());
        }

        while (id < dimension) {
            System.out.println("Please enter the name of the player " + id);
            String playerName = sc.next();
            System.out.println("Please enter the symbol for the player " + id);
            char symbol = sc.next().charAt(0);
            Player newPlayer = new Player(id++, playerName, symbol, PlayerType.HUMAN);
            players.add(newPlayer);
        }

        Collections.shuffle(players);
        Game game = gameController.createGame(dimension, players, WinningStrategyName.ORDER_ONE_WINNING_STRATEGY);
        int playerIndex = -1;

        while (game.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
            System.out.println("Current Board Status");
            gameController.displayBoard(game);
            playerIndex++;
            playerIndex = playerIndex % players.size();
            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));

            game.getMoves().add(movePlayed);

            Board saveState = saveBoardState(game.getCurrentBoard());
            game.getBoardStates().add(saveState);

            Player winner = gameController.checkWinner(game, movePlayed);

            if (winner != null) {
                System.out.println("Winner is " + winner.getName());
                break;
            }

            if (game.getMoves().size() == (game.getCurrentBoard().getDimension() * game.getCurrentBoard().getDimension())) {
                System.out.println("Game is a draw");
                break;
            }
        }

        System.out.println("Final Board Status");
        gameController.displayBoard(game);

        System.out.println("Do you want to replay? Y or N ");
        String replayInput = sc.next();

        if (replayInput.equalsIgnoreCase("Y")) {
            List<Move> replayMoves = game.getMoves();
            List<Board> boardStates = game.getBoardStates();

            int size = replayMoves.size();
            try {
                for (int i = 0; i < size; i++) {
                    Move move = replayMoves.get(i);
                    Board board = boardStates.get(i);

                    System.out.println("Move played by " + move.getPlayer().getName() + " on cell ( " + move.getCell().getRow() + " , " + move.getCell().getCol() + " )");
                    System.out.println("Board Status after the move");

                    for (int j = 0; j < board.getDimension(); j++) {
                        List<Cell> cell = board.getMatrix().get(j);
                        for (Cell cells : cell) {
                            cells.displayCell();
                        }
                        System.out.println();
                    }

                    Thread.sleep(200);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Board saveBoardState(Board board) {
        Board saveState = new Board(board.getDimension());
        saveState.setDimension(board.getDimension());

        for (int i = 0; i < board.getDimension(); i++) {
            for (int j = 0; j < board.getDimension(); j++) {
                saveState.getMatrix().get(i).get(j).setRow(board.getMatrix().get(i).get(j).getRow());
                saveState.getMatrix().get(i).get(j).setCol(board.getMatrix().get(i).get(j).getCol());
                saveState.getMatrix().get(i).get(j).setCellState(board.getMatrix().get(i).get(j).getCellState());
                saveState.getMatrix().get(i).get(j).setPlayer(board.getMatrix().get(i).get(j).getPlayer());
            }
        }

        return saveState;
    }
}