package application.tools;

import collection.Vehicle;
import collection.VehicleInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import l10n_i18n.CurrentLanguage;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TableFields {
    private final Vehicle vehicle;

    public TableFields(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ObservableList<VehicleInfo> getTableFields(){
        final ObservableList<String> BOOLEAN_TYPES = CheckboxesConstants.getBooleanTypes();
        final ObservableList<String> WEAPON_TYPES = CheckboxesConstants.getTransportTypes();
        final ObservableList<String> MOOD_TYPES = CheckboxesConstants.getFuelTypes();
        ChoiceBox<String> choiceBoxIsRealHero = new ChoiceBox<>(BOOLEAN_TYPES);
        choiceBoxIsRealHero.setValue(String.valueOf(vehicle.isRealHero()));
        ChoiceBox<String> choiceBoxIsToothPick = new ChoiceBox<>(BOOLEAN_TYPES);
        choiceBoxIsToothPick.setValue(String.valueOf(vehicle.isHasToothpick()));
        ChoiceBox<String> choiceBoxIsCarCool = new ChoiceBox<>(BOOLEAN_TYPES);
        choiceBoxIsCarCool.setValue(String.valueOf(vehicle.getCar().getStatus()));
        ChoiceBox<String> choiceBoxWeaponTypes = new ChoiceBox<>(WEAPON_TYPES);
        choiceBoxWeaponTypes.setValue(vehicle.getStringWeaponType());
        ChoiceBox<String> choiceBoxMoodTypes = new ChoiceBox<>(MOOD_TYPES);
        choiceBoxMoodTypes.setValue(vehicle.getFuelTypeString());

        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        String x = String.valueOf(vehicle.getCoordinates().getX());
        String y = String.valueOf(vehicle.getCoordinates().getY());


        if(CurrentLanguage.getCurrentLanguageString().equals("sp")){
            x = x.replace(".", ",");
            y = y.replace(".", ",");
        }

        return FXCollections.observableArrayList(
                new VehicleInfo(currentLanguage.getString("name"), vehicle.getName(), new TextField()),
                new VehicleInfo(currentLanguage.getString("coordinates"),
                        x + ";" +
                                y, new TextField()),
                new VehicleInfo(currentLanguage.getString("impactSpeed"), String.valueOf(vehicle.getImpactSpeed()),
                        new TextField()),
                new VehicleInfo(currentLanguage.getString("isRealHero"), String.valueOf(vehicle.isRealHero()),
                        choiceBoxIsRealHero),
                new VehicleInfo(currentLanguage.getString("hasToothPick"), String.valueOf(vehicle.isHasToothpick()),
                        choiceBoxIsToothPick),
                new VehicleInfo(currentLanguage.getString("weaponType"), String.valueOf(vehicle.getWeaponType()),
                        choiceBoxWeaponTypes),
                new VehicleInfo(currentLanguage.getString("mood"), String.valueOf(vehicle.getFuelType()),
                        choiceBoxMoodTypes),
                new VehicleInfo(currentLanguage.getString("carCool"), String.valueOf(vehicle.getCar().getStatus()),
                        choiceBoxIsCarCool),
                new VehicleInfo(currentLanguage.getString("creation date"), vehicle.getCreationDayDate().format(DateTimeFormatter.ofPattern(currentLanguage.getString("dataFormat"))), new Label()),
                new VehicleInfo(currentLanguage.getString("user login"), vehicle.getUserLogin(), new Label())
        );
    }


}
