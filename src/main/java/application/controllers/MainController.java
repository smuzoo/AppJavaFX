package application.controllers;

import application.tools.*;
import collection.HumanBeing;
import collection.HumanBeingCollection;
import collection.HumanBeingInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.beans.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    private TableView<HumanBeing> tableHumanBeingInfo;
    @FXML
    private TableColumn<HumanBeing, Long> idColumn;
    @FXML
    private TableColumn<HumanBeing, String> nameColumn;
    @FXML
    private Button leaveButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteByIdButton;
    @FXML
    private TableView<HumanBeingInfo> humanBeingFieldInformation;
    @FXML
    private TableColumn<HumanBeingInfo, String> nameField;
    @FXML
    private TableColumn<HumanBeingInfo, String> valueField;
    @FXML
    private TableView<HumanBeingInfo> humanBeingInformationEdit;
    @FXML
    private TableColumn<HumanBeingInfo, Void> editColumn;
    @FXML
    private TableColumn<HumanBeingInfo, String> valueFieldUpdate;
    @FXML
    private TableColumn<HumanBeingInfo, String> nameFieldUpdate;
    @FXML
    private Text errorTextTableField;
    @FXML
    private Button updateTableFieldButton;
    @FXML
    private Button deleteTableFieldButton;
    @FXML
    private Button closeTableFieldButton;
    @FXML
    private AnchorPane paneTableField;

    private static boolean doubleClickedOnField = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableHumanBeingInfo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableHumanBeingInfo.setStyle("-fx-background-color: #6B3982; -fx-table-cell-border-color: transparent;");
        humanBeingInformationEdit.setStyle("-fx-background-color: #6B3982; -fx-table-cell-border-color: transparent;");
        editColumn.setCellFactory(column -> new EditTableColumn<>());
        editColumn.setCellValueFactory(new PropertyValueFactory<>("updateField"));
        paneTableField.setVisible(false);
        nameField.setCellValueFactory(new PropertyValueFactory<>("nameField"));
        valueField.setCellValueFactory(new PropertyValueFactory<>("valueField"));
        nameFieldUpdate.setCellValueFactory(new PropertyValueFactory<>("nameField"));
        valueFieldUpdate.setCellValueFactory(new PropertyValueFactory<>("valueField"));
        nameField.setCellFactory(column -> new Column<>());
        valueField.setCellFactory(column -> new Column<>());
        nameFieldUpdate.setCellFactory(column -> new Column<>());
        valueFieldUpdate.setCellFactory(column -> new Column<>());
        humanBeingFieldInformation.setStyle("-fx-background-color: #6B3982; -fx-table-cell-border-color: transparent; -fx-background-radius: 0 0 20 20;");
        humanBeingFieldInformation.setVisible(false);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellFactory(column -> new Column<>());
        nameColumn.setCellFactory(column -> {
                    TableCell<HumanBeing, String> cell = new Column<>();
                    cell.setOnMouseEntered(new MouseEnteredHandler(cell, tableHumanBeingInfo, humanBeingFieldInformation,
                            humanBeingInformationEdit, paneTableField));
                    cell.setOnMouseClicked(new MouseClickedHandler(cell, tableHumanBeingInfo, humanBeingFieldInformation,
                            humanBeingInformationEdit, paneTableField, deleteTableFieldButton, errorTextTableField));
                    cell.setOnMouseExited(event -> {
                        if(!doubleClickedOnField){
                            humanBeingFieldInformation.setVisible(false);
                            paneTableField.setVisible(false);
                        }
                    });

                    return cell;
                }
        );

        errorTextTableField.setVisible(false);
        closeTableFieldButton.setOnAction(event -> {
            paneTableField.setVisible(false);
            doubleClickedOnField = false;
        });

        ObservableList<HumanBeing> data = FXCollections.observableArrayList(
                HumanBeingCollection.getHumanBeings()
        );

        tableHumanBeingInfo.setItems(data);

        addButton.setOnAction(event -> {
            Region root = (Region) addButton.getScene().getRoot();
            ModalSceneHandler handler = new ModalSceneHandler(Scenes.ADDFORM, root);
            handler.handle(event);
            tableHumanBeingInfo.setItems(FXCollections.observableArrayList(
                    HumanBeingCollection.getHumanBeings()
            ));
            tableHumanBeingInfo.refresh();
        });

        deleteByIdButton.setOnAction(event -> {
            Region root = (Region) deleteByIdButton.getScene().getRoot();
            ModalSceneHandler handler = new ModalSceneHandler(Scenes.DELETEBYID, root);
            handler.handle(event);
            tableHumanBeingInfo.setItems(FXCollections.observableArrayList(
                    HumanBeingCollection.getHumanBeings()
            ));
            tableHumanBeingInfo.refresh();
        });

        leaveButton.setOnAction(event -> {
            doubleClickedOnField = false;;
            ChangeSceneHandler changeSceneHandler = new ChangeSceneHandler(Scenes.LOGIN);
            changeSceneHandler.handle(event);
        });

    }

    public static boolean isDoubleClickedOnField() {
        return doubleClickedOnField;
    }

    public static void setDoubleClickedOnField(boolean doubleClickedOnField) {
        MainController.doubleClickedOnField = doubleClickedOnField;
    }
}
