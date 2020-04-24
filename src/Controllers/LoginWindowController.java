package Controllers;

import Properties.LoginWindowProperties;
import dataBase.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.util.Locale;

public class LoginWindowController {

    User user;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button loginButton;

    private LoginWindowProperties loginWindowProperties = new LoginWindowProperties();


    @FXML
    public void initialize(){
        //Binding
        loginPasswordField.textProperty().bindBidirectional(loginWindowProperties.loginFieldPropertyProperty());
        passwordPasswordField.textProperty().bindBidirectional(loginWindowProperties.passwordFieldPropertyProperty());
        passwordPasswordField.disableProperty().bind(loginWindowProperties.disablePasswordPropertyProperty());
        loginButton.disableProperty().bind(loginWindowProperties.loginButtonPropertyProperty());

    }



    public void changeLanguagesToPolish() {
        //Locale.setDefault(new Locale("pl"));
    }

    public void changeLanguagesToNetherlands() {
        //Locale.setDefault(new Locale("nl"));
    }

    public void changeLanguagesToEnglish() {
        //Locale.setDefault(new Locale("en"));
    }

    public void loginToApplication() {

    }
}
