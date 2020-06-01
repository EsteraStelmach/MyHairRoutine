package Controllers;

import dataBase.ShampoosUtils;
import dataBase.StylizeUtils;
import dataBase.UserUtils;
import dataBase.domain.User;
import Properties.MainApplicationWindowProperties;
import dataBase.domain.UserHairRoutine;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.EntityManagerUtils;
import utils.DialogsUtils;
import utils.MainApplicationProductsUtils;
import utils.MainApplicationUtils;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.*;

public class MainApplicationWindowController {

    @FXML
    private Button removeProductButton;
    @FXML
    private TextArea notesTextArea;
    // @FXML
    // private TreeView<String> treeViewMyRoutine;
    @FXML
    private ComboBox<String> editComboBox;
    @FXML
    private Label porosityLabel;
    @FXML
    private Label twistTypeLabel;
    @FXML
    private TreeView<String> productsTreeView;


    @FXML
    public void initialize() {
        porosityLabel.setText(UserUtils.getHairPorosity());
        twistTypeLabel.setText(UserUtils.getHairTwistType());
        logEditInformationToChoiceBox();
        /// this.treeViewMyRoutine.setRoot(MainApplicationUtils.getRoutineRoot());
        productsTreeView.setRoot(MainApplicationProductsUtils.getProductsRoot());
        addListerToEditComboBox();
        addListenerToProductsTreeView();
        addListenerToNotesTextArea();
        removeProductButton.disableProperty().bindBidirectional(MainApplicationProductsUtils.removeProductButtonPropertyProperty());


    }

    private void addListerToEditComboBox() {
        editComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            MainApplicationUtils.editInformation(newValue);
            porosityLabel.setText(UserUtils.getHairPorosity());
            twistTypeLabel.setText(UserUtils.getHairTwistType());
        });

    }

    private void logEditInformationToChoiceBox() {
        editComboBox.getItems().addAll(MainApplicationUtils.getInformationToEdit());
    }

    public void logOutToLoginWindow(){
        DialogsUtils.logOutAlert();
    }

    private void addListenerToProductsTreeView() {
        productsTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            notesTextArea.setText(MainApplicationProductsUtils.takeNotes(newValue));

        });
    }

    private void addListenerToNotesTextArea(){
        notesTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            MainApplicationProductsUtils.changeNotes(newValue);
        });
    }

    public void addNewProduct() {
        MainApplicationProductsUtils.showAddProductWindow();
    }

    public void removeSelectedProduct() {
        MainApplicationProductsUtils.removeProduct();
        productsTreeView.setRoot(MainApplicationProductsUtils.getProductsRoot());
    }
}







