import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Chip class
 * @author Joey Woodring
 * @author William Morgan
 * @author Jake Pope
 * @author Pierce Willoughby
 */
public class ChipTest {

    /** Chip for testing */
    private Chip playerOne;

    /** Chip for testing */
    private Chip playerTwo;

    /** Set up object fields */
    @BeforeEach
    public void setUp() {
        playerOne = new Chip('x');
        playerTwo = new Chip('o');
    }

    /** Test getter for chip type */
    @Test
    public void testGetter() {
        assertEquals('X', playerOne.getType(), "Player one type");
        assertEquals('O', playerTwo.getType(), "Player two type");
    }

    /** Test equals method for chips */
    @Test
    public void testEquals() {
        assertTrue(playerOne.equals(playerOne), "Test equals for same object");
        assertTrue(playerOne.equals(new Chip('x')), "Test equals for new object of same type");
        assertFalse(playerOne.equals(new Chip('o')), "Test equals for new object different type");
        assertFalse(playerOne.equals(playerTwo), "Test equals against the two players");
    }
}
