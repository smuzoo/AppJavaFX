package application.tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CheckboxesConstants {
    private static final ObservableList<String> BOOLEAN_TYPES = FXCollections
            .observableArrayList("false", "true");
    private static final ObservableList<String> WEAPON_TYPES = FXCollections
            .observableArrayList("null", "car", "boat", "motorcycle", "ship", "chopper");
    private static final ObservableList<String> MOOD_TYPES = FXCollections
            .observableArrayList("null", "gasoline", "nuclear", "alcohol", "kerosene");

    public static ObservableList<String> getBooleanTypes(){
        return BOOLEAN_TYPES;
    }

    public static ObservableList<String> getWeaponTypes(){
        return WEAPON_TYPES;
    }

    public static ObservableList<String> getMoodTypes(){
        return MOOD_TYPES;
    }


}
