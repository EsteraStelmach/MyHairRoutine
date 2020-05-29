package Controllers;

import Properties.LoginWindowProperties;
import dataBase.UserUtils;
import utils.EntityManagerUtils;
import utils.DialogsUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import utils.fxmlUtils;
import javax.persistence.EntityManager;
import java.util.Optional;

public class LoginWindowController {


    private static String loginWindowFxmlName = "/fxml/LoginWindow.fxml";
    private String registerWindowFxmlName = "/fxml/RegisterWindow.fxml";
    private String mainApplicationWindowFxml = "/fxml/MainApplicationWindow.fxml";
    private EntityManager  entityManager = EntityManagerUtils.getEntityManager();

    private static Stage window = new Stage();

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
        loginText.textProperty().bindBidirectional(loginWindowProperties.loginFieldPropertyProperty());
        passwordPasswordField.textProperty().bindBidirectional(loginWindowProperties.passwordFieldPropertyProperty());
        passwordPasswordField.disableProperty().bind(loginWindowProperties.disablePasswordPropertyProperty());
        loginButton.disableProperty().bind(loginWindowProperties.loginButtonPropertyProperty());
    }

    public static Scene setLoginWindowScene() {
        Scene scene= new Scene(fxmlUtils.fxmlLoader(loginWindowFxmlName));
        return scene;

    }

    public static Stage getWindow() {
        return window;
    }

    public void loginToApplication()  {
        entityManager.getTransaction().begin();
        UserUtils.setFoundUser(loginText.getText(),entityManager);
        if (UserUtils.getFoundUsers().isEmpty() || UserUtils.getFoundUsers()==null) {
            DialogsUtils.userLoginOrPasswordIsNotCorrect();
            loginText.clear();
            passwordPasswordField.clear();
        }else {
            UserUtils.setLogin(loginText.getText());
            UserUtils.setUserInformation();
            setUpWindow();

        }
    }

    private boolean isPasswordCorrect(){
       return UserUtils.getPassword().equals(passwordPasswordField.getText());
    }

    private void setUpWindow(){
        if(isPasswordCorrect()) {
            entityManager.getTransaction().commit();
            Scene mainApplicationWindowScene = new Scene(fxmlUtils.fxmlLoader(mainApplicationWindowFxml));
            window = (Stage) loginButton.getScene().getWindow();
            window.setScene(mainApplicationWindowScene);
            window.show();
        }else {
            DialogsUtils.userLoginOrPasswordIsNotCorrect();
            loginText.clear();
            passwordPasswordField.clear();
            entityManager.getTransaction().commit();
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
            EntityManagerUtils.getEntityManager().close();
            EntityManagerUtils.getEntityManager().getEntityManagerFactory().close();
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
