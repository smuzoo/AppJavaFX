package application;

public enum Scenes {
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

    public String getNameScene() {
        return nameScene;
    }
}
