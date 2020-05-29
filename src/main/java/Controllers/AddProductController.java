package Controllers;

import com.mysql.cj.util.StringUtils;
import dataBase.ProductsUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.EntityManagerUtils;
import utils.MainApplicationProductsUtils;

import javax.persistence.EntityManager;
import java.util.ResourceBundle;

public class AddProductController {

    @FXML
    private Button addProductButton;
    private ResourceBundle bundle = ResourceBundle.getBundle("Bundles.messages");

    private EntityManager entityManager = EntityManagerUtils.getEntityManager();


    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private CheckBox leaveOnCheckBox;
    @FXML
    private CheckBox coWashCheckBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private ComboBox <String> consistencyComboBox;
    @FXML
    private ComboBox <String> strongLevelComboBox;
    @FXML
    private ComboBox <String> typeComboBox;

    private static String categoryNewProduct;
    private static String notesNewProduct;
    private static boolean leaveOnNewProduct;
    private static boolean coWashNewProduct;
    private static String nameNewProduct;
    private static String consistencyNewProduct;
    private static String strongLevelNewProduct;
    private static String typeNewProduct;


    private String shampoo = bundle.getString("mainApplicationUtils.productsTreeItem.shampoo");
    private String conditioner = bundle.getString("mainApplicationUtils.productsTreeItem.conditioner");
    private String stylize = bundle.getString("mainApplicationUtils.productsTreeItem.stylize");
    private String oil= bundle.getString("mainApplicationUtils.productsTreeItem.oil");
    private String halfProducts= bundle.getString("mainApplicationUtils.productsTreeItem.halfProducts");


    private ObservableList<String> category = FXCollections.observableArrayList();
    private ObservableList<String> consistency = FXCollections.observableArrayList();
    private ObservableList<String> strongLevel = FXCollections.observableArrayList();
    private ObservableList<String> type = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        logCategoryComboBox();
        logConsistencyComboBox();
        logStrongLevel();
        logTypeComboBox();
       makeAllNotVisible();
        categoryComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                makeAllNotVisible();
                categoryListener(newValue);
            }
        });


    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public static String getCategoryNewProduct() {
        return categoryNewProduct;
    }

    public static String getNotesNewProduct() {
        return notesNewProduct;
    }

    public static boolean isLeaveOnNewProduct() {
        return leaveOnNewProduct;
    }

    public static boolean isCoWashNewProduct() {
        return coWashNewProduct;
    }

    public static String getNameNewProduct() {
        return nameNewProduct;
    }

    public static String getConsistencyNewProduct() {
        return consistencyNewProduct;
    }

    public static String getStrongLevelNewProduct() {
        return strongLevelNewProduct;
    }

    public static String getTypeNewProduct() {
        return typeNewProduct;
    }

    private void categoryListener(Object newValue){
        if(newValue.equals(shampoo)){
           strongLevelComboBox.visibleProperty().setValue(true);
           categoryNewProduct = "S";
        } else if (newValue.equals(conditioner)) {
            categoryNewProduct = "C";
            typeComboBox.visibleProperty().setValue(true);
            leaveOnCheckBox.visibleProperty().setValue(true);
            coWashCheckBox.visibleProperty().setValue(true);
        }else if (newValue.equals(stylize)){
            categoryNewProduct = "ST";
            consistencyComboBox.visibleProperty().setValue(true);
        }else if (newValue.equals(oil)){
            categoryNewProduct = "O";
        }else if(newValue.equals(halfProducts)){
            typeComboBox.visibleProperty().setValue(true);
            categoryNewProduct = "H";
        }
    }

    private void makeAllNotVisible(){
        coWashCheckBox.visibleProperty().setValue(false);
        leaveOnCheckBox.visibleProperty().setValue(false);
        typeComboBox.visibleProperty().setValue(false);
        strongLevelComboBox.visibleProperty().setValue(false);
        consistencyComboBox.visibleProperty().setValue(false);
    }


    private void logCategoryComboBox(){
        category.addAll(shampoo,conditioner,stylize,oil,halfProducts);
        categoryComboBox.getItems().addAll(category);

    }
    private void logConsistencyComboBox(){
        consistency.addAll(bundle.getString("mainApplicationUtils.stylizeTreeItem.gelStylize"),
                bundle.getString("mainApplicationUtils.stylizeTreeItem.creamStylize"),
                bundle.getString("mainApplicationUtils.stylizeTreeItem.foamStylize"));
        consistencyComboBox.getItems().addAll(consistency);
    }

    private void logStrongLevel(){
        strongLevel.addAll(bundle.getString("mainApplicationUtils.shampooTreeItem.hard"),
                bundle.getString("mainApplicationUtils.shampooTreeItem.mild"));
        strongLevelComboBox.getItems().addAll(strongLevel);
    }

    private void logTypeComboBox(){
        type.addAll(bundle.getString("mainApplicationUtils.conditionerTreeItem.emollients"),
                bundle.getString("mainApplicationUtils.conditionerTreeItem.humectants"),
                bundle.getString("mainApplicationUtils.conditionerTreeItem.protein"));
        typeComboBox.getItems().addAll(type);
    }


    public void addThisProduct() {
        nameNewProduct = nameTextField.getText();
        notesNewProduct = notesTextArea.getText();
        consistencyNewProduct = consistencyComboBox.getValue();
        strongLevelNewProduct = strongLevelComboBox.getValue();
        leaveOnNewProduct = leaveOnCheckBox.isSelected();
        coWashNewProduct = coWashCheckBox.isSelected();
        typeNewProduct = typeComboBox.getValue();
        ProductsUtils.addNewProduct(entityManager);
        addProductButton.getScene().getWindow().hide();

        MainApplicationProductsUtils.initProductsRoot();

    }


























}
