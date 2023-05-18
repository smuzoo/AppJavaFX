package application.controllers;

import application.tools.ModalController;
import commands.specific.RemoveElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import validators.Errors;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteByIdController implements Initializable, ModalController {

    private Stage modalStage;

    @FXML
    private TextField deleteElementField;
    @FXML
    private Text errorText;
    @FXML private Button closeButton;
    @FXML private Button submitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorText.setVisible(false);

        submitButton.setOnAction(event -> {
            String id = deleteElementField.getText();
            RemoveElement removeElement = new RemoveElement();
            Errors error = removeElement.isExecute(id);
            if(error == Errors.NOTHAVEERRORS){
                errorText.setText("Object was successfully deleted");
                errorText.setStyle("-fx-fill: green");
            }else{
                errorText.setText(error.getError());
                errorText.setStyle("-fx-fill: red");
            }
            errorText.setVisible(true);
        });

        closeButton.setOnAction(event -> modalStage.close());
    }

    public void setModalStage(Stage modalStage) {
        this.modalStage = modalStage;
    }

}
