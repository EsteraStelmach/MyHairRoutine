package Controllers;

import Properties.RegisterWindowProperties;
import dataBase.domain.User;
import dataBase.UserUtils;
import javafx.scene.Scene;
import utils.EntityManagerUtils;
import utils.DialogsUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.fxmlUtils;

import javax.persistence.EntityManager;


public class RegisterWindowController{

    private String secondRegisterWindowName = "/fxml/SecondRegisterWindow.fxml";
    private EntityManager entityManager = EntityManagerUtils.getEntityManager();

    @FXML
    private TextField loginTextField;
    @FXML
    private Button backButton;
    @FXML
    private TextField passwordTextField1;
    @FXML
    private TextField passwordTextField2;//repeat password
    @FXML
    private Button registerButton;

    private User user = new User();

    private RegisterWindowProperties registerWindowProperties = new RegisterWindowProperties();
    private LoginWindowController loginWindow = new LoginWindowController();


    @FXML
    public void initialize(){
        loginTextField.textProperty().bindBidirectional(registerWindowProperties.loginTextFieldPropertyProperty());
        passwordTextField1.textProperty().bindBidirectional(registerWindowProperties.passwordTextFieldPropertyProperty());
        passwordTextField2.textProperty().bindBidirectional(registerWindowProperties.passwordTextField2PropertyProperty());
        registerButton.disableProperty().bind(registerWindowProperties.registerButtonPropertyProperty());


    }

    public void registerUserAndSwitchWindow ()  {
        if(isPasswordEqualsRepeatPassword()) {
            setSecondRegisterWindowScene();
        }else{
            DialogsUtils.registerPasswordAreNotTheSame();
        }
    }

    private void setSecondRegisterWindowScene()  {
        entityManager.getTransaction().begin();
        UserUtils.setFoundUser(loginTextField.getText(),entityManager);
        if(UserUtils.getFoundUsers().isEmpty()) {
            makeUser();
            Scene secondRegisterWindowScene = new Scene(fxmlUtils.fxmlLoader(secondRegisterWindowName));
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(secondRegisterWindowScene);
            stage.show();
        }else {
            DialogsUtils.thisLoginAlreadyExistsAlert();
            loginTextField.clear();
            passwordTextField1.clear();
            passwordTextField2.clear();
        }
    }

    public User makeUser() {
        User testUser = new User();
        try {
            user.setLogin(loginTextField.getText());
            user.setPassword(passwordTextField1.getText());
            UserUtils.setLogin(loginTextField.getText());
            UserUtils.setPassword(passwordTextField1.getText());
            UserUtils.persistUser(user,entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
           DialogsUtils.errorDialogConnectingToDataBase(e);
        }
        return testUser;
    }

    private boolean isPasswordEqualsRepeatPassword(){
        return passwordTextField1.getText().equals(passwordTextField2.getText());
    }


   public void backToLoginWindow()  {
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(loginWindow.setLoginWindowScene());
        window.show();
    }
}
