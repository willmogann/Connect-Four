import java.util.Scanner;

/**
 * A game of Connect Four
 * @author Joey Woodring
 * @author William Morgan
 * @author Jake Pope
 * @author Pierce Willoughby
 */
public class ConnectFour {

    /** Private field for the board in use */
    private static Board board;

    /** Private field for player one's chip */
    private static Chip playerOne = new Chip('X');

    /** Private field for player one's chip */
    private static Chip playerTwo = new Chip('O');

    /**
     * Setup for a game of Connect Four
     * @param args string that is not used
     */
    public static void main(String[] args) {
        // Create variables needed
        Scanner scnr = new Scanner(System.in);
        char gameOption = 'N';
        int numberInRowForWin = 0;
        int gamesWonPlayerOne = 0;
        int gamesWonPlayerTwo = 0;
        int numPiecesPlacedOne = 0;
        int numPiecesPlacedTwo = 0;
        int numInRowOne = 0;
        int numInRowTwo = 0;
        boolean flag = false;

        while (gameOption != 'Q') {
            System.out.println("---------Welcome to Connect Four!---------");
            System.out.print("Enter the number of chips in a row to win: ");
            while (!scnr.hasNextInt()) {
                System.out.print("Please enter an integer: ");
                scnr.nextLine();
            }
            numberInRowForWin = scnr.nextInt();
            scnr.nextLine();
            // Create board object
            board = new Board(numberInRowForWin);
            int size = board.getSize();
            System.out.print(board.toString());
            // Helper variables for game logic
            int counter = 1;
            int column = 0;
            while (true) {
                int boardCounter = 0;
                for (int i = 0; i < size; i++) {
                    if (board.isColumnFull(i)) {
                        boardCounter++;
                    }
                }
                if (boardCounter == size) {
                    System.out.println("Game over! No player wins!");
                    break;
                }
                if (counter % 2 != 0) {
                    column = columnSelect(1, size, scnr);
                    while (board.isColumnFull(column)) {
                        System.out.println("Column " + (column + 1) + " is full.");
                        column = columnSelect(1, size, scnr);
                    }
                    board.dropChip(playerOne, column);
                    flag = board.isWinningGame(playerOne.getType(), playerOne, playerTwo);
                    numPiecesPlacedOne++;
                    numInRowOne = board.getInARow(playerOne, playerOne, playerTwo);
                    System.out.print(board.toString());
                    System.out.println("Player 1 has placed " + numPiecesPlacedOne +
                            " chips, and has " + numInRowOne + " in a row.");
                    if (board.isWinningGame(playerOne.getType(), playerOne, playerTwo)) {
                        System.out.println("Player One Wins!");
                        gamesWonPlayerOne++;
                        System.out.println("Player One has " + gamesWonPlayerOne + " wins!");
                        break;
                    }
                } else if (counter % 2 == 0) {
                    column = columnSelect(2, size, scnr);
                    while (board.isColumnFull(column)) {
                        System.out.println("Column " + column + " is full.");
                        column = columnSelect(2, size, scnr);
                    }
                    board.dropChip(playerTwo, column);
                    flag = board.isWinningGame(playerTwo.getType(), playerOne, playerTwo);
                    numPiecesPlacedTwo++;
                    numInRowTwo = board.getInARow(playerTwo, playerOne, playerTwo);
                    System.out.print(board.toString());
                    System.out.println("Player 2 has placed " + numPiecesPlacedTwo +
                            " chips, and has " + numInRowTwo + " in a row.");
                    if (board.isWinningGame(playerTwo.getType(), playerOne, playerTwo)) {
                        System.out.println("Player Two Wins!");
                        gamesWonPlayerTwo++;
                        System.out.println("Player Two has " + gamesWonPlayerTwo + " wins!");
                        break;
                    }
                }
                counter++;
            }
            numPiecesPlacedOne = 0;
            numPiecesPlacedTwo = 0;
            System.out.print("Press N for a new game or Q to quit: ");
            gameOption = Character.toUpperCase(scnr.next().charAt(0));
            while (gameOption != 'N' && gameOption != 'Q') {
                System.out.println("Invalid input.");
                System.out.print("Press N for a new game or Q to quit: ");
                gameOption = Character.toUpperCase(scnr.next().charAt(0));
            }
        }
    }

    /**
     * Gets the player to input a column to drop a chip in
     * @param player number 1 or 2
     * @param size of the board
     * @param scnr is the scanner used to get player input
     * @return column that the player has selected
     */
    public static int columnSelect(int player, int size, Scanner scnr) {
        int column = 0;
        System.out.print("Player " + player + " input column: ");
        while (!scnr.hasNextInt()) {
            scnr.nextLine();
            System.out.print("Please enter an integer.");
            System.out.print("Player " + player + " input column: ");
        }
        column = scnr.nextInt() - 1;
        scnr.nextLine();
        while (column < 0 || column > size - 1) {
            System.out.println("Usage: Column input must be between 1-" + size + ".");
            System.out.print("Player " + player + " input column: ");
            column = scnr.nextInt() - 1;
            scnr.nextLine();
        }
        return column;
    }
}
