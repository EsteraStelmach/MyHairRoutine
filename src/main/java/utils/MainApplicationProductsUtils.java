package utils;

import Controllers.AddProductController;
import dataBase.*;
import dataBase.domain.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainApplicationProductsUtils {


    private static TreeItem<String> productsRoot = new TreeItem<>();

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundles.messages");
    private static EntityManager entityManager = EntityManagerUtils.getEntityManager();
    private static AddProductController addProductController = new AddProductController();


    private static TreeItem<String> shampoosItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.productsTreeItem.shampoo"));
    private static TreeItem<String> conditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.productsTreeItem.conditioner"));
    private static TreeItem<String> stylizeItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.productsTreeItem.stylize"));
    private static TreeItem<String> oilItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.productsTreeItem.oil"));
    private static TreeItem<String> halfProductsItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.productsTreeItem.halfProducts"));
    private static TreeItem<String> strongLevelItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.shampooTreeItem.hard"));
    private static TreeItem<String> mildLevelItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.shampooTreeItem.mild"));
    private static TreeItem<String> allStylize = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.stylizeTreeItem.allStylize"));
    private static TreeItem<String> gelStylize = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.stylizeTreeItem.gelStylize"));
    private static TreeItem<String> creamStylize = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.stylizeTreeItem.creamStylize"));
    private static TreeItem<String> foamStylize = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.stylizeTreeItem.foamStylize"));
    private static TreeItem<String> humectantsHalfProducts = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.humectants"));
    private static TreeItem<String> proteinHalfProducts = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.protein"));
    private static TreeItem<String> emollientsHalfProducts = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.emollients"));

    private static TreeItem<String> allConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.allConditioners"));
    private static TreeItem<String> emollientsConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.emollients"));
    private static TreeItem<String> humectantsConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.humectants"));
    private static TreeItem<String> proteinConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.protein"));
    private static TreeItem<String> coWashConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.coWash"));
    private static TreeItem<String> leaveOnConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.leaveOn"));

    private static BooleanProperty removeProductButtonProperty = new SimpleBooleanProperty(true);

    private static String helpString_Category;
    private static int helpInt_id;
    private static String notes = " ";
    private static String helpString_productName;
    private static Object helpObject;


    public static BooleanProperty removeProductButtonPropertyProperty() {
        return removeProductButtonProperty;
    }

    public static void initProductsRoot() {
        productsRoot.getChildren().clear();
        shampoosItem.getChildren().clear();
        conditionersItem.getChildren().clear();
        oilItem.getChildren().clear();
        stylizeItem.getChildren().clear();
        halfProductsItem.getChildren().clear();
        strongLevelItem.getChildren().clear();
        mildLevelItem.getChildren().clear();
        allStylize.getChildren().clear();
        gelStylize.getChildren().clear();
        foamStylize.getChildren().clear();
        creamStylize.getChildren().clear();
        humectantsHalfProducts.getChildren().clear();
        emollientsHalfProducts.getChildren().clear();
        proteinHalfProducts.getChildren().clear();
        allConditionersItem.getChildren().clear();
        humectantsConditionersItem.getChildren().clear();
        emollientsConditionersItem.getChildren().clear();
        proteinConditionersItem.getChildren().clear();
        coWashConditionersItem.getChildren().clear();
        leaveOnConditionersItem.getChildren().clear();
        initShampoosRoot();
        initConditionersRoot();
        initOilsRoot();
        initStylizeRoot();
        initHalfProductsRoot();
        productsRoot.getChildren().addAll(shampoosItem, conditionersItem, stylizeItem, oilItem, halfProductsItem);

    }

    public static TreeItem<String> getProductsRoot() {
        initProductsRoot();
        return productsRoot;
    }

    private static void initShampoosRoot() {
        for (Shampoos shampoo : ShampoosUtils.getHardShampoos(entityManager)) {
            strongLevelItem.getChildren().add(new TreeItem<>(shampoo.getName()));
        }
        for (Shampoos shampoo : ShampoosUtils.getMildShampoos(entityManager)) {
            mildLevelItem.getChildren().add(new TreeItem<>(shampoo.getName()));
        }
        shampoosItem.getChildren().addAll(strongLevelItem, mildLevelItem);

    }

    private static void initStylizeRoot() {
        for (Stylize stylize : StylizeUtils.getAllStylize(entityManager)) {
            allStylize.getChildren().add(new TreeItem<>(stylize.getName()));
        }
        for (Stylize stylize : StylizeUtils.getGelStylize(entityManager)) {
            gelStylize.getChildren().add(new TreeItem<>(stylize.getName()));
        }
        for (Stylize stylize : StylizeUtils.getCreamStylize(entityManager)) {
            creamStylize.getChildren().add(new TreeItem<>(stylize.getName()));
        }
        for (Stylize stylize : StylizeUtils.getFoamStylize(entityManager)) {
            foamStylize.getChildren().add(new TreeItem<>(stylize.getName()));
        }
        stylizeItem.getChildren().addAll(allStylize, gelStylize, creamStylize, foamStylize);


    }

    private static void initHalfProductsRoot() {
        for (HalfProducts halfProducts : HalfProductsUtils.getHumectantsHafProducts(entityManager)) {
            humectantsHalfProducts.getChildren().add(new TreeItem<>(halfProducts.getName()));
        }
        for (HalfProducts halfProducts : HalfProductsUtils.getProteinHafProducts(entityManager)) {
            proteinHalfProducts.getChildren().add(new TreeItem<>(halfProducts.getName()));
        }
        for (HalfProducts halfProducts : HalfProductsUtils.getEmollientsHafProducts(entityManager)) {
            emollientsHalfProducts.getChildren().add(new TreeItem<>(halfProducts.getName()));
        }
        halfProductsItem.getChildren().addAll(humectantsHalfProducts, proteinHalfProducts, emollientsHalfProducts);
    }

    private static void initOilsRoot() {
        for (Oils oil : OilsUtils.getAllOils(entityManager)) {
            oilItem.getChildren().add(new TreeItem<>(oil.getName()));
        }

    }


    private static void initConditionersRoot() {
        for (Conditioners conditioner : ConditionersUtils.getAllConditioners(entityManager)) {
            allConditionersItem.getChildren().add(new TreeItem<>(conditioner.getName()));
        }
        for (Conditioners conditioner : ConditionersUtils.getEmollientsConditioners(entityManager)) {
            emollientsConditionersItem.getChildren().add(new TreeItem<>(conditioner.getName()));
        }

        for (Conditioners conditioner : ConditionersUtils.getHumectantsConditioners(entityManager)) {
            humectantsConditionersItem.getChildren().add(new TreeItem<>(conditioner.getName()));
        }
        for (Conditioners conditioner : ConditionersUtils.getProteinConditioners(entityManager)) {
            proteinConditionersItem.getChildren().add(new TreeItem<>(conditioner.getName()));

        }
        for (Conditioners conditioner : ConditionersUtils.getCoWashConditioners(entityManager)) {
            coWashConditionersItem.getChildren().add(new TreeItem<>(conditioner.getName()));

        }
        for (Conditioners conditioner : ConditionersUtils.getLeaveOnConditioners(entityManager)) {
            leaveOnConditionersItem.getChildren().add(new TreeItem<>(conditioner.getName()));

        }
        conditionersItem.getChildren().addAll(allConditionersItem, emollientsConditionersItem, humectantsConditionersItem, proteinConditionersItem,
                coWashConditionersItem, leaveOnConditionersItem);

    }

    public static void showAddProductWindow() {
        Scene addProductScene = new Scene(fxmlUtils.fxmlLoader("/fxml/AddProduct.fxml"));
        Stage stage = new Stage();
        stage.setScene(addProductScene);
        stage.show();
    }

    public static String takeNotes(TreeItem<String> newValue) {
        if (!searchProduct(newValue)) {
            helpString_Category = " ";
            removeProductButtonProperty.setValue(true);
            return notes = " ";

        } else {
            removeProductButtonProperty.setValue(false);
            return notes;
        }
    }

    private static boolean searchProduct(TreeItem<String> newValue) {
        boolean isProductClicked = false;
        for (Shampoos shampoo : ShampoosUtils.getAllShampoos(entityManager)) {
            if ((newValue.getValue()).equals(shampoo.getName())) {
                notes = shampoo.getNotes();
                helpString_Category = "S";
                helpInt_id = shampoo.getIdShampoo();
                helpString_productName = shampoo.getName();
                helpObject = shampoo;
                isProductClicked = true;
            }
        }
        for (Conditioners conditioner : ConditionersUtils.getAllConditioners(entityManager)) {
            if (newValue.getValue().equals(conditioner.getName())) {
                notes = conditioner.getNotes();
                helpString_Category = "C";
                helpInt_id = conditioner.getId();
                helpString_productName = conditioner.getName();
                helpObject = conditioner;
                isProductClicked = true;
            }
        }
        for (HalfProducts halfProducts : HalfProductsUtils.getAllHafProducts(entityManager)) {
            if (newValue.getValue().equals(halfProducts.getName())) {
                notes = halfProducts.getNotes();
                helpString_Category = "H";
                helpInt_id = halfProducts.getId();
                helpString_productName = halfProducts.getName();
                helpObject = halfProducts;
                isProductClicked = true;
            }
        }
        for (Oils oil : OilsUtils.getAllOils(entityManager)) {
            if (newValue.getValue().equals(oil.getName())) {
                notes = oil.getNotes();
                helpString_Category = "O";
                helpInt_id = oil.getId();
                helpString_productName = oil.getName();
                helpObject = oil;
                isProductClicked = true;
            }
        }
        for (Stylize stylize : StylizeUtils.getAllStylize(entityManager)) {
            if (newValue.getValue().equals(stylize.getName())) {
                notes = stylize.getNotes();
                helpString_Category = "ST";
                helpInt_id = stylize.getId();
                helpString_productName = stylize.getName();
                helpObject = stylize;
                isProductClicked = true;
            }
        }
        return isProductClicked;
    }

    public static void changeNotes(String newValue) {
        entityManager.getTransaction().begin();
        if (helpString_Category.equals("S")) {
            Shampoos shampoo = entityManager.find(Shampoos.class, helpInt_id);
            shampoo.setNotes(newValue);
        } else if (helpString_Category.equals("C")) {
            Conditioners conditioner = entityManager.find(Conditioners.class, helpInt_id);
            conditioner.setNotes(newValue);
        } else if (helpString_Category.equals("ST")) {
            Stylize stylize = entityManager.find(Stylize.class, helpInt_id);
            stylize.setNotes(newValue);
        } else if (helpString_Category.equals("O")) {
            Oils oil = entityManager.find(Oils.class, helpInt_id);
            oil.setNotes(newValue);
        } else if (helpString_Category.equals("H")) {
            HalfProducts halfProduct = entityManager.find(HalfProducts.class, helpInt_id);
            halfProduct.setNotes(newValue);
        }
        entityManager.getTransaction().commit();

    }

    public static void removeProduct() {
        Alert confirmationAlert = DialogsUtils.removeProductAlert(helpString_productName);
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        Products helpProduct = null;
        if (result.get() == ButtonType.OK) {
            List<Products> allUserProducts = ProductsUtils.getUserProducts(entityManager);
            for (Products product : allUserProducts) {
                if (product.getIdProduct() == helpInt_id) {
                    helpProduct = product;
                }
            }
            entityManager.getTransaction().begin();
            entityManager.remove(helpObject);
            entityManager.remove(helpProduct);
            entityManager.getTransaction().commit();
        }
    }
}
 