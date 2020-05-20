package utils;

import Controllers.SecondRegisterWindowController;
import dataBase.UserUtils;
import javafx.collections.FXCollections;
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

    private static TreeItem<String> routineRoot= new TreeItem<>();
    private static TreeItem<String> productsRoot= new TreeItem<>();

    private static ObservableList<String> washNames= FXCollections.observableArrayList();
    private static ObservableList<String> productsNames= FXCollections.observableArrayList();

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundles.messages");
    private static EntityManager entityManager = EntityManagerUtils.getEntityManager();

    private static String wash1 = resourceBundle.getString("mainApplicationUtils.washTreeItem.was1");
    private static String wash2 = resourceBundle.getString("mainApplicationUtils.washTreeItem.was2");
    private static String wash3 = resourceBundle.getString("mainApplicationUtils.washTreeItem.was3");
    private static String wash4 = resourceBundle.getString("mainApplicationUtils.washTreeItem.was4");
    private static String wash5 = resourceBundle.getString("mainApplicationUtils.washTreeItem.was5");
    private static String wash6 = resourceBundle.getString("mainApplicationUtils.washTreeItem.was6");
    private static String wash7 = resourceBundle.getString("mainApplicationUtils.washTreeItem.was7");
    private static String wash8 = resourceBundle.getString("mainApplicationUtils.washTreeItem.was8");
    private static String wash9 = resourceBundle.getString("mainApplicationUtils.washTreeItem.was9");
    private static String wash10 = resourceBundle.getString("mainApplicationUtils.washTreeItem.was10");

    private static String shampoo = resourceBundle.getString("mainApplicationUtils.productsTreeItem.shampoo");
    private static String conditioner= resourceBundle.getString("mainApplicationUtils.productsTreeItem.conditioner");
    private static String stylize= resourceBundle.getString("mainApplicationUtils.productsTreeItem.stylize");
    private static String oil = resourceBundle.getString("mainApplicationUtils.productsTreeItem.oil");
    private static String halfProducts = resourceBundle.getString("mainApplicationUtils.productsTreeItem.halfProducts");

    private static String login = resourceBundle.getString("registerWindow.labelLogin");
    private static String password = resourceBundle.getString("registerWindow.labelPassword");
    private static String name = resourceBundle.getString("mainApplicationWindow.tap1.choiceBox.name");
    private static String lastName = resourceBundle.getString("mainApplicationWindow.tap1.choiceBox.lastName");
    private static String hairPorosity = resourceBundle.getString("mainApplicationWindow.tap1.choiceBox.porosity");
    private static String hairTwistType = resourceBundle.getString("mainApplicationWindow.tap1.choiceBox.twistType");

    private static ObservableList<String> informationToEdit = FXCollections.observableArrayList();
    private static ArrayList<String> porosityList = new ArrayList<String>(SecondRegisterWindowController.getHairPorosityList());
    private static ArrayList<String> twistList = new ArrayList<String>(SecondRegisterWindowController.getHairTwistList());

    public static ObservableList<String> getInformationToEdit(){
        informationToEdit.addAll(login,lastName,password,name,hairPorosity,hairTwistType);
        return informationToEdit;
    }


    public static TreeItem<String> getRoutineRoot() {
        initRoutineRoot();
        return routineRoot;
    }
    public static TreeItem<String> getProductsRoot() {
        initProductsRoot();
        return productsRoot;
    }




    private static void choiceDialogForEditInformation(String chosenOption){

        ChoiceDialog<String> choiceDialog;
        if(chosenOption.equals(hairPorosity)){
            choiceDialog = DialogsUtils.editChoiceBoxInformation(UserUtils.getHairPorosity(),porosityList);
            choiceDialog.setContentText(resourceBundle.getString("mainApplicationWindow.editChoiceBox.hairPorosity"));
            Optional<String> result = choiceDialog.showAndWait();
            if(result.isPresent()){
                UserUtils.upDateUserPorosity(result.get(),entityManager);
            }
        }else if(chosenOption.equals(hairTwistType)) {
            choiceDialog = DialogsUtils.editChoiceBoxInformation(UserUtils.getHairTwistType(), twistList);
            choiceDialog.setContentText(resourceBundle.getString("mainApplicationWindow.editChoiceBox.hairPorosity"));
            Optional<String> result = choiceDialog.showAndWait();
            if(result.isPresent()){
                UserUtils.upDateUserTwistType(result.get(),entityManager);
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
        }else if (chosenOption.equals(password)) {
            textInputDialog = (TextInputDialog) DialogsUtils.editTextDialog(chosenOption);
            textInputDialog.setContentText(resourceBundle.getString("mainApplicationWindow.editChoiceBox.password"));
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserPassword(result.get(), entityManager);
            }
        }else if (chosenOption.equals(name)) {
            textInputDialog = (TextInputDialog) DialogsUtils.editTextDialog(chosenOption);
            textInputDialog.setContentText(resourceBundle.getString("mainApplicationWindow.editChoiceBox.name"));
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserName(result.get(), entityManager);
            }
        }else if (chosenOption.equals(lastName)) {
            textInputDialog = (TextInputDialog) DialogsUtils.editTextDialog(chosenOption);
            textInputDialog.setContentText(resourceBundle.getString("mainApplicationWindow.editChoiceBox.lastName"));
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent()) {
                UserUtils.upDateUserLastName(result.get(), entityManager);
            }

        }
    }

    public static void editInformation (String chosenOption){

        if (chosenOption.equals(hairPorosity) || chosenOption.equals(hairTwistType)) {
            choiceDialogForEditInformation(chosenOption);
        } else {
            textInputDialogForEditInformation(chosenOption);
        }
    }

    private static void initRoutineRoot(){
        washNames.addAll(wash1,wash2,wash3,wash4,wash5,wash6,wash7,wash8,wash9,wash10);
        for(String wash:washNames){
            TreeItem<String> washItem = new TreeItem<>(wash);
            routineRoot.getChildren().add(washItem);
        }

    }

    private static void initProductsRoot(){
        productsNames.addAll(shampoo,conditioner,stylize,oil,halfProducts);
        for(String product:productsNames){
            TreeItem<String> productItem = new TreeItem<>(product);
            productsRoot.getChildren().add(productItem);
        }
    }





























}
