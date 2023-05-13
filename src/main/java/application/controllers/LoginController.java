package application.controllers;

import application.Scenes;
import authentication.Authentication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button toRegistrationButton;

    @FXML private Text errorText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorText.setVisible(false);
        loginButton.setOnAction(event -> {
            String login = loginField.getText();
            String password = passwordField.getText();
            boolean isLogin = Authentication.isLogin(login, password);
            if(isLogin){
                Parent menuParent = null;
                try {
                    menuParent = FXMLLoader.load(getClass().getResource(Scenes.MAIN.getPathToScene()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene menuScene = new Scene(menuParent);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(menuScene);
                primaryStage.show();
            }
            else {
                errorText.setText("There is no user with this username or password");
                errorText.setVisible(true);
            }
        });

        toRegistrationButton.setOnAction(event -> {
            Parent registrationParent = null;
            try {
                registrationParent = FXMLLoader.load(getClass().getResource(Scenes.REGISTRATION.getPathToScene()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene menuScene = new Scene(registrationParent);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(menuScene);
            primaryStage.show();
        });

    }


}
