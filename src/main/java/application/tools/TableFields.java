package application.tools;

import collection.HumanBeing;
import collection.HumanBeingInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import l10n_i18n.CurrentLanguage;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TableFields {
    private final HumanBeing humanBeing;

    public TableFields(HumanBeing humanBeing) {
        this.humanBeing = humanBeing;
    }

    public ObservableList<HumanBeingInfo> getTableFields(){
        final ObservableList<String> BOOLEAN_TYPES = CheckboxesConstants.getBooleanTypes();
        final ObservableList<String> WEAPON_TYPES = CheckboxesConstants.getWeaponTypes();
        final ObservableList<String> MOOD_TYPES = CheckboxesConstants.getMoodTypes();
        ChoiceBox<String> choiceBoxIsRealHero = new ChoiceBox<>(BOOLEAN_TYPES);
        choiceBoxIsRealHero.setValue(String.valueOf(humanBeing.isRealHero()));
        ChoiceBox<String> choiceBoxIsToothPick = new ChoiceBox<>(BOOLEAN_TYPES);
        choiceBoxIsToothPick.setValue(String.valueOf(humanBeing.isHasToothpick()));
        ChoiceBox<String> choiceBoxIsCarCool = new ChoiceBox<>(BOOLEAN_TYPES);
        choiceBoxIsCarCool.setValue(String.valueOf(humanBeing.getCar().getStatus()));
        ChoiceBox<String> choiceBoxWeaponTypes = new ChoiceBox<>(WEAPON_TYPES);
        choiceBoxWeaponTypes.setValue(humanBeing.getStringWeaponType());
        ChoiceBox<String> choiceBoxMoodTypes = new ChoiceBox<>(MOOD_TYPES);
        choiceBoxMoodTypes.setValue(humanBeing.getStringMood());

        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        String x = String.valueOf(humanBeing.getCoordinates().getX());
        String y = String.valueOf(humanBeing.getCoordinates().getY());


        if(CurrentLanguage.getCurrentLanguageString().equals("sp")){
            x = x.replace(".", ",");
            y = y.replace(".", ",");
        }

        return FXCollections.observableArrayList(
                new HumanBeingInfo(currentLanguage.getString("name"), humanBeing.getName(), new TextField()),
                new HumanBeingInfo(currentLanguage.getString("coordinates"),
                        x + ";" +
                                y, new TextField()),
                new HumanBeingInfo(currentLanguage.getString("impactSpeed"), String.valueOf(humanBeing.getImpactSpeed()),
                        new TextField()),
                new HumanBeingInfo(currentLanguage.getString("isRealHero"), String.valueOf(humanBeing.isRealHero()),
                        choiceBoxIsRealHero),
                new HumanBeingInfo(currentLanguage.getString("hasToothPick"), String.valueOf(humanBeing.isHasToothpick()),
                        choiceBoxIsToothPick),
                new HumanBeingInfo(currentLanguage.getString("weaponType"), String.valueOf(humanBeing.getWeaponType()),
                        choiceBoxWeaponTypes),
                new HumanBeingInfo(currentLanguage.getString("mood"), String.valueOf(humanBeing.getMood()),
                        choiceBoxMoodTypes),
                new HumanBeingInfo(currentLanguage.getString("carCool"), String.valueOf(humanBeing.getCar().getStatus()),
                        choiceBoxIsCarCool),
                new HumanBeingInfo(currentLanguage.getString("creation date"), humanBeing.getCreationDayDate().format(DateTimeFormatter.ofPattern(currentLanguage.getString("dataFormat"))), new Label()),
                new HumanBeingInfo(currentLanguage.getString("user login"), humanBeing.getUserLogin(), new Label())
        );
    }


}
