package application.tools;

import application.controllers.MainController;
import authentication.User;
import collection.Vehicle;
import collection.VehicleCollection;
import collection.VehicleInfo;
import commands.specific.RemoveElement;
import commands.specific.Update;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import validators.Errors;

public class MouseClickedHandler  implements EventHandler<MouseEvent> {
    private final TableCell<Vehicle, String> cell;
    private final TableView<Vehicle> tableView;
    private final TableView<VehicleInfo> humanBeingFieldInformation;
    private final TableView<VehicleInfo> humanBeingInformationEdit;
    private final AnchorPane paneTableField;
    private final Button deleteButton;
    private final Text errorText;
    private final Button updateTableFieldButton;
    private final TableColumn<VehicleInfo, Control> editColumn;

    public MouseClickedHandler(TableCell<Vehicle, String> cell, TableView<Vehicle> tableView,
                               TableView<VehicleInfo> humanBeingFieldInformation,
                               TableView<VehicleInfo> humanBeingInformationEdit, AnchorPane paneTableField,
                               Button deleteButton, Text errorText, Button updateTableFieldButton,
                               TableColumn<VehicleInfo, Control> editColumn) {
        this.cell = cell;
        this.tableView = tableView;
        this.humanBeingFieldInformation = humanBeingFieldInformation;
        this.humanBeingInformationEdit = humanBeingInformationEdit;
        this.paneTableField = paneTableField;
        this.deleteButton = deleteButton;
        this.errorText = errorText;
        this.updateTableFieldButton = updateTableFieldButton;
        this.editColumn = editColumn;
    }
    @Override
    public void handle(MouseEvent event) {
        errorText.setVisible(false);
        MainController.setDoubleClickedOnField(true);
        final int index = cell.getTableRow().getIndex();
        ObservableList<Vehicle> vehicles = tableView.getItems();
        if (index < vehicles.size()) {
            humanBeingFieldInformation.setVisible(false);
            paneTableField.setVisible(false);
            Vehicle vehicle = vehicles.get(index);
            TableFields tableFields = new TableFields(vehicle);
            ObservableList<VehicleInfo> dataField = tableFields.getTableFields();
            if (vehicle.getUserLogin().equals(User.getLogin())) {
                humanBeingInformationEdit.setItems(dataField);
                humanBeingInformationEdit.refresh();
                paneTableField.setVisible(true);
                deleteButton.setOnAction(actionEvent -> {
                    RemoveElement removeElement = new RemoveElement();
                    Errors error = removeElement.isExecute(String.valueOf(vehicle.getId()));
                    if(error == Errors.NOTHAVEERRORS){
                        MainController.setDoubleClickedOnField(false);
                        paneTableField.setVisible(false);
                        tableView.setItems(FXCollections.observableArrayList(
                                VehicleCollection.getVehicles()
                        ));
                        tableView.refresh();
                    }else {
                        errorText.setText(error.getError());
                        errorText.setVisible(true);
                    }
                });
                updateTableFieldButton.setOnAction(e -> {
                   ObservableList<VehicleInfo> itemsMainTable = humanBeingInformationEdit.getItems();
                    boolean isUpdated = true;
                    Update update = new Update(vehicle);
                    for(int i = 0; i < itemsMainTable.size(); i++){
                        Control cell = editColumn.getCellObservableValue(i).getValue();
                        if(cell instanceof TextField text){
                            String textValue = text.getText();
                            if(!textValue.isEmpty()){
                                Errors error = update.updateVehicle(i, textValue);
                                if(error != Errors.NOTHAVEERRORS){
                                    errorText.setText(error.getError());
                                    errorText.setVisible(true);
                                    isUpdated = false;
                                    break;
                                }
                            }
                        } else if (cell instanceof ChoiceBox) {
                            ChoiceBox<String> choiceBox = (ChoiceBox<String>) cell;
                            String value = choiceBox.getValue();
                            if(!value.equals("null")){
                                Errors error = update.updateVehicle(i, value);
                                if(error != Errors.NOTHAVEERRORS){
                                    errorText.setText(error.getError());
                                    errorText.setVisible(true);
                                    isUpdated = false;
                                    break;
                                }

                            }

                        }
                    }
                    if(isUpdated){
                        update.updateCollection();
                        tableView.setItems(FXCollections.observableArrayList(
                                VehicleCollection.getVehicles()
                        ));
                        tableView.refresh();
                        paneTableField.setVisible(false);
                        MainController.setDoubleClickedOnField(false);
                    }
                });
            } else {
                humanBeingFieldInformation.setItems(dataField);
                humanBeingFieldInformation.refresh();
                humanBeingFieldInformation.setVisible(true);
            }
        }
    }

}
