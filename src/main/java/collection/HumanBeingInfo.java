package collection;

import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;

public class HumanBeingInfo {
    private final String nameField;
    private final String valueField;
    private final Control updateField;

    public HumanBeingInfo(String nameField, String valueField, Control updateField) {
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
