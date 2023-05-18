package application.controllers;

import application.tools.Scenes;
import application.tools.ChangeSceneHandler;
import authentication.Authentication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import validators.Errors;

import java.net.URL;
import java.util.ResourceBundle;


public class RegistrationController implements Initializable {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registrationButton;

    @FXML
    private Button photoUser;

    @FXML
    private Text errorText;

    @FXML
    private Button toLoginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorText.setVisible(false);

        registrationButton.setOnAction(event -> {
            String login = loginField.getText();
            String password = passwordField.getText();
            Errors error = Authentication.register(login, password);
            if (error == Errors.NOTHAVEERRORS) {
                ChangeSceneHandler changeSceneHandler = new ChangeSceneHandler(Scenes.MAIN);
                changeSceneHandler.handle(event);
            }
            else{
                errorText.setText(error.getError());
                errorText.setVisible(true);
            }
        });

        toLoginButton.setOnAction(new ChangeSceneHandler(Scenes.LOGIN));
    }
}
