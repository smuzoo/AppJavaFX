package application.tools;

public enum Scenes {
    ADDFORM("addElement"),
    DELETEBYID("deleteElement"),
    LOGIN("login"),
    REGISTRATION("registration"),
    MAIN("main");

    private String nameScene;

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
