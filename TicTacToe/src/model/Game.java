package model;

import exception.*;
import service.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author mdarmanansari
 */
public class Game {
    private Board currentBoard;
    private List<Player> players;
    private Player currentPlayer;
    private GameStatus gameStatus;
    private List<Move> moves;
    private List<Board> boardStates;
    private WinningStrategy winningStrategy;
    private int numberOfSymbols;

    private Game(Board currentBoard, List<Player> players, WinningStrategy winningStrategy) {
        this.currentBoard = currentBoard;
        this.players = players;
        this.currentPlayer = null;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.boardStates = new ArrayList<>();
        this.winningStrategy = winningStrategy;
        this.numberOfSymbols = players.size();
    }

    public static Builder builder() {
        return new Builder();
    }

    public void replayGame(Game game) {
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

                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void undoMove(Game game) {
        List<Move> playedMoves = game.getMoves();
        List<Board> boardStates = game.getBoardStates();

        if (playedMoves.isEmpty()) {
            throw new InvalidUndoMoveException("No moves played yet");
        }

        Move lastMove = playedMoves.get(playedMoves.size() - 1);
        Board lastBoard = boardStates.get(boardStates.size() - 1);

        game.getMoves().remove(lastMove);
        game.getBoardStates().remove(lastBoard);

        Cell lastMoveCell = lastMove.getCell();
        lastMoveCell.setCellState(CellState.EMPTY);
        lastMoveCell.setPlayer(null);

    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Board> getBoardStates() {
        return boardStates;
    }

    public void setBoardStates(List<Board> boardStates) {
        this.boardStates = boardStates;
    }

    public WinningStrategy getWinningStrategy() {
        return winningStrategy;
    }

    public void setWinningStrategy(WinningStrategy winningStrategy) {
        this.winningStrategy = winningStrategy;
    }

    public int getNumberOfSymbols() {
        return numberOfSymbols;
    }

    public void setNumberOfSymbols(int numberOfSymbols) {
        this.numberOfSymbols = numberOfSymbols;
    }

    public static class Builder {
        private int dimension;
        private Board currentBoard;
        private List<Player> players;
        private WinningStrategy winningStrategy;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setCurrentBoard(Board currentBoard) {
            this.currentBoard = currentBoard;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public void validateNumberOfPlayers() {
            if (players.size() < dimension - 2 || players.size() >= dimension) {
                throw new InvalidPlayerException("Player size should be N-2 or N-1 as per board size");
            }
        }

        public void validateBoardSize() {
            if (dimension < 3 || dimension > 10) {
                throw new InvalidBoardSizeException("Board size should be greater than 2 or less than 11");
            }
        }

        public void validatePlayerSymbols() {
            HashSet<Character> symbols = new HashSet<>();
            for (Player player : players) {
                symbols.add(player.getSymbol());
            }

            if (symbols.size() != players.size()) {
                throw new InvalidSymbolSetUpException("There should be unique symbols for all the players");
            }
        }

        public void validateBotCount() {
            int botCount = 0;
            for (Player player : players) {
                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }
            if (botCount > 1 || botCount < 0) {
                throw new InvalidBotCountException("We can have maximum 1 bot per game");
            }
        }

        public void validate() {
            validateNumberOfPlayers();
            validateBoardSize();
            validatePlayerSymbols();
            validateBotCount();
        }

        public Game build() {
            validate();
            return new Game(new Board(dimension), players, winningStrategy);
        }
    }
}
