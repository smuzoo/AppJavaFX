package application.controllers;

import application.tools.Scenes;
import application.tools.ChangeSceneHandler;
import authentication.Authentication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
                ChangeSceneHandler changeSceneHandler = new ChangeSceneHandler(Scenes.MAIN);
                changeSceneHandler.handle(event);
            }
            else {
                errorText.setText("There is no user with this username or password");
                errorText.setVisible(true);
            }
        });

        toRegistrationButton.setOnAction(new ChangeSceneHandler(Scenes.REGISTRATION));

    }


}
