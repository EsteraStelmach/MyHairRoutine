package Controllers;

import dataBase.ShampoosUtils;
import dataBase.StylizeUtils;
import dataBase.UserUtils;
import dataBase.WashRoutineUtils;
import dataBase.domain.User;
import Properties.MainApplicationWindowProperties;
import dataBase.domain.WashRoutine;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import utils.*;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.*;

public class MainApplicationWindowController {

    @FXML
    private TableView<WashRoutine> washTableView;
    @FXML
    private TableColumn<WashRoutine,String> washNumberColumnMyRoutineTable;
    @FXML
    private TableColumn<WashRoutine,String> washTypeColumnMyRoutineTable;
    @FXML
    private TableColumn<WashRoutine,String> washingDescriptionColumnMyRoutineTable;
    @FXML
    private Button removeProductButton;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private ComboBox<String> editComboBox;
    @FXML
    private Label porosityLabel;
    @FXML
    private Label twistTypeLabel;
    @FXML
    private TreeView<String> productsTreeView;
    @FXML
    private Button removeRoutineWashButton;

    private EntityManager entityManager = EntityManagerUtils.getEntityManager();

    public TableColumn<WashRoutine, String> getWashNumberColumnMyRoutineTable() {
        return washNumberColumnMyRoutineTable;
    }

    public TableColumn<WashRoutine, String> getWashTypeColumnMyRoutineTable() {
        return washTypeColumnMyRoutineTable;
    }

    public TableColumn<WashRoutine, String> getWashingDescriptionColumnMyRoutineTable() {
        return washingDescriptionColumnMyRoutineTable;
    }

    @FXML
    public void initialize() {
        porosityLabel.setText(UserUtils.getHairPorosity());
        twistTypeLabel.setText(UserUtils.getHairTwistType());
        logEditInformationToChoiceBox();
        productsTreeView.setRoot(MainApplicationProductsUtils.getProductsRoot());
        addListerToEditComboBox();
        addListenerToProductsTreeView();
        addListenerToNotesTextArea();
        removeProductButton.disableProperty().bindBidirectional(MainApplicationProductsUtils.removeProductButtonPropertyProperty());
        loadInformationToWashRoutineTable();
        removeRoutineWashButton.disableProperty().bindBidirectional(removeRoutineWashDisableProperty());

    }

    private BooleanProperty removeRoutineWashDisableProperty(){//zmienic pozniej jak zrobie edit
        BooleanProperty removeRoutineWashProperty = new SimpleBooleanProperty(true);
        if(washTableView.getSelectionModel().getSelectedItem()!=null){
            removeRoutineWashProperty.setValue(false);
        }
        return removeRoutineWashProperty;
    }



    private void loadInformationToWashRoutineTable(){
        washTableView.setEditable(true);
        washNumberColumnMyRoutineTable.setCellValueFactory(new PropertyValueFactory("numberWash"));
        washTypeColumnMyRoutineTable.setCellValueFactory(new PropertyValueFactory("washType"));
        washingDescriptionColumnMyRoutineTable.setCellValueFactory(new PropertyValueFactory("washingDescription"));
        MainApplicationMyWashRoutineUtils.makeColumnMyRoutineTable(washingDescriptionColumnMyRoutineTable);
        MainApplicationMyWashRoutineUtils.makeColumnMyRoutineTable(washTypeColumnMyRoutineTable);
        MainApplicationMyWashRoutineUtils.makeColumnMyRoutineTable(washNumberColumnMyRoutineTable);

        washTableView.setItems(MainApplicationMyWashRoutineUtils.getWashRoutinesObservableList());
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

    public void logOutToLoginWindow() throws IOException {
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

    public void addWashRoutine() {
        MainApplicationMyWashRoutineUtils.addWashRoutine(entityManager);
        loadInformationToWashRoutineTable();

    }

    public void removeSelectedWash() {
        WashRoutine washRoutine = washTableView.getSelectionModel().getSelectedItem();
        UserUtils.removeUserWashRoutine(washRoutine,entityManager);
        loadInformationToWashRoutineTable();

    }


    public void editWashingDescription(TableColumn.CellEditEvent<WashRoutine, String> washRoutineStringCellEditEvent) {
        WashRoutine washRoutine = washTableView.getSelectionModel().getSelectedItem();
        //washRoutine.setWashingDescription(washRoutineStringCellEditEvent.getNewValue());
    }
}







