import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Program to test the board class
 * @author William Morgan
 * @author Jake Pope
 * @author Joey Woodring
 * @author Pierce Willoughby
 */
public class BoardTest {

    /** chip object to represent player one */
    private Chip playerOne;

    /** chip object to represent player two */
    private Chip playerTwo;

    /** board object to represent board */
    private Board board;

    /** final variable to represent four in a row */
    public static final int FOUR_IN_A_ROW = 4;

    /** final variable to represent size of array */
    public static final int SIZE = 8;

    /** Set up object fields */
    @BeforeEach
    public void setUp() {
        playerOne = new Chip('x');
        playerTwo = new Chip('o');
        board = new Board(4);
    }

    /** test Constructor method for board program */
    @Test
    public void testConstructor() {
        boolean expected = true;
        boolean actual = true;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board.getChip(row, col) != null) {
                    actual = false;
                }
            }
        }
        assertEquals(expected, actual, "Test constructor for board");
    }

    /** test dropChip method */
    @Test
    public void testDropChip() {
        board.dropChip(playerOne, 0);
        assertEquals(playerOne, board.getChip(SIZE - 1, 0), "Test drop chip method");
        board.dropChip(playerTwo, 0);
        assertEquals(playerTwo, board.getChip(SIZE - 2, 0), "Test drop chip method in same col");
        board.dropChip(playerOne, 1);
        assertEquals(playerOne, board.getChip(SIZE - 1, 1), "Test drop chip method in diff col");
    }

    /** Test isColumnFull */
    @Test
    public void testIsColumnFull() {
        board = new Board(4);
        board.dropChip(playerOne, 0);
        board.dropChip(playerTwo, 0);
        board.dropChip(playerOne, 0);
        board.dropChip(playerTwo, 0);
        board.dropChip(playerOne, 0);
        board.dropChip(playerTwo, 0);
        board.dropChip(playerOne, 0);
        board.dropChip(playerTwo, 0);
        assertTrue(board.isColumnFull(0), "Test column full method for full column");
        assertFalse(board.isColumnFull(1), "Test column full for empty column");
    }

    /** test isWinningGame method  */
    @Test
    public void testIsWinningGame() {
        board = new Board(4);
        assertFalse(board.isWinningGame(playerOne.getType(), playerOne, playerTwo),
            "Test not winning game");
        for (int i = 0; i < FOUR_IN_A_ROW; i++) {
            board.dropChip(playerOne, 0);
        }
        assertTrue(board.isWinningGame(playerOne.getType(), playerOne, playerTwo),
            "Test vertical winning game");
        board = new Board(4);
        for (int i = 0; i < FOUR_IN_A_ROW; i++) {
            board.dropChip(playerOne, i);
        }
        assertTrue(board.isWinningGame(playerOne.getType(), playerOne, playerTwo),
            "Test horizontal winning game");
        board = new Board(4);
        board.dropChip(playerOne, 0);
        board.dropChip(playerTwo, 1);
        board.dropChip(playerOne, 1);
        board.dropChip(playerTwo, 2);
        board.dropChip(playerTwo, 2);
        board.dropChip(playerOne, 2);
        board.dropChip(playerTwo, 3);
        board.dropChip(playerTwo, 3);
        board.dropChip(playerTwo, 3);
        board.dropChip(playerOne, 3);
        assertTrue(board.isWinningGame(playerOne.getType(), playerOne, playerTwo),
            "Test upward sloping diagonal winning game");
        board = new Board(4);
        board.dropChip(playerTwo, 0);
        board.dropChip(playerTwo, 0);
        board.dropChip(playerTwo, 0);
        board.dropChip(playerOne, 0);
        board.dropChip(playerTwo, 1);
        board.dropChip(playerTwo, 1);
        board.dropChip(playerOne, 1);
        board.dropChip(playerTwo, 2);
        board.dropChip(playerOne, 2);
        board.dropChip(playerOne, 3);
        assertTrue(board.isWinningGame(playerOne.getType(), playerOne, playerTwo),
            "Test downward sloping diagonal winning game");
    }

    /** test toString method */
    @Test
    public void testToString() {
        board = new Board(4);
        for (int i = 0; i < 4; i++) {
            board.dropChip(playerOne, 0);
        }
        String expected = " _ _ _ _ _ _ _ _\n" +
                          "|_|_|_|_|_|_|_|_|\n" +
                          "|_|_|_|_|_|_|_|_|\n" +
                          "|_|_|_|_|_|_|_|_|\n" +
                          "|_|_|_|_|_|_|_|_|\n" +
                          "|X|_|_|_|_|_|_|_|\n" +
                          "|X|_|_|_|_|_|_|_|\n" +
                          "|X|_|_|_|_|_|_|_|\n" +
                          "|X|_|_|_|_|_|_|_|\n";
        assertEquals(expected, board.toString(), "Test to string method");
    }

    /** Test getInARow */
    @Test
    public void testGetInARow() {
        board = new Board(4);
        boolean flag = false;
        for (int i = 0; i < 3; i++) {
            board.dropChip(playerOne, 0);
        }
        flag = board.isWinningGame(playerOne.getType(), playerOne, playerTwo);
        assertEquals(3, board.getInARow(playerOne, playerOne, playerTwo),
            "Test in a row for vertical");
        board = new Board(4);
        for (int i = 0; i < FOUR_IN_A_ROW; i++) {
            board.dropChip(playerOne, i);
        }
        flag = board.isWinningGame(playerOne.getType(), playerOne, playerTwo);
        assertEquals(4, board.getInARow(playerOne, playerOne, playerTwo),
            "Test in a row for horizontal");
        board = new Board(4);
        board.dropChip(playerOne, 0);
        board.dropChip(playerTwo, 1);
        board.dropChip(playerOne, 1);
        board.dropChip(playerTwo, 2);
        board.dropChip(playerTwo, 2);
        board.dropChip(playerOne, 2);
        board.dropChip(playerTwo, 3);
        board.dropChip(playerTwo, 3);
        board.dropChip(playerTwo, 3);
        board.dropChip(playerOne, 3);
        flag = board.isWinningGame(playerOne.getType(), playerOne, playerTwo);
        assertEquals(4, board.getInARow(playerOne, playerOne, playerTwo),
            "Test get in a row for upward diagonal");
        board = new Board(4);
        board.dropChip(playerTwo, 0);
        board.dropChip(playerTwo, 0);
        board.dropChip(playerTwo, 0);
        board.dropChip(playerOne, 0);
        board.dropChip(playerTwo, 1);
        board.dropChip(playerTwo, 1);
        board.dropChip(playerOne, 1);
        board.dropChip(playerTwo, 2);
        board.dropChip(playerOne, 2);
        board.dropChip(playerOne, 3);
        flag = board.isWinningGame(playerOne.getType(), playerOne, playerTwo);
        assertEquals(4, board.getInARow(playerOne, playerOne, playerTwo),
            "Test in a row for diagonal game sloping down");
    }
}
