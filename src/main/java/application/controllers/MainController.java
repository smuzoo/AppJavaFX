package application.controllers;

import application.tools.*;
import collection.HumanBeing;
import collection.HumanBeingCollection;
import collection.HumanBeingInfo;
import commands.Command;
import commands.CommandController;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import utils.readers.ReaderFromConsole;

import java.net.URL;
import java.util.Collection;
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
    private TableColumn<HumanBeingInfo, Control> editColumn;
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
    @FXML
    private Button executeScriptButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button mapButton;

    private static boolean doubleClickedOnField = false;

    private final Duration searchDelay = Duration.seconds(0.5);
    private javafx.animation.Timeline searchTimeline;

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
                            humanBeingInformationEdit, paneTableField, deleteTableFieldButton, errorTextTableField,
                            updateTableFieldButton, editColumn));
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

        updateTable(HumanBeingCollection.getHumanBeings());

        addButton.setOnAction(event -> {
            Region root = (Region) addButton.getScene().getRoot();
            ModalSceneHandler handler = new ModalSceneHandler(Scenes.ADDFORM, root);
            handler.handle(event);
            updateTable(HumanBeingCollection.getHumanBeings());
        });

        deleteByIdButton.setOnAction(event -> {
            Region root = (Region) deleteByIdButton.getScene().getRoot();
            ModalSceneHandler handler = new ModalSceneHandler(Scenes.DELETEBYID, root);
            handler.handle(event);
            updateTable(HumanBeingCollection.getHumanBeings());
        });

        leaveButton.setOnAction(event -> {
            doubleClickedOnField = false;;
            ChangeSceneHandler changeSceneHandler = new ChangeSceneHandler(Scenes.LOGIN);
            changeSceneHandler.handle(event);
        });

        executeScriptButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a script file");
            CommandController commandController = new CommandController(new ReaderFromConsole());
            Command executeScript = commandController.getCommand("execute_script");
            executeScript.execute(fileChooser.showOpenDialog(tableHumanBeingInfo.getScene().getWindow()).getAbsolutePath());
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (searchTimeline != null) {
                searchTimeline.stop();
            }

            // Запускаем таймлайн с задержкой
            searchTimeline = new javafx.animation.Timeline();
            searchTimeline.getKeyFrames().add(
                    new javafx.animation.KeyFrame(searchDelay, event -> performSearch(newValue))
            );
            searchTimeline.play();
        });

        mapButton.setOnAction(new ChangeSceneHandler(Scenes.MAP));

    }

    private void performSearch(String searchTerm) {
        // Очищаем предыдущий результат поиска
        tableHumanBeingInfo.getItems().clear();

        // Выполняем поиск и добавляем найденные элементы в таблицу
        for (HumanBeing humanBeing : HumanBeingCollection.getHumanBeings()) {
            if (String.valueOf(humanBeing.getId()).contains(searchTerm) || humanBeing.getName().contains(searchTerm)) {
                tableHumanBeingInfo.getItems().add(humanBeing);
            }
        }
    }

    public static boolean isDoubleClickedOnField() {
        return doubleClickedOnField;
    }

    public static void setDoubleClickedOnField(boolean doubleClickedOnField) {
        MainController.doubleClickedOnField = doubleClickedOnField;
    }
    public void updateTable(Collection<HumanBeing> data){
        tableHumanBeingInfo.setItems(FXCollections.observableArrayList(
                data
        ));
        tableHumanBeingInfo.refresh();
    }

}
