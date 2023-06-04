package application.tools;

import collection.HumanBeing;
import collection.HumanBeingInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

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
        choiceBoxIsRealHero.setValue(BOOLEAN_TYPES.get(0));
        ChoiceBox<String> choiceBoxIsToothPick = new ChoiceBox<>(BOOLEAN_TYPES);
        choiceBoxIsToothPick.setValue(BOOLEAN_TYPES.get(0));
        ChoiceBox<String> choiceBoxIsCarCool = new ChoiceBox<>(BOOLEAN_TYPES);
        choiceBoxIsCarCool.setValue(BOOLEAN_TYPES.get(0));
        ChoiceBox<String> choiceBoxWeaponTypes = new ChoiceBox<>(WEAPON_TYPES);
        choiceBoxWeaponTypes.setValue(WEAPON_TYPES.get(0));
        ChoiceBox<String> choiceBoxMoodTypes = new ChoiceBox<>(MOOD_TYPES);
        choiceBoxMoodTypes.setValue(MOOD_TYPES.get(0));
        return FXCollections.observableArrayList(
                new HumanBeingInfo("Name", humanBeing.getName(), new TextField()),
                new HumanBeingInfo("Coordinates",
                        humanBeing.getCoordinates().getX() + "," +
                                humanBeing.getCoordinates().getY(), new TextField()),
                new HumanBeingInfo("ImpactSpeed", String.valueOf(humanBeing.getImpactSpeed()),
                        new TextField()),
                new HumanBeingInfo("Is real hero", String.valueOf(humanBeing.isRealHero()),
                        choiceBoxIsRealHero),
                new HumanBeingInfo("Has tooth pick", String.valueOf(humanBeing.isHasToothpick()),
                        choiceBoxIsToothPick),
                new HumanBeingInfo("Weapon Type", String.valueOf(humanBeing.getWeaponType()),
                        choiceBoxWeaponTypes),
                new HumanBeingInfo("Mood", String.valueOf(humanBeing.getMood()),
                        choiceBoxMoodTypes),
                new HumanBeingInfo("Car cool", String.valueOf(humanBeing.getCar().getStatus()),
                        choiceBoxIsCarCool),
                new HumanBeingInfo("Creation date", humanBeing.getStringCreationDate(), new Label()),
                new HumanBeingInfo("User login", humanBeing.getUserLogin(), new Label())
        );
    }


}
