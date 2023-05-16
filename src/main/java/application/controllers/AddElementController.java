package application.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddElementController implements Initializable {
    private final ObservableList<String> BOOLEAN_TYPES = FXCollections
            .observableArrayList("false", "true");
    private final ObservableList<String> WEAPON_TYPES = FXCollections
            .observableArrayList("null", "hammer", "pistol", "shotgun");
    private final ObservableList<String> MOOD_TYPES = FXCollections
            .observableArrayList("null", "longing", "gloom", "apathy", "rage");

    @FXML private ChoiceBox<String> realHeroChoice;
    @FXML private ChoiceBox<String> hasToothPickChoice;
    @FXML private ChoiceBox<String> weaponTypeChoice;
    @FXML private ChoiceBox<String> moodChoice;
    @FXML private ChoiceBox<String> carChoice;
    @FXML private TextField nameField;
    @FXML private TextField impactSpeedField;
    @FXML private Button submitButton;
    @FXML private Text errorText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    }
}
