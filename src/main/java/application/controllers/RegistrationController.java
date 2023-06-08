package application.controllers;

import application.tools.Scenes;
import application.tools.ChangeSceneHandler;
import authentication.Authentication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import l10n_i18n.CurrentLanguage;
import l10n_i18n.Languages;
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

    @FXML private Label createAccountLabel;

    @FXML private Text haveAccountText;

    @FXML private MenuButton currentLanguageMenu;

    @FXML private MenuItem ruLanguage;

    @FXML private MenuItem itLanguage;

    @FXML private MenuItem dutLanguage;

    @FXML private MenuItem spainLanguage;

    @FXML private Label languageLabel;
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
        ruLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Languages.ru);
            CurrentLanguage.setCurrentLanguageString("ru");
            setLanguage();
        });
        itLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Languages.it);
            CurrentLanguage.setCurrentLanguageString("it");
            setLanguage();
        });
        dutLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Languages.du);
            CurrentLanguage.setCurrentLanguageString("du");
            setLanguage();
        });
        spainLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Languages.sp);
            CurrentLanguage.setCurrentLanguageString("sp");

            setLanguage();
        });
        setLanguage();
    }

    private void setLanguage(){
        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        createAccountLabel.setText(currentLanguage.getString("createAccountText"));
        loginField.setPromptText(currentLanguage.getString("loginField"));
        passwordField.setPromptText(currentLanguage.getString("passwordField"));
        registrationButton.setText(currentLanguage.getString("createButton"));
        haveAccountText.setText(currentLanguage.getString("haveAccountText"));
        toLoginButton.setText(currentLanguage.getString("loginButton"));
        currentLanguageMenu.setText(currentLanguage.getString(CurrentLanguage.getCurrentLanguageString()));
        ruLanguage.setText(currentLanguage.getString("ru"));
        itLanguage.setText(currentLanguage.getString("it"));
        dutLanguage.setText(currentLanguage.getString("du"));
        spainLanguage.setText(currentLanguage.getString("sp"));
        languageLabel.setText(currentLanguage.getString("language"));
    }
}
