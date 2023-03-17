package colors;

/**
 * The enum Colors to highlight text.
 */
public enum Colors {
    /**
     * Red color.
     */
    RED("\u001B[31m"),
    /**
     * Purple color.
     */
    PURPLE("\u001B[35m"),
    /**
     * Blue color.
     */
    BLUE("\u001B[34m"),
    /**
     * Reset color.
     */
    RESET("\u001B[0m");

    private final String code;

    Colors(String code){
        this.code = code;
    }
    @Override
    public String toString() {
        return code;
    }
}
