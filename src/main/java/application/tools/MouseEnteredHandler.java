package application.tools;

import application.controllers.MainController;
import authentication.User;
import collection.Vehicle;
import collection.VehicleInfo;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

public class MouseEnteredHandler implements EventHandler<MouseEvent> {
    private final TableCell<Vehicle, String> cell;
    private final TableView<Vehicle> tableView;
    private final TableView<VehicleInfo> humanBeingFieldInformation;
    private final TableView<VehicleInfo> humanBeingInformationEdit;
    private final AnchorPane paneTableField;

    public MouseEnteredHandler(TableCell<Vehicle, String> cell, TableView<Vehicle> tableView,
                               TableView<VehicleInfo> humanBeingFieldInformation,
                               TableView<VehicleInfo> humanBeingInformationEdit, AnchorPane paneTableField) {
        this.cell = cell;
        this.tableView = tableView;
        this.humanBeingFieldInformation = humanBeingFieldInformation;
        this.humanBeingInformationEdit = humanBeingInformationEdit;
        this.paneTableField = paneTableField;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!MainController.isDoubleClickedOnField()) {
            final int index = cell.getTableRow().getIndex();
            ObservableList<Vehicle> vehicles = tableView.getItems();
            if (index < vehicles.size()) {
                Vehicle vehicle = vehicles.get(index);
                TableFields tableFields = new TableFields(vehicle);
                ObservableList<VehicleInfo> dataField = tableFields.getTableFields();
                if (vehicle.getUserLogin().equals(User.getLogin())) {
                    humanBeingInformationEdit.setItems(dataField);
                    humanBeingInformationEdit.refresh();
                    paneTableField.setVisible(true);
                } else {
                    humanBeingFieldInformation.setItems(dataField);
                    humanBeingFieldInformation.refresh();
                    humanBeingFieldInformation.setVisible(true);
                }
            }
        }
    }
}

