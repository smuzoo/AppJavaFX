package application.tools;

import javafx.scene.control.TableCell;
import javafx.scene.layout.Pane;

public class Column<S, T> extends TableCell<S, T> {

    private final Pane hoverPane = new Pane();

    public Column(){
        hoverPane.setStyle("-fx-background-color: black;");
        hoverPane.setOpacity(0);

        setOnMouseEntered(event -> hoverPane.setOpacity(0.2));
        setOnMouseExited(event -> hoverPane.setOpacity(0));
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setText(null);
            setStyle("-fx-background-color:  #210B2B;");
        } else {
            setText(item.toString());
            int rowIndex = getIndex();
            if (rowIndex % 2 == 0) {
                setStyle("-fx-background-color: #4F2662; -fx-alignment: CENTER; -fx-text-fill: white;"); // установка первого цвета
            } else {
                setStyle("-fx-background-color: #210B2B; -fx-alignment: CENTER; -fx-text-fill: white;"); // установка второго цвета
            }
        }
    }
}