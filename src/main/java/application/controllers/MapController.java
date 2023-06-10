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
    import l10n_i18n.CurrentLanguage;

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
        private static final float SCREEN_WIDTH = 1080;
        private static final Integer SCREEN_HEIGHT = 700;

        private float oldX;

        private double oldY;

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
                Circle circle = new Circle(normalizeX(human.getCoordinates().getX()),
                        normalizeY(human.getCoordinates().getY()), RADIUS);
                circle.setFill(color);
                circle.setUserData(human);
                circle.setOnMousePressed(this::onMousePressed);
                circle.setOnMouseDragged(this::onMouseDragged);
                circle.setOnMouseReleased(this::onMouseReleased);
                mapPane.getChildren().add(circle);
            }
            mapPane.setOnMouseMoved(event -> {
                coordinateLabel.setText("X: " + denormalizeX((float) event.getX()) + ", Y: " + denormalizeY(event.getY()));
            });

            closeMapButton.setOnAction(new ChangeSceneHandler(Scenes.MAIN));
            setLanguage();
        }
        private void onMousePressed(MouseEvent event) {
            if (event.getButton() == MouseButton.PRIMARY) {
                Circle circle = (Circle) event.getSource();
                draggedHumanBeing = (HumanBeing) circle.getUserData();
                oldX = normalizeX(draggedHumanBeing.getCoordinates().getX());
                oldY = normalizeY(draggedHumanBeing.getCoordinates().getY());
            }
        }

        private void onMouseDragged(MouseEvent event) {
            if (draggedHumanBeing != null) {
                if(!draggedHumanBeing.getUserLogin().equals(User.getLogin())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(CurrentLanguage.getCurrentLanguage().getString("impossible edit object"));
                    alert.setHeaderText(null);
                    alert.setContentText(CurrentLanguage.getCurrentLanguage().getString("not created this user"));
                    ButtonType okButton = new ButtonType(CurrentLanguage.getCurrentLanguage().getString("ok"));
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

                    draggedHumanBeing.getCoordinates().setX(denormalizeX((float) newX));
                    draggedHumanBeing.getCoordinates().setY((int) denormalizeY(newY));

                }
                  }

            coordinateLabel.setText("X: " + denormalizeX((float) event.getX()) + ", Y: " + denormalizeY(event.getY()));
        }

        private void onMouseReleased(MouseEvent event) {
            if (draggedHumanBeing != null && User.getLogin().equals(draggedHumanBeing.getUserLogin())) {

                Circle circle = (Circle) event.getSource();

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

                    // Ожидание ответа пользователя
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == buttonYes) {
                        // Если пользователь нажал "Да", изменяем координаты объекта
                        circle.setCenterX(newX);
                        circle.setCenterY(newY);
                        draggedHumanBeing.getCoordinates().setX(denormalizeX((float) newX));
                        draggedHumanBeing.getCoordinates().setY((int) denormalizeY(newY));
                    } else {
                        // Если пользователь нажал "Нет", возвращаем объект на исходную позицию
                        circle.setCenterX(oldX);
                        circle.setCenterY(oldY);
                        draggedHumanBeing.getCoordinates().setX(denormalizeX(oldX));
                        draggedHumanBeing.getCoordinates().setY((int) denormalizeY(oldY));
                    }
                }
                draggedHumanBeing = null;

            }


        }

        private void setLanguage(){
            ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
            closeMapButton.setText(currentLanguage.getString("to table"));

        }
        private Color generateRandomColor() {
            Random random = new Random();
            double red = random.nextDouble();
            double green = random.nextDouble();
            double blue = random.nextDouble();

            return new Color(red, green, blue, 1.0);
        }

        private float normalizeX(float x){
            float minValue = -Float.MAX_VALUE;
            float maxValue = -809f;
            return ((x - minValue) / (maxValue - minValue)) * SCREEN_WIDTH;
        }
        private double normalizeY(double y){
            double minValue = Integer.MIN_VALUE;
            double maxValue = Integer.MAX_VALUE;
            double res =  (y - minValue) /   (maxValue - minValue);
            return res*SCREEN_HEIGHT;
        }
        private float denormalizeX(float normalizedX) {
            float minValue = -Float.MAX_VALUE;
            float maxValue = -809f;
            float denormalizedX = normalizedX / SCREEN_WIDTH * (maxValue - minValue) + minValue;
            return denormalizedX;
        }

        private double denormalizeY(double normalizedY) {
            double minValue = Integer.MIN_VALUE;
            double maxValue = Integer.MAX_VALUE;
            double denormalizedY = normalizedY / SCREEN_HEIGHT * (maxValue - minValue) + minValue;
            return denormalizedY;
        }


    }
