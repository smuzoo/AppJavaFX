package application.tools;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeSceneHandler implements EventHandler<ActionEvent> {

    private final Scenes changeScene;

    public ChangeSceneHandler(Scenes scene){
        this.changeScene = scene;
    }

    @Override
    public void handle(ActionEvent event) {
        Parent menuParent = null;
        try {
            menuParent = FXMLLoader.load(getClass().getResource(changeScene.getPathToScene()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene menuScene = new Scene(menuParent);
        menuScene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }
}
