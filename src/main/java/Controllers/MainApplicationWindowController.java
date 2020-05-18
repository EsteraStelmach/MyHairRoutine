package Controllers;

import dataBase.UserUtils;
import dataBase.domain.User;
import Properties.MainApplicationWindowProperties;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.EntityManagerUtils;
import utils.DialogsUtils;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.*;

public class MainApplicationWindowController {
   private ResourceBundle bundle = ResourceBundle.getBundle("Bundles.messages");


    private String login = bundle.getString("registerWindow.labelLogin");
    private String password = bundle.getString("registerWindow.labelPassword");
    private String name = bundle.getString("mainApplicationWindow.tap1.choiceBox.name");
    private String lastName = bundle.getString("mainApplicationWindow.tap1.choiceBox.lastName");
    private String hairPorosity = bundle.getString("mainApplicationWindow.tap1.choiceBox.porosity");
    private String hairTwistType = bundle.getString("mainApplicationWindow.tap1.choiceBox.twistType");

    private User user = UserUtils.getUser();


   private ObservableList<String> informationToEdit = FXCollections.observableArrayList();
   private ArrayList<String> porosityList = new ArrayList<String>(SecondRegisterWindowController.getHairPorosityList());
   private ArrayList<String> twistList = new ArrayList<String>(SecondRegisterWindowController.getHairTwistList());

    private EntityManager entityManager = EntityManagerUtils.getEntityManager();

    private MainApplicationWindowProperties mainApplicationWindowProperties = new MainApplicationWindowProperties();

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHairPorosity() {
        return hairPorosity;
    }

    public String getHairTwistType() {
        return hairTwistType;
    }

    @FXML
    private ComboBox <String> editComboBox;
    @FXML
    private Label porosityLabel;
    @FXML
    private Label twistTypeLabel;

    @FXML
    public void initialize(){
        porosityLabel.setText(UserUtils.getHairPorosity());
        twistTypeLabel.setText(UserUtils.getHairTwistType());
        logEditInformationToChoiceBox();
        editComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
           @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               editInformation(newValue);
            }
        });

    }

   public void logOutToLoginWindow() throws IOException {
        DialogsUtils.logOutAlert();
    }

    private void logEditInformationToChoiceBox(){
        informationToEdit.addAll(login,lastName,password,name,hairPorosity,hairTwistType);
        editComboBox.getItems().addAll(informationToEdit);
    }

    private void choiceDialogForEditInformation(String chosenOption){

        ChoiceDialog<String> choiceDialog;
        if(chosenOption.equals(hairPorosity)){
            choiceDialog = DialogsUtils.editChoiceBoxInformation(UserUtils.getHairPorosity(),porosityList);
            choiceDialog.setContentText(bundle.getString("mainApplicationWindow.editChoiceBox.hairPorosity"));
            Optional<String> result = choiceDialog.showAndWait();
            if(result.isPresent()){
                UserUtils.upDateUserPorosity(result.get(),entityManager);
            }
        }else if(chosenOption.equals(hairTwistType)) {
            choiceDialog = DialogsUtils.editChoiceBoxInformation(UserUtils.getHairTwistType(), twistList);
            choiceDialog.setContentText(bundle.getString("mainApplicationWindow.editChoiceBox.hairPorosity"));
            Optional<String> result = choiceDialog.showAndWait();
            if(result.isPresent()){
                UserUtils.upDateUserTwistType(result.get(),entityManager);
            }
        }

    }

    private void textInputDialogForEditInformation(String chosenOption) {
        TextInputDialog textInputDialog;
      if (chosenOption.equals(login)) {
            textInputDialog = (TextInputDialog) DialogsUtils.editTextDialog(chosenOption);
            textInputDialog.setContentText(bundle.getString("mainApplicationWindow.editChoiceBox.login"));
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
              UserUtils.upDateUserLogin(result.get(), entityManager);
            }
        }else if (chosenOption.equals(password)) {
            textInputDialog = (TextInputDialog) DialogsUtils.editTextDialog(chosenOption);
            textInputDialog.setContentText(bundle.getString("mainApplicationWindow.editChoiceBox.password"));
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserPassword(result.get(), entityManager);
            }
        }else if (chosenOption.equals(name)) {
            textInputDialog = (TextInputDialog) DialogsUtils.editTextDialog(chosenOption);
            textInputDialog.setContentText(bundle.getString("mainApplicationWindow.editChoiceBox.name"));
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserName(result.get(), entityManager);
            }
        }else if (chosenOption.equals(lastName)) {
            textInputDialog = (TextInputDialog) DialogsUtils.editTextDialog(chosenOption);
            textInputDialog.setContentText(bundle.getString("mainApplicationWindow.editChoiceBox.lastName"));
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserLastName(result.get(), entityManager);
            }

        }
    }



    private void editInformation (String chosenOption){

        if (chosenOption.equals(hairPorosity) || chosenOption.equals(hairTwistType)) {
            choiceDialogForEditInformation(chosenOption);
        } else {
           textInputDialogForEditInformation(chosenOption);
        }
    }
}






