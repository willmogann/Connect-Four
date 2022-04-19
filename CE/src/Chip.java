

/**
 * Class for Chip object to be used in Board.java
 * @author Joey Woodring
 * @author William Morgan
 * @author Jake Pope
 */
public class Chip {

    /**
     * private field for the type of chip(X or O)
     */
    private char type;

    /**
     * constructor the Chip object
     * @param type for type of chip
     * @throws IllegalArgumentException if invalid type
     */
    public Chip(char type) {
        type = Character.toUpperCase(type);
        if (type != 'X' && type != 'O') {
            throw new IllegalArgumentException("Invalid type entry");
        }
        this.type = type;
    }

    /**
     * getter method for the type of chip(X or O)
     * @return type
     */
    public char getType() {
        return this.type;
    }

    /**
     * method to check equality of two chip objects
     * @param o other object to check equality
     * @return true or false if object equals or not
     */
    public boolean equals(Object o) {
        if (!(o instanceof Chip)) {
            return false;
        } else {
            Chip c = (Chip)o;
            return type == c.getType();
        }
    }
}
