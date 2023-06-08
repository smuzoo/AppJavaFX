package application.tools;

public enum Scenes {
    ADDFORM("addElement"),
    DELETEBYID("deleteElement"),
    LOGIN("login"),
    REGISTRATION("registration"),
    MAP("map"),
    COUNTGREATERIMPACTSPEED("countGreaterImpactSpeed"),
    DELETEGREATERKEY("deleteGreaterElements"),

    SHOWLESSSPEED("showLessSpeed"),
    REMOVELOWERHUMAN("removeLowerHuman"),

    REMOVEGREATERHUMAN("removeGreaterHuman"),

    MAIN("main");

    private final String nameScene;

    Scenes(String nameScene) {
        this.nameScene = nameScene;
    }

    public String getPathToScene(){
        return "/" + getNameScene() + "/" + "main.fxml";
    }

    private String getNameScene() {
        return nameScene;
    }
}
