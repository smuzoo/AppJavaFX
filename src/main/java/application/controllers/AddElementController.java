package application.controllers;

import application.tools.CheckboxesConstants;
import application.tools.ModalController;
import commands.specific.InsertHumanBeing;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.readers.ReaderFromConsole;
import validators.Errors;

import java.net.URL;
import java.util.ResourceBundle;

public class AddElementController implements Initializable, ModalController {
    private Stage modalStage;

    private final ObservableList<String> BOOLEAN_TYPES = CheckboxesConstants.getBooleanTypes();
    private final ObservableList<String> WEAPON_TYPES = CheckboxesConstants.getWeaponTypes();
    private final ObservableList<String> MOOD_TYPES = CheckboxesConstants.getMoodTypes();
    @FXML private ChoiceBox<String> realHeroChoice;
    @FXML private ChoiceBox<String> hasToothPickChoice;
    @FXML private ChoiceBox<String> weaponTypeChoice;
    @FXML private ChoiceBox<String> moodChoice;
    @FXML private ChoiceBox<String> carChoice;
    @FXML private TextField nameField;
    @FXML private TextField impactSpeedField;
    @FXML private Button submitButton;
    @FXML private TextField coordinatesField;
    @FXML private Text errorText;
    @FXML private Button closeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorText.setVisible(false);

        realHeroChoice.setValue(BOOLEAN_TYPES.get(0));
        realHeroChoice.setItems(BOOLEAN_TYPES);
        hasToothPickChoice.setValue(BOOLEAN_TYPES.get(0));
        hasToothPickChoice.setItems(BOOLEAN_TYPES);
        carChoice.setValue(BOOLEAN_TYPES.get(0));
        carChoice.setItems(BOOLEAN_TYPES);
        weaponTypeChoice.setValue(WEAPON_TYPES.get(0));
        weaponTypeChoice.setItems(WEAPON_TYPES);
        moodChoice.setValue(MOOD_TYPES.get(0));
        moodChoice.setItems(MOOD_TYPES);

        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String coordinates = coordinatesField.getText();
            String impactSpeed = impactSpeedField.getText();
            String realHero = realHeroChoice.getValue();
            String hasToothPick = hasToothPickChoice.getValue();
            String weaponType = weaponTypeChoice.getValue();
            String mood = moodChoice.getValue();
            String carCool = carChoice.getValue();
            InsertHumanBeing insertHumanBeing = new InsertHumanBeing(new ReaderFromConsole());
            System.out.println("Mood: " + mood);
            System.out.println("WeaponType: " + weaponType);
            Errors error = insertHumanBeing.execute(name, coordinates, impactSpeed, realHero, hasToothPick,
                    weaponType, mood, carCool);
            if(error == Errors.NOTHAVEERRORS){
                modalStage.close();

            }else {
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
