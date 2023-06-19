package application.controllers;

import application.tools.ChangeSceneHandler;
import application.tools.Scenes;
import authentication.User;
import collection.Vehicle;
import collection.VehicleCollection;
import commands.specific.Update;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import l10n_i18n.CurrentLanguage;

import java.io.File;
import java.net.URL;
import java.util.*;

public class MapController implements Initializable {

    @FXML
    private AnchorPane mapPane;
    @FXML
    private Button closeMapButton;
    private static final double RADIUS = 10;
    private static final float SCREEN_WIDTH = 1080;
    private static final Integer SCREEN_HEIGHT = 700;

    private double oldX;

    private double oldY;

    private Vehicle draggedVehicle;


    private final Map<String, Color> colorsUsers = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File folder = new File("src/main/resources/carsPng");
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        Random random = new Random();

        for (Vehicle human : VehicleCollection.getVehicles()) {


            double radius = RADIUS * 4; // Увеличение размера в 4 раза
            ImageView imageView = new ImageView(new Image("file:" + files[random.nextInt(files.length)].getPath()));
            imageView.setFitWidth(radius);
            imageView.setFitHeight(radius);
            imageView.setX(normalizeX(human.getCoordinates().getX()));
            imageView.setY(normalizeY(human.getCoordinates().getY()));
            imageView.setUserData(human);
            imageView.setOnMousePressed(this::onMousePressed);
            imageView.setOnMouseDragged(this::onMouseDragged);
            imageView.setOnMouseReleased(this::onMouseReleased);
            mapPane.getChildren().add(imageView);
        }

        closeMapButton.setOnAction(new ChangeSceneHandler(Scenes.MAIN));
        setLanguage();
    }
    private void onMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            ImageView image = (ImageView) event.getSource();
            draggedVehicle = (Vehicle) image.getUserData();
            if (draggedVehicle != null) {
                oldX = normalizeX(draggedVehicle.getCoordinates().getX());
                oldY = normalizeY(draggedVehicle.getCoordinates().getY());
            }
        }
    }

    private void onMouseDragged(MouseEvent event) {
        if (draggedVehicle != null) {
            if(!draggedVehicle.getUserLogin().equals(User.getLogin())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(CurrentLanguage.getCurrentLanguage().getString("impossible edit object"));
                alert.setHeaderText(null);
                alert.setContentText(CurrentLanguage.getCurrentLanguage().getString("not created this user"));
                ButtonType okButton = new ButtonType(CurrentLanguage.getCurrentLanguage().getString("ok"));
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            }
            else {
                ImageView image = (ImageView) event.getSource();

                double newX = event.getX();
                double newY = event.getY();

                // Проверка и корректировка координат, чтобы не выйти за границы экрана
                newX = Math.max(RADIUS, Math.min(SCREEN_WIDTH - RADIUS, newX));
                newY = Math.max(RADIUS, Math.min(SCREEN_HEIGHT - RADIUS, newY));

                image.setX(newX);
                image.setY(newY);
                draggedVehicle.getCoordinates().setX(denormalizeX((float) newX));
                draggedVehicle.getCoordinates().setY((int) denormalizeY(newY));

            }
        }

    }

    private void onMouseReleased(MouseEvent event) {
        if (draggedVehicle != null && User.getLogin().equals(draggedVehicle.getUserLogin())) {

            ImageView image = (ImageView) event.getSource();

            double newX = event.getX();
            double newY = event.getY();
            if(newX != oldX && newY != oldY) {
                // Проверка и корректировка координат, чтобы не выйти за границы экрана
                newX = Math.max(RADIUS, Math.min(SCREEN_WIDTH - RADIUS, newX));
                newY = Math.max(RADIUS, Math.min(SCREEN_HEIGHT - RADIUS, newY));



                // Создание модального окна для подтверждения изменения координат
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(CurrentLanguage.getCurrentLanguage().getString("confirm coordinate changes"));
                alert.setHeaderText(null);
                alert.setContentText(CurrentLanguage.getCurrentLanguage().getString("want change coordinates"));

                // Установка кнопок "Да" и "Нет"
                ButtonType buttonYes = new ButtonType(CurrentLanguage.getCurrentLanguage().getString("yes"));
                ButtonType buttonNo = new ButtonType(CurrentLanguage.getCurrentLanguage().getString("no"));

                alert.getButtonTypes().setAll(buttonYes, buttonNo);

                Optional<ButtonType> result = alert.showAndWait();
                float x;
                int y;

                if (result.isPresent() && result.get() == buttonYes) {
                    image.setX(newX);
                    image.setY(newY);
                    x = denormalizeX((float) newX);
                    y = (int) denormalizeY(newY);
                } else {
                    image.setX(oldX);
                    image.setY(oldY);
                    x = denormalizeX(oldX);
                    y = (int) denormalizeY(oldY);
                }
                Update update = new Update(draggedVehicle);
                update.updateVehicle(1, x + "," + y);
                update.updateCollection();
                draggedVehicle.getCoordinates().setX(x);
                draggedVehicle.getCoordinates().setY(y);
            }
            draggedVehicle = null;

        }


    }

    private void setLanguage(){
        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        closeMapButton.setText(currentLanguage.getString("to table"));

    }


    private double normalizeX(float x){
        float minValue = -Float.MAX_VALUE / 4;
        float maxValue = Float.MAX_VALUE / 4;
        return ((x - minValue) / (maxValue - minValue)) * SCREEN_WIDTH;
    }
    private double normalizeY(double y){
        double minValue = Integer.MIN_VALUE;
        double maxValue = Integer.MAX_VALUE;
        double res =  (y - minValue) /   (maxValue - minValue);
        return res*SCREEN_HEIGHT;
    }
    private float denormalizeX(double normalizedX) {
        float minValue = -Float.MAX_VALUE / 4;
        float maxValue = Float.MAX_VALUE / 4;
        double denormalizedX = normalizedX / SCREEN_WIDTH * (maxValue - minValue) + minValue;
        return (float) denormalizedX;
    }

    private double denormalizeY(double normalizedY) {
        double minValue = Integer.MIN_VALUE;
        double maxValue = Integer.MAX_VALUE;
        double denormalizedY = normalizedY / SCREEN_HEIGHT * (maxValue - minValue) + minValue;
        return denormalizedY;
    }


}