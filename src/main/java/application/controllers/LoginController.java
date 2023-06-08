package application.controllers;

import application.tools.Scenes;
import application.tools.ChangeSceneHandler;
import authentication.Authentication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import l10n_i18n.CurrentLanguage;
import l10n_i18n.Languages;

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

    @FXML private Label loginTextLabel;

    @FXML private Text dontHaveAccountText;

    @FXML private MenuButton currentLanguageMenu;

    @FXML private MenuItem ruLanguage;

    @FXML private MenuItem itLanguage;

    @FXML private MenuItem dutLanguage;

    @FXML private MenuItem spainLanguage;

    @FXML private Label languageLabel;

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

                errorText.setVisible(true);
            }
        });

        toRegistrationButton.setOnAction(new ChangeSceneHandler(Scenes.REGISTRATION));
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
        loginTextLabel.setText(currentLanguage.getString("loginText"));
        loginField.setPromptText(currentLanguage.getString("loginField"));
        passwordField.setPromptText(currentLanguage.getString("passwordField"));
        loginButton.setText(currentLanguage.getString("loginButton"));
        dontHaveAccountText.setText(currentLanguage.getString("dontHaveAccountText"));
        toRegistrationButton.setText(currentLanguage.getString("createAccountButton"));
        errorText.setText(currentLanguage.getString("invalidAuth"));
        currentLanguageMenu.setText(currentLanguage.getString(CurrentLanguage.getCurrentLanguageString()));
        ruLanguage.setText(currentLanguage.getString("ru"));
        itLanguage.setText(currentLanguage.getString("it"));
        dutLanguage.setText(currentLanguage.getString("du"));
        spainLanguage.setText(currentLanguage.getString("sp"));
        languageLabel.setText(currentLanguage.getString("language"));
    }


}
