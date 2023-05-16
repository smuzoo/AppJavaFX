package application.tools;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddModalSceneHandler implements EventHandler<ActionEvent> {

    private final Scenes modalWindow;

    public AddModalSceneHandler(Scenes modalWindow){
        this.modalWindow = modalWindow;
    }

    @Override
    public void handle(ActionEvent event) {
        Parent modalContent = null;
        try {
            modalContent = FXMLLoader.load(getClass().getResource(modalWindow.getPathToScene()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(modalContent));
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.showAndWait();
    }

}
