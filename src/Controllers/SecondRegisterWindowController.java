package Controllers;

import Properties.RegisterWindowProperties;
import Properties.SecondRegisterWindowProperties;
import dataBase.User;
import dataBase.UserUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.fxmlUtils;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecondRegisterWindowController {

    @FXML
    private Button confirmButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ChoiceBox <String> choiceBoxHairTwist;
    @FXML
    private ChoiceBox <String> choiceBoxHairPorosity;

    private String mainApplicationWindowFxml = "/fxml/MainApplicationWindow.fxml";

    private ObservableList hairPorosityList = FXCollections.observableArrayList();

    private ObservableList hairTwistList = FXCollections.observableArrayList();

    private SecondRegisterWindowProperties secondRegisterWindowProperties =  new SecondRegisterWindowProperties();

    private ResourceBundle bundle = ResourceBundle.getBundle("Bundles.messages");

    private RegisterWindowController registerWindowController = new RegisterWindowController();
    private User user;

    public SecondRegisterWindowController()  {
    }


    @FXML
    public void initialize() throws SQLException {
        loadHairPorosityList();
        loadHairTwistList();
        setUser();



    }

    private void setUser() throws SQLException {
       user= new User();
       user.setUserLogin(UserUtils.getLogin());
       user.selectIdUser(UserUtils.getLogin());
    }

    private void loadHairPorosityList(){
        String low=bundle.getString("secondRegisterWindow.choiceBox.label.low");
        String medium=bundle.getString("secondRegisterWindow.choiceBox.label.medium");
        String high=bundle.getString("secondRegisterWindow.choiceBox.label.high");
        hairPorosityList.addAll(low,medium,high);
        choiceBoxHairPorosity.getItems().addAll(hairPorosityList);
    }

    private void loadHairTwistList(){
        hairTwistList.addAll("2a","2b","2c","wurly","3a","3b","3c");
        choiceBoxHairTwist.getItems().addAll(hairTwistList);
    }


    public void confirmAndGoToApplication() throws SQLException {
        if(!(nameTextField.getText().isEmpty())) {
            user.setFirstName(nameTextField.getText());
        }
        if(!(lastNameTextField.getText().isEmpty())) {
            user.setLastName(lastNameTextField.getText());
        }
        if(!(choiceBoxHairPorosity==null)) {
            user.setHairPorosity(choiceBoxHairPorosity.getValue());
        }
        if(!(choiceBoxHairTwist==null))
        user.setHairTwistType(choiceBoxHairTwist.getValue());

        Scene mainApplicationWindowScene = new Scene(fxmlUtils.fxmlLoader(mainApplicationWindowFxml));
        Stage window = (Stage) confirmButton.getScene().getWindow();
        window.setScene(mainApplicationWindowScene);
        window.show();





    }
}
