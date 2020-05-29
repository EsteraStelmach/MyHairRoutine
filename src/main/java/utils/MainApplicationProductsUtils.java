package utils;

import Controllers.AddProductController;
import dataBase.*;
import dataBase.domain.*;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
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
    private static TreeItem<String> humectantsHalfProducts= new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.humectants"));
    private static TreeItem<String> proteinHalfProducts = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.protein"));
    private static TreeItem<String> emollientsHalfProducts = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.emollients"));

    private static TreeItem<String> allConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.allConditioners"));
    private static TreeItem<String> emollientsConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.emollients"));
    private static TreeItem<String> humectantsConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.humectants"));
    private static TreeItem<String> proteinConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.protein"));
    private static TreeItem<String> coWashConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.coWash"));
    private static TreeItem<String> leaveOnConditionersItem = new TreeItem<>(resourceBundle.getString("mainApplicationUtils.conditionerTreeItem.leaveOn"));

    public static void initProductsRoot() {
        productsRoot.getChildren().clear();
        shampoosItem.getChildren().clear();
        conditionersItem.getChildren().clear();
        oilItem.getChildren().clear();
        stylizeItem.getChildren().clear();
        halfProductsItem.getChildren().clear();
        initShampoosRoot();
        initConditionersRoot();
        initOilsRoot();
        initStylizeRoot();
        initHalfProductsRoot();
        productsRoot.getChildren().addAll(shampoosItem,conditionersItem,stylizeItem,oilItem,halfProductsItem );

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
        for(Stylize stylize: StylizeUtils.getAllStylize(entityManager)){
            allStylize.getChildren().add(new TreeItem<>(stylize.getName()));
        }
        for(Stylize stylize :StylizeUtils.getGelStylize(entityManager)){
            gelStylize.getChildren().add(new TreeItem<>(stylize.getName()));
        }
        for(Stylize stylize :StylizeUtils.getCreamStylize(entityManager)){
            creamStylize.getChildren().add(new TreeItem<>(stylize.getName()));
        }
        for(Stylize stylize :StylizeUtils.getFoamStylize(entityManager)){
            foamStylize.getChildren().add(new TreeItem<>(stylize.getName()));
        }
        stylizeItem.getChildren().addAll(allStylize,gelStylize,creamStylize,foamStylize);


    }

    private static void initHalfProductsRoot(){
        for(HalfProducts halfProducts: HalfProductsUtils.getHumectantsHafProducts(entityManager)){
            humectantsHalfProducts.getChildren().add(new TreeItem<>(halfProducts.getName()));
        }
        for(HalfProducts halfProducts: HalfProductsUtils.getProteinHafProducts(entityManager)){
            proteinHalfProducts.getChildren().add(new TreeItem<>(halfProducts.getName()));
        }
        for(HalfProducts halfProducts: HalfProductsUtils.getEmollientsHafProducts(entityManager)){
            emollientsHalfProducts.getChildren().add(new TreeItem<>(halfProducts.getName()));
        }
        halfProductsItem.getChildren().addAll(humectantsHalfProducts,proteinHalfProducts,emollientsHalfProducts);
    }

    private static void initOilsRoot(){
        for(Oils oil:OilsUtils.getAllOils(entityManager)){
            oilItem.getChildren().add(new TreeItem<>(oil.getName()));
        }

    }

    public static String takeNotes(TreeItem<String> newValue) {
        String notes = null;
        for (Shampoos shampoo : ShampoosUtils.getAllShampoos(entityManager)) {
            if ((newValue.getValue()).equals(shampoo.getName())) {
                notes = shampoo.getNotes();
            }
        }
        return notes;
    }



    private static void initConditionersRoot() {
        for (Conditioners conditioner : ConditionersUtils.getAllConditioners(entityManager)){
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
        conditionersItem.getChildren().addAll(allConditionersItem,emollientsConditionersItem,humectantsConditionersItem,proteinConditionersItem,
                coWashConditionersItem,leaveOnConditionersItem);

    }

    public static void showAddProductWindow(){
        Scene addProductScene = new Scene(fxmlUtils.fxmlLoader("/fxml/AddProduct.fxml"));
        Stage stage = new Stage();
        stage.setScene(addProductScene);
        stage.show();
    }



}



