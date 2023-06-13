package application.controllers;

import application.tools.*;
import authentication.Authentication;
import authentication.User;
import collection.*;
import commands.Command;
import commands.CommandController;
import commands.specific.ShowInfo;
import commands.specific.Update;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import l10n_i18n.CurrentLanguage;
import l10n_i18n.Languages;
import utils.readers.ReaderFromConsole;
import validators.Errors;

import java.net.URL;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Locale;
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
    private TableColumn<Vehicle, Float> xColumn;
    @FXML
    private TableColumn<Vehicle, Integer> yColumn;

    @FXML private TableColumn<Vehicle, Integer> powerEngineColumn;
    @FXML private TableColumn<Vehicle, String> transportTypeColumn;

    @FXML private TableColumn<Vehicle, String> fuelTypeColumn;


    @FXML
    private Text errorTextTableField;
    @FXML
    private Button updateTableFieldButton;
    @FXML
    private Button deleteTableFieldButton;
    @FXML
    private Button closeTableFieldButton;
    @FXML
    private Button executeScriptButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button mapButton;

    @FXML
    private AnchorPane paneTableField;
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
        tableHumanBeingInfo.setEditable(true);
        tableHumanBeingInfo.setStyle("-fx-background-color: #6B3982; -fx-table-cell-border-color: transparent;");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> {
            Vehicle selectedVehicle = event.getRowValue();
            if (User.getLogin().equals(selectedVehicle.getUserLogin())) {
                try {
                    String newValue = event.getNewValue();
                    Update updateCommand = new Update(selectedVehicle);
                    Errors error = updateCommand.updateHuman(0, newValue);
                    if(error != Errors.NOTHAVEERRORS){
                        showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), error.getError());
                        tableHumanBeingInfo.refresh();
                    }else {
                        updateCommand.updateCollection();
                        updateTable(VehicleCollection.getVehicles());
                    }
                }catch (NullPointerException nullPointerException){
                    showAlert(CurrentLanguage.getCurrentLanguage().getString("null"), CurrentLanguage.getCurrentLanguage().getString("not null"));
                    tableHumanBeingInfo.refresh();
                }
            }else {
                showAlert(CurrentLanguage.getCurrentLanguage().getString("not update"),
                        CurrentLanguage.getCurrentLanguage().getString("not this user"));

                tableHumanBeingInfo.refresh();
            }
        });

        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        xColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        xColumn.setOnEditCommit(event -> {
            Vehicle selectedVehicle = event.getRowValue();
            if (User.getLogin().equals(selectedVehicle.getUserLogin())) {
                try {
                    float newValue = event.getNewValue();
                    Update updateCommand = new Update(selectedVehicle);
                    Errors error = updateCommand.updateX(String.valueOf(newValue));
                    if (error != Errors.NOTHAVEERRORS) {
                        showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), error.getError());
                        tableHumanBeingInfo.refresh();
                    } else {
                        updateCommand.updateCollection();
                        updateTable(VehicleCollection.getVehicles());
                    }
                } catch (NullPointerException nullPointerException) {
                    showAlert(CurrentLanguage.getCurrentLanguage().getString("null"), CurrentLanguage.getCurrentLanguage().getString("not null"));
                    tableHumanBeingInfo.refresh();
                } catch (NumberFormatException  numberFormatException) {
                    showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), Errors.NOTCANTRANSFORMTOINT.getError());
                    tableHumanBeingInfo.refresh();
                }

            }else {
                showAlert(CurrentLanguage.getCurrentLanguage().getString("not update"),
                        CurrentLanguage.getCurrentLanguage().getString("not this user"));

                tableHumanBeingInfo.refresh();
            }
        });

        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        yColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        yColumn.setOnEditCommit(event -> {
            Vehicle selectedVehicle = event.getRowValue();
            if (User.getLogin().equals(selectedVehicle.getUserLogin())) {
                try {
                    float newValue = event.getNewValue();
                    Update updateCommand = new Update(selectedVehicle);
                    Errors error = updateCommand.updateY(String.valueOf(newValue));
                    if(error != Errors.NOTHAVEERRORS){
                        showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), error.getError());
                        tableHumanBeingInfo.refresh();

                    }else {
                        updateCommand.updateCollection();
                        updateTable(VehicleCollection.getVehicles());
                    }

                }catch (NullPointerException nullPointerException){
                    showAlert(CurrentLanguage.getCurrentLanguage().getString("null"), CurrentLanguage.getCurrentLanguage().getString("not null"));
                    tableHumanBeingInfo.refresh();
                }
                catch (NumberFormatException   numberFormatException){
                    showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), Errors.NOTCANTRANSFORMTOINT.getError());
                    tableHumanBeingInfo.refresh();
                }
            }else {
                showAlert(CurrentLanguage.getCurrentLanguage().getString("not update"),
                        CurrentLanguage.getCurrentLanguage().getString("not this user"));

                tableHumanBeingInfo.refresh();
            }
        });
        powerEngineColumn.setCellValueFactory(new PropertyValueFactory<>("impactSpeed"));
        powerEngineColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        powerEngineColumn.setOnEditCommit(event -> {
            Vehicle selectedVehicle = event.getRowValue();
            if (User.getLogin().equals(selectedVehicle.getUserLogin())) {
                try {
                    Integer newValue = event.getNewValue();
                    Update updateCommand = new Update(selectedVehicle);
                    Errors error = updateCommand.updateHuman(2, String.valueOf(newValue));
                    if(error != Errors.NOTHAVEERRORS){
                        showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), error.getError());
                        tableHumanBeingInfo.refresh();

                    }else {
                        updateCommand.updateCollection();
                        updateTable(VehicleCollection.getVehicles());
                    }

                }catch (NullPointerException nullPointerException){
                    showAlert(CurrentLanguage.getCurrentLanguage().getString("null"), CurrentLanguage.getCurrentLanguage().getString("not null"));
                    tableHumanBeingInfo.refresh();
                }
                catch (NumberFormatException   numberFormatException){
                    showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), Errors.NOTCANTRANSFORMTOINT.getError());
                    tableHumanBeingInfo.refresh();
                }
            }else {
                showAlert(CurrentLanguage.getCurrentLanguage().getString("not update"),
                        CurrentLanguage.getCurrentLanguage().getString("not this user"));

                tableHumanBeingInfo.refresh();
            }
        });

        transportTypeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        transportTypeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(CheckboxesConstants.getWeaponTypes()));
        transportTypeColumn.setOnEditCommit(event -> {
            Vehicle selectedVehicle = event.getRowValue();
            if (User.getLogin().equals(selectedVehicle.getUserLogin())) {
                String newValue = event.getNewValue().toLowerCase();
                Update updateCommand = new Update(selectedVehicle );
                Errors error = updateCommand.updateHuman(5, newValue);
                if(error != Errors.NOTHAVEERRORS){
                    showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), error.getError());
                    tableHumanBeingInfo.refresh();

                }else {
                    updateCommand.updateCollection();
                    updateTable(VehicleCollection.getVehicles());
                }
            }
            else {
                showAlert(CurrentLanguage.getCurrentLanguage().getString("not update"),
                        CurrentLanguage.getCurrentLanguage().getString("not this user"));

                tableHumanBeingInfo.refresh();
            }
        });

        fuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        fuelTypeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(CheckboxesConstants.getMoodTypes()));
        fuelTypeColumn.setOnEditCommit(event -> {
            Vehicle selectedVehicle = event.getRowValue();
            if (User.getLogin().equals(selectedVehicle.getUserLogin())) {
                String newValue = event.getNewValue().toLowerCase().toLowerCase();
                System.out.println("New value: " + newValue);
                Update updateCommand = new Update(selectedVehicle);
                Errors error = updateCommand.updateHuman(6, newValue);
                if(error != Errors.NOTHAVEERRORS){
                    showAlert(CurrentLanguage.getCurrentLanguage().getString("error"), error.getError());
                    tableHumanBeingInfo.refresh();

                }else {
                    updateCommand.updateCollection();
                    updateTable(VehicleCollection.getVehicles());
                }
            }
            else {
                showAlert(CurrentLanguage.getCurrentLanguage().getString("not update"),
                        CurrentLanguage.getCurrentLanguage().getString("not this user"));

                tableHumanBeingInfo.refresh();
            }
        });

        nickname.setText(User.getLogin());

        errorTextTableField.setVisible(false);


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
