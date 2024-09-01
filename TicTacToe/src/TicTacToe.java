import controller.GameController;
import model.*;
import service.winningStrategy.WinningStrategyName;

import java.util.*;

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

        if(botAns.equalsIgnoreCase("Y")){
            Player bot = new Bot(id++,'$', BotDifficultyLevel.HARD);
            players.add(bot);
            System.out.println("Bot " + bot.getName() + " added to the game with symbol " + bot.getSymbol());
        }

        while(id < dimension){
            System.out.println("Please enter the name of the player "+id);
            String playerName = sc.next();
            System.out.println("Please enter the symbol for the player "+id);
            char symbol = sc.next().charAt(0);
            Player newPlayer = new Player(id++, playerName, symbol, PlayerType.HUMAN);
            players.add(newPlayer);
        }

        Collections.shuffle(players);
        Game game = gameController.createGame(dimension, players, WinningStrategyName.ORDER_ONE_WINNING_STRATEGY);
        int playerIndex = -1;

        while(game.getGameStatus().equals(GameStatus.IN_PROGRESS)){
            System.out.println("Current Board Status");
            gameController.displayBoard(game);
            playerIndex++;
            playerIndex = playerIndex % players.size();
            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
            game.getMoves().add(movePlayed);
            game.getBoardStates().add(game.getCurrentBoard());
            Player winner = gameController.checkWinner(game, movePlayed);

            if(winner != null){
                System.out.println("Winner is "+winner.getName());
                break;
            }

            if(game.getMoves().size() == (game.getCurrentBoard().getDimension() * game.getCurrentBoard().getDimension())){
                System.out.println("Game is a draw");
                break;
            }
        }

        System.out.println("Final Board Status");
        gameController.displayBoard(game);
    }
}