

/**
 * Class for the Connect Four Board/Grid
 * @author Joey Woodring
 * @author William Morgan
 * @author Jake Pope
 * @author Pierce Willoughby
 */
public class Board {

    /**
     * private field for the 2D array that makes up the board
     */
    private Chip[][] board;

    /**
     * private field for number of rows needed to win
     */
    private int numberInRowForWin;

    /**
     * private field for size of board
     */
    private int size;

    /**
     * private field for each column of the board
     */
    private Chip[] column;

    /**
     * field for player one horizontal counter
     */
    private int horizontalCounterP1 = 0;

    /**
     * field for player one vertical counter
     */
    private int verticalCounterP1 = 0;

    /**
     * field for player one upward diagonal counter
     */
    private int upDiagonalCounterP1 = 0;

    /**
     * field for player one downward diagonal counter
     */
    private int downDiagonalCounterP1 = 0;

    /**
     * field for player two horizontal counter
     */
    private int horizontalCounterP2 = 0;

    /**
     * field for player two vertical counter
     */
    private int verticalCounterP2 = 0;

    /**
     * field for player two upward diagonal counter
     */
    private int upDiagonalCounterP2 = 0;

    /**
     * field for player two downward diagonal counter
     */
    private int downDiagonalCounterP2 = 0;

