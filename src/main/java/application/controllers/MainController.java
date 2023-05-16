package application.controllers;

import application.tools.AddModalSceneHandler;
import application.tools.Scenes;
import application.tools.ChangeSceneHandler;
import application.tools.Column;
import collection.HumanBeing;
import collection.HumanBeingCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML private TableView<HumanBeing> tableHumanBeingInfo;
    @FXML private TableColumn<HumanBeing, Long> idColumn;
    @FXML private TableColumn<HumanBeing, String> nameColumn;
    @FXML private Button leaveButton;
    @FXML private Button addButton;
    @FXML private Button deleteByIdButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableHumanBeingInfo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellFactory(column -> new Column<>());
        nameColumn.setCellFactory(column -> new Column<>());
        tableHumanBeingInfo.setStyle("-fx-background-color: #6B3982; -fx-table-cell-border-color: transparent;");




        ObservableList<HumanBeing> data = FXCollections.observableArrayList(
                HumanBeingCollection.getHumanBeings()
        );

        tableHumanBeingInfo.setItems(data);

        addButton.setOnAction(new AddModalSceneHandler(Scenes.ADDFORM));

        deleteByIdButton.setOnAction(new AddModalSceneHandler(Scenes.DELETEBYID));

        leaveButton.setOnAction(new ChangeSceneHandler(Scenes.LOGIN));

    }
}
