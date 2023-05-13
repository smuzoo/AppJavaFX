package application.controllers;

import application.HumanBeingInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {
    @FXML private TableView<HumanBeingInfo> tableView;
    @FXML private TableColumn<HumanBeingInfo, Long> idColumn;
    @FXML private TableColumn<HumanBeingInfo, String> nameColumn;
    @FXML private TableColumn<HumanBeingInfo, String> usernameColumn;

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        ObservableList<HumanBeingInfo> data = FXCollections.observableArrayList(
                new HumanBeingInfo(1L, "Alice", "alice123"),
                new HumanBeingInfo(2L, "Bob", "bob456"),
                new HumanBeingInfo(3L, "Charlie", "charlie789")
        );

        tableView.setItems(data);
    }
}