    /**
     * constructor for Board object
     * @param numberInRowForWin for number to be in row for win
     */
    public Board(int numberInRowForWin) {
        this.numberInRowForWin = numberInRowForWin;
        size = numberInRowForWin * 2;
        board = new Chip[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = null;
            }
        }
    }

    /**
     * Puts the chip on the gameboard at its proper spot
     * @param row on gameboard
     * @param column on gameboard
     * @return board
     */
    public Chip getChip(int row, int column) {
        return board[row][column];
    }

    /**
     * Getter method for gameboard size
     * @return size of board
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Checks if the column is full
     * @param column on the gameboard
     * @return true if empty, return false if full
     * @throws IllegalArgumentException "Invalid column"
     */
    public boolean isColumnFull(int column) {
        if (column < 0 || column > size) {
            throw new IllegalArgumentException("Invalid column");
        }
        if (board[0][column] != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * drops a chip into the board in selected column
     * @param chip to be dropped
     * @param column for the chip to be dropped
     * @throws IllegalArgumentException if column exceeds board size
     * @throws IllegalArgumentException if column is full
     */
    public void dropChip(Chip chip, int column) {
        if (column < 0 || column > size) {
            throw new IllegalArgumentException("Invalid column");
        }
        if (!isColumnFull(column)) {
            for (int row = board.length - 1; row >= 0; row--) {
                if (board[row][column] == null) {
                    board[row][column] = chip;
                    break;
                }
            }
        } else {
            throw new IllegalArgumentException("Column is full");
        }
    }

    /**
     * determines if there is a winner with the current board
     * @param playerType X or O
     * @param playerOne chip
     * @param playerTwo chip
     * @return true or false if game is winning or not
     * @throws IllegalArgumentException "Invalid player type"
     */
    public boolean isWinningGame(char playerType, Chip playerOne, Chip playerTwo) {
        if (playerType != playerOne.getType() && playerType != playerTwo.getType()) {
            throw new IllegalArgumentException("Invalid player type");
        }
        // Check for horizontal winning game
        int counter = 0;
        horizontalCounterP1 = 0;
        horizontalCounterP2 = 0;
        for (int row = board.length - 1; row >= 0; row--) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == null) {
                    counter = 0;
                } else if (board[row][col].getType() == playerType) {
                    counter++;
                    if (board[row][col].getType() == playerOne.getType()) {
                        if (counter > horizontalCounterP1) {
                            horizontalCounterP1 = counter;
                        }
                    } else if (board[row][col].getType() == playerTwo.getType()) {
                        if (counter > horizontalCounterP2) {
                            horizontalCounterP2 = counter;
                        }
                    }
                } else {
                    counter = 0;
                }
                if (counter == numberInRowForWin) {
                    return true;
                }
            }
        }

        // Check for vertical winning game
        counter = 0;
        verticalCounterP1 = 0;
        verticalCounterP2 = 0;
        for (int col = 0; col < board.length; col++) {
            for (int row = board.length - 1; row >= 0; row--) {
                if (board[row][col] == null) {
                    counter = 0;
                } else if (board[row][col].getType() == playerType) {
                    counter++;
                    if (board[row][col].getType() == playerOne.getType()) {
                        if (counter > verticalCounterP1) {
                            verticalCounterP1 = counter;
                        }
                    } else if (board[row][col].getType() == playerTwo.getType()) {
                        if (counter > verticalCounterP2) {
                            verticalCounterP2 = counter;
                        }
                    }
                } else {
                    counter = 0;
                }
                if (counter == numberInRowForWin) {
                    return true;
                }
            }
        }

        // Check for diagonal winning game with upward slope
        counter = 0;
        upDiagonalCounterP1 = 0;
        upDiagonalCounterP2 = 0;
        for (int row = board.length - 1; row >= board.length - numberInRowForWin - 1; row--) {
            for (int col = 0; col < board.length - numberInRowForWin; col++) {
                for (int i = 0; i < numberInRowForWin; i++) {
                    if (board[row - i][col + i] == null) {
                        counter = 0;
                    } else if (board[row - i][col + i].getType() == playerType) {
                        counter++;
                        if (board[row - i][col + i].getType() == playerOne.getType()) {
                            if (counter > upDiagonalCounterP1) {
                                upDiagonalCounterP1 = counter;
                            }
                        } else if (board[row - i][col + i].getType() == playerTwo.getType()) {
                            if (counter > upDiagonalCounterP2) {
                                upDiagonalCounterP2 = counter;
                            }
                        }
                    } else {
                        counter = 0;
                    }
                }
                if (counter == numberInRowForWin) {
                    return true;
                }
            }
        }

        /*counter = 0;
        for (int row = board.length - 1; row > board.length - numberInRowForWin; row--) {
            int upToIValue = row;
            for (int col = 0; col < board.length - upToIValue; col++) {
                for (int i = 0; i <= upToIValue; i++) {
                    if (board[row - i][col + i] == null) {
                        counter = 0;
                    } else if (board[row - i][col + i].getType() == playerType) {
                        counter++;
                        if (board[row - i][col + i].getType() == playerOne.getType()) {
                            if (counter > downDiagonalCounterP1) {
                                downDiagonalCounterP1 = counter;
                            }
                        } else if (board[row - i][col + i].getType() == playerTwo.getType()) {
                            if (counter > downDiagonalCounterP2) {
                                downDiagonalCounterP2 = counter;
                            }
                        }
                    } else {
                        counter = 0;
                    }
                }
            }
        }*/

        //Check for diagonal winning game with downward slope
        counter = 0;
        downDiagonalCounterP1 = 0;
        downDiagonalCounterP2 = 0;
        for (int row = board.length - numberInRowForWin; row >= 0; row--) {
            for (int col = 0; col < board.length - numberInRowForWin; col++) {
                for (int i = 0; i < numberInRowForWin; i++) {
                    if (board[row + i][col + i] == null) {
                        counter = 0;
                    } else if (board[row + i][col + i].getType() == playerType) {
                        counter++;
                        if (board[row + i][col + i].getType() == playerOne.getType()) {
                            if (counter > downDiagonalCounterP1) {
                                downDiagonalCounterP1 = counter;
                            }
                        } else if (board[row + i][col + i].getType() == playerTwo.getType()) {
                            if (counter > downDiagonalCounterP2) {
                                downDiagonalCounterP2 = counter;
                            }
                        }
                    } else {
                        counter = 0;
                    }
                }
                if (counter == numberInRowForWin) {
                    return true;
                }
            }
        }

        counter = 0;
        for (int row = board.length - 1; row > board.length - numberInRowForWin; row--) {
            int upToIValue = board.length - 1 - row;
            for (int col = 0; col < board.length - upToIValue; col++) {
                for (int i = 0; i <= upToIValue; i++) {
                    if (board[row + i][col + i] == null) {
                        counter = 0;
                    } else if (board[row + i][col + i].getType() == playerType) {
                        counter++;
                        if (board[row + i][col + i].getType() == playerOne.getType()) {
                            if (counter > downDiagonalCounterP1) {
                                downDiagonalCounterP1 = counter;
                            }
                        } else if (board[row + i][col + i].getType() == playerTwo.getType()) {
                            if (counter > downDiagonalCounterP2) {
                                downDiagonalCounterP2 = counter;
                            }
                        }
                    } else {
                        counter = 0;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method to return the number of chips in a row at a given time for an input player
     * @param player for player to check
     * @param playerOne for player one
     * @param playerTwo for player two
     * @return max in a row for player
     */
    public int getInARow(Chip player, Chip playerOne, Chip playerTwo) {
        final int FOUR = 4;
        int[] counters = new int[FOUR];
        if (player.equals(playerOne)) {
            counters[0] = horizontalCounterP1;
            counters[1] = verticalCounterP1;
            counters[2] = upDiagonalCounterP1;
            counters[3] = downDiagonalCounterP1;
        } else if (player.equals(playerTwo)) {
            counters[0] = horizontalCounterP2;
            counters[1] = verticalCounterP2;
            counters[2] = upDiagonalCounterP2;
            counters[3] = downDiagonalCounterP2;
        }
        int max = counters[0];
        for (int val : counters) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    /**
     * Makes the board using a string
     * @return string with the gameboard
     */
    public String toString() {
        String boardString = "";
        for (int i = 0; i < board.length; i++) {
            boardString += " _";
        }
        boardString += "\n";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                boardString += "|";
                if (board[i][j] != null) {
                    boardString += board[i][j].getType();
                } else {
                    boardString += "_";
                }
            }
            boardString += "|\n";
        }
        return boardString;
    }
}
