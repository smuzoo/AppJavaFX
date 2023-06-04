package application.tools;

import application.controllers.MainController;
import authentication.User;
import collection.HumanBeing;
import collection.HumanBeingInfo;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

public class MouseEnteredHandler implements EventHandler<MouseEvent> {
    private final TableCell<HumanBeing, String> cell;
    private final TableView<HumanBeing> tableView;
    private final TableView<HumanBeingInfo> humanBeingFieldInformation;
    private final TableView<HumanBeingInfo> humanBeingInformationEdit;
    private final AnchorPane paneTableField;

    public MouseEnteredHandler(TableCell<HumanBeing, String> cell, TableView<HumanBeing> tableView,
                               TableView<HumanBeingInfo> humanBeingFieldInformation,
                               TableView<HumanBeingInfo> humanBeingInformationEdit, AnchorPane paneTableField) {
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
            ObservableList<HumanBeing> humanBeings = tableView.getItems();
            if (index < humanBeings.size()) {
                HumanBeing humanBeing = humanBeings.get(index);
                TableFields tableFields = new TableFields(humanBeing);
                ObservableList<HumanBeingInfo> dataField = tableFields.getTableFields();
                if (humanBeing.getUserLogin().equals(User.getLogin())) {
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

