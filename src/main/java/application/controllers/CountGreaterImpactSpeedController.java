package application.controllers;

import application.tools.ModalController;
import commands.specific.CountGreaterThanImpactSpeed;
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

public class CountGreaterImpactSpeedController implements Initializable, ModalController {

    private Stage modalStage;



    @FXML
    private TextField impactSpeedField;
    @FXML
    private Text errorText;
    @FXML private Button closeButton;
    @FXML private Button submitButton;

    @FXML private Label enterSpeedLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorText.setVisible(false);

        submitButton.setOnAction(event -> {
            String impactSpeed = impactSpeedField.getText();
            CountGreaterThanImpactSpeed counter = new CountGreaterThanImpactSpeed();
            Errors error = counter.count(impactSpeed);
            if(error == Errors.NOTHAVEERRORS){
                errorText.setText(counter.getResult());
                errorText.setStyle("-fx-fill: green");
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
        enterSpeedLabel.setText(currentLanguage.getString("enter speed"));
        submitButton.setText(currentLanguage.getString("count"));
    }
    public void setModalStage(Stage modalStage) {
        this.modalStage = modalStage;
    }

}
