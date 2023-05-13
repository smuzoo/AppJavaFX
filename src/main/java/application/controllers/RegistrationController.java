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
import validators.Errors;

import java.io.IOException;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorText.setVisible(false);
        registrationButton.setOnAction(event -> {
            String login = loginField.getText();
            String password = passwordField.getText();
            Errors error = Authentication.register(login, password);
            if (error == Errors.NOTHAVEERRORS) {
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
            else{
                errorText.setText(error.getError());
                errorText.setVisible(true);
            }
        });
    }
}
