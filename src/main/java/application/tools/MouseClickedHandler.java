package application.tools;

import application.controllers.MainController;
import authentication.User;
import collection.HumanBeing;
import collection.HumanBeingCollection;
import collection.HumanBeingInfo;
import commands.specific.RemoveElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import validators.Errors;

public class MouseClickedHandler  implements EventHandler<MouseEvent> {
    private final TableCell<HumanBeing, String> cell;
    private final TableView<HumanBeing> tableView;
    private final TableView<HumanBeingInfo> humanBeingFieldInformation;
    private final TableView<HumanBeingInfo> humanBeingInformationEdit;
    private final AnchorPane paneTableField;
    private final Button deleteButton;
    private final Text errorText;

    public MouseClickedHandler(TableCell<HumanBeing, String> cell, TableView<HumanBeing> tableView,
                               TableView<HumanBeingInfo> humanBeingFieldInformation,
                               TableView<HumanBeingInfo> humanBeingInformationEdit, AnchorPane paneTableField,
                               Button deleteButton, Text errorText) {
        this.cell = cell;
        this.tableView = tableView;
        this.humanBeingFieldInformation = humanBeingFieldInformation;
        this.humanBeingInformationEdit = humanBeingInformationEdit;
        this.paneTableField = paneTableField;
        this.deleteButton = deleteButton;
        this.errorText = errorText;
    }
    @Override
    public void handle(MouseEvent event) {
        errorText.setVisible(false);
        MainController.setDoubleClickedOnField(true);
        final int index = cell.getTableRow().getIndex();
        ObservableList<HumanBeing> humanBeings = tableView.getItems();
        if (index < humanBeings.size()) {
            humanBeingFieldInformation.setVisible(false);
            paneTableField.setVisible(false);
            HumanBeing humanBeing = humanBeings.get(index);
            TableFields tableFields = new TableFields(humanBeing);
            ObservableList<HumanBeingInfo> dataField = tableFields.getTableFields();
            if (humanBeing.getUserLogin().equals(User.getLogin())) {
                humanBeingInformationEdit.setItems(dataField);
                humanBeingInformationEdit.refresh();
                paneTableField.setVisible(true);
                deleteButton.setOnAction(actionEvent -> {
                    RemoveElement removeElement = new RemoveElement();
                    Errors error = removeElement.isExecute(String.valueOf(humanBeing.getId()));
                    if(error == Errors.NOTHAVEERRORS){
                        MainController.setDoubleClickedOnField(false);
                        paneTableField.setVisible(false);
                        tableView.setItems(FXCollections.observableArrayList(
                                HumanBeingCollection.getHumanBeings()
                        ));
                        tableView.refresh();
                    }else {
                        errorText.setText(error.getError());
                        errorText.setVisible(true);
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
