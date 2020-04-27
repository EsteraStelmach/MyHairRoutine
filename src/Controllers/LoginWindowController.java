package Controllers;

import Properties.LoginWindowProperties;
import dataBase.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ResourceBundle;

public class LoginWindowController {

    User user;

    protected String fxmlName = "/fxml/LoginWindow.fxml";

    @FXML
    private TextField loginText;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button loginButton;


    private LoginWindowProperties loginWindowProperties = new LoginWindowProperties();

    ResourceBundle bundle = ResourceBundle.getBundle("Bundles.messages");


    public Scene setLoginWindowScene(){
       FXMLLoader fxmlLoader = new FXMLLoader();
       Parent loginWindowBorderPane;
       Scene scene=null;
       try {
           loginWindowBorderPane = fxmlLoader.load(getClass().getResource(fxmlName), bundle);
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

    public void openRegisterWindow(ActionEvent event) {

        try {
            Parent registerWindowParent = FXMLLoader.load(getClass().getResource("/fxml/RegisterWindow.fxml"),bundle);
            Scene registerWindowScene = new Scene(registerWindowParent);
            Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            window.setScene(registerWindowScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
