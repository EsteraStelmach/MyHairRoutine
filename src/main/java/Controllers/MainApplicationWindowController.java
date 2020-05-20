package Controllers;

import dataBase.UserUtils;
import dataBase.domain.User;
import Properties.MainApplicationWindowProperties;
import dataBase.domain.UserHairRoutine;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.EntityManagerUtils;
import utils.DialogsUtils;
import utils.MainApplicationUtils;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.*;

public class MainApplicationWindowController {


    @FXML
    private TreeView<String> treeViewMyRoutine;
    @FXML
    private ComboBox <String> editComboBox;
    @FXML
    private Label porosityLabel;
    @FXML
    private Label twistTypeLabel;
    @FXML
    private TreeView<String> productsTreeView;


    @FXML
    public void initialize(){
        porosityLabel.setText(UserUtils.getHairPorosity());
        twistTypeLabel.setText(UserUtils.getHairTwistType());
        logEditInformationToChoiceBox();
        this.treeViewMyRoutine.setRoot(MainApplicationUtils.getRoutineRoot());
        this.productsTreeView.setRoot(MainApplicationUtils.getProductsRoot());
        addListerToEditComboBox();
    }

    private void addListerToEditComboBox(){
        editComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                MainApplicationUtils.editInformation(newValue);
            }
        });

    }

    private void logEditInformationToChoiceBox(){
        editComboBox.getItems().addAll(MainApplicationUtils.getInformationToEdit());
    }

    public void logOutToLoginWindow() throws IOException {
        DialogsUtils.logOutAlert();
    }


}






