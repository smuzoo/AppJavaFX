package colors;

public enum Colors {
    RED("\u001B[31m"),
    PURPLE("\u001B[35m"),
    BLUE("\u001B[34m"),
    RESET("\u001B[0m");

    private String code;

    Colors(String code){
        this.code = code;
    }
    @Override
    public String toString() {
        return code;
    }
}
