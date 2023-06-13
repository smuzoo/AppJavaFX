package application.controllers;

import application.tools.*;
import authentication.User;
import collection.Vehicle;
import collection.VehicleCollection;
import collection.VehicleInfo;
import commands.Command;
import commands.CommandController;
import commands.specific.ShowInfo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import l10n_i18n.CurrentLanguage;
import l10n_i18n.Languages;
import utils.readers.ReaderFromConsole;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    private TableView<Vehicle> tableHumanBeingInfo;
    @FXML
    private TableColumn<Vehicle, Long> idColumn;
    @FXML
    private TableColumn<Vehicle, String> nameColumn;
    @FXML
    private Button leaveButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteByIdButton;
    @FXML
    private TableView<VehicleInfo> humanBeingFieldInformation;
    @FXML
    private TableColumn<VehicleInfo, String> nameField;
    @FXML
    private TableColumn<VehicleInfo, String> valueField;
    @FXML
    private TableView<VehicleInfo> humanBeingInformationEdit;
    @FXML
    private TableColumn<VehicleInfo, Control> editColumn;
    @FXML
    private TableColumn<VehicleInfo, String> valueFieldUpdate;
    @FXML
    private TableColumn<VehicleInfo, String> nameFieldUpdate;
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
    @FXML
    private MenuItem clearButton;
    @FXML
    private MenuItem helpButton;
    @FXML
    private MenuItem showInfoButton;
    @FXML
    private MenuItem countGreaterSpeedButton;

    @FXML private MenuButton currentLanguageMenu;

    @FXML private MenuItem ruLanguage;

    @FXML private MenuItem itLanguage;

    @FXML private MenuItem dutLanguage;

    @FXML private MenuItem spainLanguage;

    @FXML private Label languageLabel;

    @FXML private Label nickname;

    @FXML private MenuButton commands;

    @FXML private MenuItem removeGreaterKeyButton;

    @FXML private MenuItem removeGreaterHumanButton;

    @FXML private MenuItem removeLowerHumanButton;

    @FXML private MenuItem showLessSpeedButton;

    @FXML private Label searchLabel;

    @FXML private Label nameLabel;

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
                    TableCell<Vehicle, String> cell = new Column<>();
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

        nickname.setText(User.getLogin());

        errorTextTableField.setVisible(false);
        closeTableFieldButton.setOnAction(event -> {
            paneTableField.setVisible(false);
            doubleClickedOnField = false;
        });

        updateTable(VehicleCollection.getVehicles());

        addButton.setOnAction(event -> {
            Scene root = addButton.getScene();
            ModalSceneHandler handler = new ModalSceneHandler(Scenes.ADDFORM, root);
            handler.handle(event);
            updateTable(VehicleCollection.getVehicles());
        });

        deleteByIdButton.setOnAction(event -> {
            Scene root = deleteByIdButton.getScene();
            ModalSceneHandler handler = new ModalSceneHandler(Scenes.DELETEBYID, root);
            handler.handle(event);
            updateTable(VehicleCollection.getVehicles());
        });

        leaveButton.setOnAction(event -> {
            doubleClickedOnField = false;;
            ChangeSceneHandler changeSceneHandler = new ChangeSceneHandler(Scenes.LOGIN);
            changeSceneHandler.handle(event);
        });

        executeScriptButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(CurrentLanguage.getCurrentLanguage().getString("choose script"));
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

        clearButton.setOnAction(event -> {
            if(User.getLogin().equals("admin")) {
                Command clearCommand = new CommandController(new ReaderFromConsole()).getCommand("clear");
                clearCommand.execute("");
                updateTable(VehicleCollection.getVehicles());
            }else showAlert(CurrentLanguage.getCurrentLanguage().getString("impossible clear"),
                    CurrentLanguage.getCurrentLanguage().getString("not admin"));
        });

        helpButton.setOnAction(event -> {
            showAlert(CurrentLanguage.getCurrentLanguage().getString("info about commands"),
                    CurrentLanguage.getCurrentLanguage().getString("info for commands"));
        });

        showInfoButton.setOnAction(event -> {
            ShowInfo info = new ShowInfo();
            String result = info.execute();
            showAlert(CurrentLanguage.getCurrentLanguage().getString("info about collection"),
                    CurrentLanguage.getCurrentLanguage().getString("info"));
        });


        countGreaterSpeedButton.setOnAction(event -> {
            ModalSceneHandler handler = new ModalSceneHandler(Scenes.COUNTGREATERIMPACTSPEED,
                    addButton.getScene());
            handler.handle(event);
        });
        removeGreaterKeyButton.setOnAction(event -> {
            ModalSceneHandler handler = new ModalSceneHandler(Scenes.DELETEGREATERKEY,
                    addButton.getScene());
            handler.handle(event);
            updateTable(VehicleCollection.getVehicles());
        });
        showLessSpeedButton.setOnAction(event -> {
            ModalSceneHandler handler = new ModalSceneHandler(Scenes.SHOWLESSSPEED,
                    addButton.getScene());
            handler.handle(event);
        });

        removeLowerHumanButton.setOnAction(event -> {
            ModalSceneHandler handler = new ModalSceneHandler(Scenes.REMOVELOWERHUMAN, addButton.getScene());
            handler.handle(event);
            updateTable(VehicleCollection.getVehicles());
        });

        removeGreaterHumanButton.setOnAction(event -> {
            ModalSceneHandler handler = new ModalSceneHandler(Scenes.REMOVEGREATERHUMAN, addButton.getScene());
            handler.handle(event);
            updateTable(VehicleCollection.getVehicles());
        });

        ruLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Languages.ru);
            CurrentLanguage.setCurrentLanguageString("ru");
            setLanguage();
        });
        itLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Languages.it);
            CurrentLanguage.setCurrentLanguageString("it");
            setLanguage();
        });
        dutLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Languages.du);
            CurrentLanguage.setCurrentLanguageString("du");
            setLanguage();
        });
        spainLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Languages.sp);
            CurrentLanguage.setCurrentLanguageString("sp");

            setLanguage();
        });
        setLanguage();
    }


    private void setLanguage(){
        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        currentLanguageMenu.setText(currentLanguage.getString(CurrentLanguage.getCurrentLanguageString()));
        ruLanguage.setText(currentLanguage.getString("ru"));
        itLanguage.setText(currentLanguage.getString("it"));
        dutLanguage.setText(currentLanguage.getString("du"));
        spainLanguage.setText(currentLanguage.getString("sp"));
        languageLabel.setText(currentLanguage.getString("language"));
        mapButton.setText(currentLanguage.getString("mapButton"));
        deleteByIdButton.setText(currentLanguage.getString("deleteByIdButton"));
        executeScriptButton.setText(currentLanguage.getString("executeScriptButton"));
        addButton.setText(currentLanguage.getString("addButton"));
        leaveButton.setText(currentLanguage.getString("leaveButton"));
        commands.setText(currentLanguage.getString("commands"));
        countGreaterSpeedButton.setText(currentLanguage.getString("countGreaterSpeedButton"));
        clearButton.setText(currentLanguage.getString("clearButton"));
        removeGreaterKeyButton.setText(currentLanguage.getString("removeGreaterKeyButton"));
        removeGreaterHumanButton.setText(currentLanguage.getString("removeGreaterHumanButton"));
        removeLowerHumanButton.setText(currentLanguage.getString("removeLowerHumanButton"));
        showInfoButton.setText(currentLanguage.getString("showInfoButton"));
        showLessSpeedButton.setText(currentLanguage.getString("showLessSpeedButton"));
        helpButton.setText(currentLanguage.getString("helpButton"));
        searchLabel.setText(currentLanguage.getString("searchLabel"));
        searchField.setPromptText(currentLanguage.getString("searchField"));
        updateTableFieldButton.setText(currentLanguage.getString("updateTableFieldButton"));
        deleteTableFieldButton.setText(currentLanguage.getString("deleteTableFieldButton"));
        closeTableFieldButton.setText(currentLanguage.getString("closeTableFieldButton"));
        nameLabel.setText(currentLanguage.getString("name"));
    }


    private void performSearch(String searchTerm) {
        // Очищаем предыдущий результат поиска
        tableHumanBeingInfo.getItems().clear();

        // Выполняем поиск и добавляем найденные элементы в таблицу
        for (Vehicle vehicle : VehicleCollection.getVehicles()) {
            if (String.valueOf(vehicle.getId()).contains(searchTerm) || vehicle.getName().contains(searchTerm)) {
                tableHumanBeingInfo.getItems().add(vehicle);
            }
        }
    }

    public static boolean isDoubleClickedOnField() {
        return doubleClickedOnField;
    }

    public static void setDoubleClickedOnField(boolean doubleClickedOnField) {
        MainController.doubleClickedOnField = doubleClickedOnField;
    }
    public void updateTable(Collection<Vehicle> data){
        tableHumanBeingInfo.setItems(FXCollections.observableArrayList(
                data
        ));
        tableHumanBeingInfo.refresh();
    }

    private void showAlert(String title, String result){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(result);
        ButtonType okButton = new ButtonType(CurrentLanguage.getCurrentLanguage().getString("ok"));
        alert.setResizable(true);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }

}
