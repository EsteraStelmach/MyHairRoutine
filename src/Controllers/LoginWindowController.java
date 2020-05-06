package Controllers;

import Properties.LoginWindowProperties;
import dataBase.DataBaseConnection;
import dataBase.User;
import dataBase.UserUtils;
import javafx.scene.input.DragEvent;
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

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Optional;


public class LoginWindowController {


    private String loginWindowFxmlName = "/fxml/LoginWindow.fxml";
    private String registerWindowFxmlName = "/fxml/RegisterWindow.fxml";
    private String mainApplicationWindowFxml = "/fxml/MainApplicationWindow.fxml";

    private static Stage window;

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


    @FXML
    public void initialize(){
        //Binding
        loginText.textProperty().bindBidirectional(loginWindowProperties.loginFieldPropertyProperty());
        passwordPasswordField.textProperty().bindBidirectional(loginWindowProperties.passwordFieldPropertyProperty());
        passwordPasswordField.disableProperty().bind(loginWindowProperties.disablePasswordPropertyProperty());
        loginButton.disableProperty().bind(loginWindowProperties.loginButtonPropertyProperty());
        DataBaseConnection.connect();

    }


    public Scene setLoginWindowScene(){
       Scene scene= new Scene(fxmlUtils.fxmlLoader(loginWindowFxmlName));

        return scene;
    }


    public void loginToApplication() throws SQLException {
        User user = new User();
        if (user.isLoginAndPasswordCorrect(loginText.getText(), passwordPasswordField.getText())) {
            UserUtils.setLogin(loginText.getText());
            UserUtils.setPassword(passwordPasswordField.getText());
            UserUtils.selectUserInformation(loginText.getText());
            Scene mainApplicationWindowScene = new Scene(fxmlUtils.fxmlLoader(mainApplicationWindowFxml));
            window = (Stage) loginButton.getScene().getWindow();
            window.setScene(mainApplicationWindowScene);
            window.show();
        } else {
            DialogsUtils.userLoginOrPasswordIsNotCorrect();
            loginText.clear();
            passwordPasswordField.clear();
        }
    }

    public static Stage getWindow() {
        return window;
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
