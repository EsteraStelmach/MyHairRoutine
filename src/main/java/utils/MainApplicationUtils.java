package utils;

import Controllers.SecondRegisterWindowController;
import dataBase.ConditionersUtils;
import dataBase.ShampoosUtils;
import dataBase.StylizeUtils;
import dataBase.UserUtils;
import dataBase.domain.Conditioners;
import dataBase.domain.Shampoos;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import jdk.nashorn.internal.ir.LiteralNode;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainApplicationUtils {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundles.messages");

    private static ObservableList<String> washNames = FXCollections.observableArrayList();
    private static EntityManager entityManager = EntityManagerUtils.getEntityManager();


    private static String login = resourceBundle.getString("registerWindow.labelLogin");
    private static String password = resourceBundle.getString("registerWindow.labelPassword");
    private static String name = resourceBundle.getString("mainApplicationWindow.tap1.choiceBox.name");
    private static String lastName = resourceBundle.getString("mainApplicationWindow.tap1.choiceBox.lastName");
    private static String hairPorosity = resourceBundle.getString("mainApplicationWindow.tap1.choiceBox.porosity");
    private static String hairTwistType = resourceBundle.getString("mainApplicationWindow.tap1.choiceBox.twistType");

    private static ObservableList<String> informationToEdit = FXCollections.observableArrayList();
    private static ArrayList<String> porosityList = new ArrayList<String>(SecondRegisterWindowController.getHairPorosityList());
    private static ArrayList<String> twistList = new ArrayList<String>(SecondRegisterWindowController.getHairTwistList());

    public static ObservableList<String> getInformationToEdit() {
        informationToEdit.addAll(login, lastName, password, name, hairPorosity, hairTwistType);
        return informationToEdit;
    }

    private static void choiceDialogForEditInformation(String chosenOption) {

        ChoiceDialog<String> choiceDialog;
        if (chosenOption.equals(hairPorosity)) {
            choiceDialog = DialogsUtils.editChoiceBoxInformation(UserUtils.getHairPorosity(), porosityList);
            choiceDialog.setContentText(resourceBundle.getString("mainApplicationWindow.editChoiceBox.hairPorosity"));
            Optional<String> result = choiceDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserPorosity(result.get(), entityManager);
            }
        } else if (chosenOption.equals(hairTwistType)) {
            choiceDialog = DialogsUtils.editChoiceBoxInformation(UserUtils.getHairTwistType(), twistList);
            choiceDialog.setContentText(resourceBundle.getString("mainApplicationWindow.editChoiceBox.hairTwistType"));
            Optional<String> result = choiceDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserTwistType(result.get(), entityManager);
            }
        }

    }

    private static void textInputDialogForEditInformation(String chosenOption) {
        TextInputDialog textInputDialog;
        if (chosenOption.equals(login)) {
            textInputDialog = (TextInputDialog) DialogsUtils.editTextDialog(chosenOption);
            textInputDialog.setContentText(resourceBundle.getString("mainApplicationWindow.editChoiceBox.login"));
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserLogin(result.get(), entityManager);
            }
        } else if (chosenOption.equals(password)) {
            textInputDialog = (TextInputDialog) DialogsUtils.editTextDialog(chosenOption);
            textInputDialog.setContentText(resourceBundle.getString("mainApplicationWindow.editChoiceBox.password"));
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserPassword(result.get(), entityManager);
            }
        } else if (chosenOption.equals(name)) {
            textInputDialog = (TextInputDialog) DialogsUtils.editTextDialog(chosenOption);
            textInputDialog.setContentText(resourceBundle.getString("mainApplicationWindow.editChoiceBox.name"));
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserName(result.get(), entityManager);
            }
        } else if (chosenOption.equals(lastName)) {
            textInputDialog = (TextInputDialog) DialogsUtils.editTextDialog(chosenOption);
            textInputDialog.setContentText(resourceBundle.getString("mainApplicationWindow.editChoiceBox.lastName"));
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserLastName(result.get(), entityManager);
            }

        }
    }

    public static void editInformation(String chosenOption) {

        if (chosenOption.equals(hairPorosity) || chosenOption.equals(hairTwistType)) {
            choiceDialogForEditInformation(chosenOption);
        } else {
            textInputDialogForEditInformation(chosenOption);
        }
    }



}






























