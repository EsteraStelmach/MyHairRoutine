package Controllers;

import dataBase.domain.User;
import dataBase.UserUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.EntityManagerUtils;
import utils.fxmlUtils;
import javax.persistence.EntityManager;
import java.util.ResourceBundle;
import java.lang.String;

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

    private static ResourceBundle bundle = ResourceBundle.getBundle("Bundles.messages");

    private static ObservableList hairPorosityList = FXCollections.observableArrayList();

    private EntityManager entityManager = EntityManagerUtils.getEntityManager();

    private static String low=bundle.getString("secondRegisterWindow.choiceBox.label.low");
    private static String medium=bundle.getString("secondRegisterWindow.choiceBox.label.medium");
    private static String high=bundle.getString("secondRegisterWindow.choiceBox.label.high");

    public SecondRegisterWindowController()  {
    }

    @FXML
    public void initialize()  {
        loadHairPorosityList();
        loadHairTwistList();
    }


    public static ObservableList getHairPorosityList() {
        hairPorosityList.clear();
        hairPorosityList.addAll(low,medium,high);
        return hairPorosityList;
    }

    public static ObservableList getHairTwistList() {
        hairTwistList.clear();
        hairTwistList.addAll("2a","2b","2c","wurly","3a","3b","3c");
        return hairTwistList;
    }

    private static ObservableList hairTwistList = FXCollections.observableArrayList();

    private void loadHairPorosityList(){
        //choiceBoxHairPorosity.getItems().clear();
        choiceBoxHairPorosity.getItems().addAll(getHairPorosityList());
    }

    private void loadHairTwistList(){
        //choiceBoxHairTwist.getItems().clear();
        choiceBoxHairTwist.getItems().addAll(getHairTwistList());
    }


    public void confirmAndGoToApplication()  {
        entityManager.getTransaction().begin();
        UserUtils.setFoundUser(UserUtils.getLogin(),entityManager);
        UserUtils.setUserInformation();
        User user = UserUtils.getUser();
        user.setFirstName(" ");
        user.setLastName(" ");
        if(!(nameTextField.getText().isEmpty())) {
            user.setFirstName(nameTextField.getText());
        }
        if(!(lastNameTextField.getText().isEmpty())) {
            user.setLastName(lastNameTextField.getText());
        }
        user.setHairPorosity(choiceBoxHairPorosity.getValue());
        user.setHairTwistType(choiceBoxHairTwist.getValue());

        entityManager.getTransaction().commit();
        UserUtils.setFoundUser(UserUtils.getLogin(),entityManager);
        UserUtils.setUserInformation();

        Scene mainApplicationWindowScene = new Scene(fxmlUtils.fxmlLoader(mainApplicationWindowFxml));
        Stage window = (Stage) confirmButton.getScene().getWindow();
        window.setScene(mainApplicationWindowScene);
        window.show();
    }

}
