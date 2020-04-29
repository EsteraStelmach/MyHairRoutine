package Controllers;

import Properties.LoginWindowProperties;
import dataBase.DataBaseConnection;
import dataBase.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
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

    private ResourceBundle bundle = ResourceBundle.getBundle("Bundles.messages");

    private DataBaseConnection baseConnection = new DataBaseConnection();

   private User user = new User();

    public Button getExitButton() {
        return exitButton;
    }

    public Scene setLoginWindowScene(){
       FXMLLoader fxmlLoader = new FXMLLoader();
       Parent loginWindowBorderPane;
       Scene scene=null;
       try {
           loginWindowBorderPane = fxmlLoader.load(getClass().getResource(loginWindowFxmlName), bundle);
           scene= new Scene(loginWindowBorderPane);
       } catch (IOException e) {
           e.printStackTrace();
       }


     return scene;
   }

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
        try {
            if(user.isLoginAndPasswordCorrect(loginText.getText(),passwordPasswordField.getText())) {
                Parent mainApplicationWindowParent = FXMLLoader.load(getClass().getResource(mainApplicationWindowFxml), bundle);
                Scene mainApplicationWindowScene = new Scene(mainApplicationWindowParent);
                Stage window = (Stage) loginButton.getScene().getWindow();
                window.setScene(mainApplicationWindowScene);
                window.show();
            }else{
                System.out.println("Incorrect login or password");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void openRegisterWindow() {

        try {
            Parent registerWindowParent = FXMLLoader.load(getClass().getResource(registerWindowFxmlName),bundle);
            Scene registerWindowScene = new Scene(registerWindowParent);
            Stage window = (Stage) registerButton.getScene().getWindow();
            window.setScene(registerWindowScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void closeApplication() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
