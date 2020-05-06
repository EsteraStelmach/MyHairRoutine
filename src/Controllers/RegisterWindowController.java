package Controllers;

import Properties.RegisterWindowProperties;
import dataBase.DataBaseConnection;
import dataBase.User;
import dataBase.UserUtils;
import javafx.scene.Scene;
import utils.DialogsUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.fxmlUtils;
import java.sql.SQLException;

public class RegisterWindowController{

    private String secondRegisterWindowName = "/fxml/SecondRegisterWindow.fxml";

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
        DataBaseConnection.connect();


    }

    public void registerUserAndSwitchWindow () throws SQLException {
        if(isPasswordEqualsRepeatPassword()) {
            setSecondRegisterWindowScene();
        }else{
            DialogsUtils.registerPasswordAreNotTheSame();
        }
    }

    private void setSecondRegisterWindowScene() throws SQLException {
        if(!user.isThisUserLoginNotExists(loginTextField.getText())) {
            tryInsertUser();
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

    private void tryInsertUser(){
        try {
            user.insertUser(loginTextField.getText(), passwordTextField1.getText());
            UserUtils.setLogin(loginTextField.getText());
            UserUtils.setPassword(passwordTextField1.getText());
        } catch (Exception e) {
            DialogsUtils.errorDialogConnectingToDataBase(e);
        }
    }

    private boolean isPasswordEqualsRepeatPassword(){
        return passwordTextField1.getText().equals(passwordTextField2.getText());
    }


    public void backToLoginWindow() {
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(loginWindow.setLoginWindowScene());
        window.show();
    }
}
