package Controllers;

import Properties.LoginWindowProperties;
import dataBase.DataBaseConnection;
import dataBase.User;
import utils.DialogsUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import utils.fxmlUtils;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginWindowController {


    private String loginWindowFxmlName = "/fxml/LoginWindow.fxml";
    private String registerWindowFxmlName = "/fxml/RegisterWindow.fxml";
    private String mainApplicationWindowFxml = "/fxml/MainApplicationWindow.fxml";

    @FXML
    private TextField loginText;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button registerButton;

    private LoginWindowProperties loginWindowProperties = new LoginWindowProperties();

    private DataBaseConnection baseConnection = new DataBaseConnection();

    private User user = new User();

    @FXML
    public void initialize(){
        //Binding
        loginText.textProperty().bindBidirectional(loginWindowProperties.loginFieldPropertyProperty());
        passwordPasswordField.textProperty().bindBidirectional(loginWindowProperties.passwordFieldPropertyProperty());
        passwordPasswordField.disableProperty().bind(loginWindowProperties.disablePasswordPropertyProperty());
        loginButton.disableProperty().bind(loginWindowProperties.loginButtonPropertyProperty());
        baseConnection.connect();
        user.connect();
    }


    public Scene setLoginWindowScene(){
       Scene scene= new Scene(fxmlUtils.fxmlLoader(loginWindowFxmlName));

        return scene;
    }


    public void loginToApplication() {
        if (user.isLoginAndPasswordCorrect(loginText.getText(), passwordPasswordField.getText())) {
            Scene mainApplicationWindowScene = new Scene(fxmlUtils.fxmlLoader(mainApplicationWindowFxml));
            Stage window = (Stage) loginButton.getScene().getWindow();
            window.setScene(mainApplicationWindowScene);
            window.show();
        } else {
            DialogsUtils.userLoginOrPasswordIsNotCorrect();
        }
    }

    public void openRegisterWindow() {

        Scene registerWindowScene = new Scene(fxmlUtils.fxmlLoader(registerWindowFxmlName));
        Stage window = (Stage) registerButton.getScene().getWindow();
        window.setScene(registerWindowScene);



    }

    public void closeApplication() {
        Optional result = DialogsUtils.exitApplication();
        if(result.get()== ButtonType.OK) {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        }
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
}
