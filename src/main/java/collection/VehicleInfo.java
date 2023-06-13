package collection;

import javafx.scene.control.Control;

public class VehicleInfo {
    private final String nameField;
    private final String valueField;
    private final Control updateField;

    public VehicleInfo(String nameField, String valueField, Control updateField) {
        this.nameField = nameField;
        this.valueField = valueField;
        this.updateField = updateField;
    }

    public String getNameField() {
        return nameField;
    }

    public String getValueField() {
        return valueField;
    }

    public Control getUpdateField() {
        return updateField;
    }
}
