package application;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HumanBeingInfo {
    private LongProperty id;
    private StringProperty username;
    private StringProperty name;

    public HumanBeingInfo(Long id, String name, String username) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }
}
