package Controllers;

import Properties.RegisterWindowProperties;
import dataBase.DataBaseConnection;
import dataBase.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class RegisterWindowController{

    @FXML
    public TextField loginTextField;
    @FXML
    protected TextField passwordTextField1;
    @FXML
    protected TextField passwordTextField2;//repeat password
    @FXML
    protected Button registerButton;

    private RegisterWindowProperties registerWindowProperties = new RegisterWindowProperties();
    private DataBaseConnection baseConnection = new DataBaseConnection();
    private User user = new User();


    @FXML
    public void initialize(){
        loginTextField.textProperty().bindBidirectional(registerWindowProperties.loginTextFieldPropertyProperty());
        passwordTextField1.textProperty().bindBidirectional(registerWindowProperties.passwordTextFieldPropertyProperty());
        passwordTextField2.textProperty().bindBidirectional(registerWindowProperties.passwordTextField2PropertyProperty());
        registerButton.disableProperty().bind(registerWindowProperties.registerButtonPropertyProperty());
        baseConnection.connect();
        user.connect();

    }

    public void registerUserAndSwitchWindow () throws SQLException {
        if(isPasswordEqualsRepeatPassword()) {
           user.insertUser(loginTextField.getText(), passwordTextField1.getText());
        }else{
            System.out.println("Passwords are not the same");
        }
    }

    private boolean isPasswordEqualsRepeatPassword(){
        return passwordTextField1.getText().equals(passwordTextField2.getText());
    }

































}
