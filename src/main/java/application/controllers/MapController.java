package application.controllers;

import application.tools.ChangeSceneHandler;
import application.tools.Scenes;
import authentication.User;
import collection.HumanBeing;
import collection.HumanBeingCollection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.*;

public class MapController implements Initializable {

    @FXML
    private AnchorPane mapPane;
    @FXML
    private Label coordinateLabel;
    @FXML
    private Button closeMapButton;
    private static final double RADIUS = 10;
    private static final double SCREEN_WIDTH = 1080;
    private static final double SCREEN_HEIGHT = 768;

    private float oldX;

    private Integer oldY;

    private HumanBeing draggedHumanBeing;


    private final Map<String, Color> colorsUsers = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (HumanBeing human : HumanBeingCollection.getHumanBeings()) {
            String userLogin = human.getUserLogin();
            Color color = colorsUsers.get(userLogin);
            if(color == null) {
                color = generateRandomColor();
                colorsUsers.put(userLogin, color);
            }
            Circle circle = new Circle(human.getCoordinates().getX(), human.getCoordinates().getY(), RADIUS);
            circle.setUserData(human);
            circle.setOnMousePressed(this::onMousePressed);
            circle.setOnMouseDragged(this::onMouseDragged);
            circle.setOnMouseReleased(this::onMouseReleased);
            mapPane.getChildren().add(circle);
        }
        mapPane.setOnMouseMoved(event -> {
            coordinateLabel.setText("X: " + (event.getX()) + ", Y: " + (event.getY()));
        });

        mapPane.getChildren().add(coordinateLabel);

        closeMapButton.setOnAction(new ChangeSceneHandler(Scenes.MAIN));

    }
    private void onMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Circle circle = (Circle) event.getSource();
            draggedHumanBeing = (HumanBeing) circle.getUserData();
            oldX = draggedHumanBeing.getCoordinates().getX();
            oldY = draggedHumanBeing.getCoordinates().getY();
        }
    }

    private void onMouseDragged(MouseEvent event) {
        if (draggedHumanBeing != null) {
            if(!draggedHumanBeing.getUserLogin().equals(User.getLogin())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Невозможно редактировать объект");
                alert.setHeaderText(null);
                alert.setContentText("Вы не можете редактировать данный объект, так как не вы его создали.");
                ButtonType okButton = new ButtonType("OK");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            }
            else {
                Circle circle = (Circle) event.getSource();

                double newX = event.getX();
                double newY = event.getY();

                // Проверка и корректировка координат, чтобы не выйти за границы экрана
                newX = Math.max(RADIUS, Math.min(SCREEN_WIDTH - RADIUS, newX));
                newY = Math.max(RADIUS, Math.min(SCREEN_HEIGHT - RADIUS, newY));

                circle.setCenterX(newX);
                circle.setCenterY(newY);

                draggedHumanBeing.getCoordinates().setX((float) newX);
                draggedHumanBeing.getCoordinates().setY((int) newY);

            }
              }

        coordinateLabel.setText("X: " + (event.getX()) + ", Y: " + (event.getY()));
    }

    private void onMouseReleased(MouseEvent event) {
        if (draggedHumanBeing != null) {

            Circle circle = (Circle) event.getSource();

            double newX = event.getX();
            double newY = event.getY();
            if(newX != oldX && newY != oldY) {
                System.out.println("New " + newY + " " + newX);
                System.out.println("Old " + oldY + " " + oldX);
                // Проверка и корректировка координат, чтобы не выйти за границы экрана
                newX = Math.max(RADIUS, Math.min(SCREEN_WIDTH - RADIUS, newX));
                newY = Math.max(RADIUS, Math.min(SCREEN_HEIGHT - RADIUS, newY));



                // Создание модального окна для подтверждения изменения координат
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение изменения координат");
                alert.setHeaderText(null);
                alert.setContentText("Хотите изменить координаты объекта?");

                // Установка кнопок "Да" и "Нет"
                ButtonType buttonYes = new ButtonType("Да");
                ButtonType buttonNo = new ButtonType("Нет");

                alert.getButtonTypes().setAll(buttonYes, buttonNo);

                // Ожидание ответа пользователя
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == buttonYes) {
                    // Если пользователь нажал "Да", изменяем координаты объекта
                    circle.setCenterX(newX);
                    circle.setCenterY(newY);
                    draggedHumanBeing.getCoordinates().setX((float) newX);
                    draggedHumanBeing.getCoordinates().setY((int) newY);
                } else {
                    // Если пользователь нажал "Нет", возвращаем объект на исходную позицию
                    circle.setCenterX(oldX);
                    circle.setCenterY(oldY);
                    draggedHumanBeing.getCoordinates().setX(oldX);
                    draggedHumanBeing.getCoordinates().setY(oldY);
                }
            }
            draggedHumanBeing = null;

        }

    }
    private Color generateRandomColor() {
        Random random = new Random();
        double red = random.nextDouble();
        double green = random.nextDouble();
        double blue = random.nextDouble();

        return new Color(red, green, blue, 1.0);
    }

}
