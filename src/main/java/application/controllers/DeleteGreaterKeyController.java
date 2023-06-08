package application.controllers;

import application.tools.ModalController;
import commands.specific.RemoveElement;
import commands.specific.RemoveGreaterKey;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import l10n_i18n.CurrentLanguage;
import validators.Errors;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteGreaterKeyController implements Initializable, ModalController {

    private Stage modalStage;

    @FXML
    private TextField deleteElementField;
    @FXML
    private Text errorText;
    @FXML private Button closeButton;
    @FXML private Button submitButton;

    @FXML private Label idLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorText.setVisible(false);

        submitButton.setOnAction(event -> {
            String id = deleteElementField.getText();
            RemoveGreaterKey removeGreaterKey = new RemoveGreaterKey();
            Errors error = removeGreaterKey.isExecute(id);
            if(error == Errors.NOTHAVEERRORS){
                modalStage.close();
            }else{
                errorText.setText(error.getError());
                errorText.setStyle("-fx-fill: red");
            }
            errorText.setVisible(true);
        });

        closeButton.setOnAction(event -> modalStage.close());

        setLanguage();
    }


    private void setLanguage(){
        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        idLabel.setText(currentLanguage.getString("enter id"));
        submitButton.setText(currentLanguage.getString("deleteTableFieldButton"));
    }
    public void setModalStage(Stage modalStage) {
        this.modalStage = modalStage;
    }

}
