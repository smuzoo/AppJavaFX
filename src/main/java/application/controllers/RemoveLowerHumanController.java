package application.controllers;

import application.tools.CheckboxesConstants;
import application.tools.ModalController;
import collection.Vehicle;
import commands.specific.RemoveLowerHumanBeing;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import l10n_i18n.CurrentLanguage;
import utils.CreatorHumanBeingObject;
import utils.readers.ReaderFromConsole;
import validators.Errors;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveLowerHumanController implements Initializable, ModalController {
    private Stage modalStage;

    private final ObservableList<String> BOOLEAN_TYPES = CheckboxesConstants.getBooleanTypes();
    private final ObservableList<String> WEAPON_TYPES = CheckboxesConstants.getTransportTypes();
    private final ObservableList<String> MOOD_TYPES = CheckboxesConstants.getFuelTypes();
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

    @FXML private Label createObject;

    @FXML private Label nameLabel;

    @FXML private Label realHeroLabel;

    @FXML private Label hasToothPickLabel;

    @FXML private Label impactSpeedLabel;

    @FXML private Label carCoolLabel;

    @FXML private Label moodLabel;

    @FXML private Label weaponTypeLabel;

    @FXML private Label coordinatesLabel;

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
            CreatorHumanBeingObject creatorHumanBeingObject = new CreatorHumanBeingObject(new ReaderFromConsole());
            Vehicle vehicle = creatorHumanBeingObject.create(name, coordinates, impactSpeed, realHero, hasToothPick,
                    weaponType, mood, carCool);
            Errors error = creatorHumanBeingObject.getError();
            if(error == Errors.NOTHAVEERRORS){
                RemoveLowerHumanBeing removeLowerHumanBeing = new RemoveLowerHumanBeing(new ReaderFromConsole());
                removeLowerHumanBeing.remove(vehicle);
                modalStage.close();
            }else {
                errorText.setText(error.getError());
                errorText.setStyle("-fx-fill: red");
            }
            errorText.setVisible(true);
        });

        closeButton.setOnAction(event -> modalStage.close());
        setLanguages();
    }

    public void setLanguages(){
        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        createObject.setText(currentLanguage.getString("createObject"));
        nameLabel.setText(currentLanguage.getString("name"));
        coordinatesLabel.setText(currentLanguage.getString("coordinates"));
        realHeroLabel.setText(currentLanguage.getString("isRealHero"));
        hasToothPickLabel.setText(currentLanguage.getString("hasToothPick"));
        weaponTypeLabel.setText(currentLanguage.getString("weaponType"));
        moodLabel.setText(currentLanguage.getString("mood"));
        carCoolLabel.setText(currentLanguage.getString("carCool"));
        impactSpeedLabel.setText(currentLanguage.getString("impactSpeed"));
        submitButton.setText(currentLanguage.getString("createButton"));
    }

    public void setModalStage(Stage modalStage) {
        this.modalStage = modalStage;
    }
}
