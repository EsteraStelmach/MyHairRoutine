package dataBase;

import Controllers.AddProductController;
import dataBase.domain.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsUtils {


    private static int userId = UserUtils.getId();
    private static List<Products> allUserProducts = new ArrayList<>();
    private static String category;
    private static String notesNewProduct;
    private static boolean leaveOnNewProduct;
    private static boolean coWashNewProduct;
    private static String nameNewProduct;
    private static String consistencyNewProduct;
    private static String strongLevelNewProduct;
    private static String typeNewProduct;
    private static Shampoos shampoo;
    private static Conditioners conditioner;
    private static Stylize stylize;
    private static Oils oil;
    private static HalfProducts halfProduct;

    private static ResourceBundle bundle = ResourceBundle.getBundle("Bundles.messages");



    public static List<Products> getUserProducts(EntityManager entityManager){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT p FROM Products p WHERE p.idUser= :idUser", Products.class).
                setParameter("idUser",userId);
        allUserProducts = query.getResultList();
        entityManager.getTransaction().commit();
        return  allUserProducts;
    }

    public static void addNewProduct(EntityManager entityManager){
        entityManager.getTransaction().begin();
        Products product = new Products();
        category = AddProductController.getCategoryNewProduct();
        product.setCategory(category);
        product.setIdUser(userId);
        entityManager.persist(product);
        if(category.equals("S")){
            makeNewShampoo();
            entityManager.persist(shampoo);
            product.setIdProduct(shampoo.getIdShampoo());
        }else if(category.equals("C")){
            makeNewConditioner();
            entityManager.persist(conditioner);
            product.setIdProduct(conditioner.getId());
        }else if(category.equals("ST")){
            makeNewStylize();
            entityManager.persist(stylize);
            product.setIdProduct(stylize.getId());
        }else if(category.equals("O")){
            makeNewOil();
            entityManager.persist(oil);
            product.setIdProduct(oil.getId());
        }else if(category.equals("H")){
            makeNewHalfProduct();
            entityManager.persist(halfProduct);
            product.setIdProduct(halfProduct.getId());
        }
        entityManager.getTransaction().commit();

    }

    private static void makeNewHalfProduct(){
        halfProduct = new HalfProducts();
        halfProduct.setName(AddProductController.getNameNewProduct());
        halfProduct.setType(convertTypeToSymbol(AddProductController.getTypeNewProduct()));
        halfProduct.setNotes(AddProductController.getNotesNewProduct());
        if(halfProduct.getType() == null){
            halfProduct.setType("0");
        }
        if(halfProduct.getNotes() == null){
            halfProduct.setNotes(" ");
        }
    }

    private static void makeNewOil(){
        oil =  new Oils();
        oil.setName(AddProductController.getNameNewProduct());
        oil.setNotes(AddProductController.getNotesNewProduct());
        if(oil.getNotes() == null){
            oil.setNotes(" ");
        }
    }

    private static void makeNewStylize(){
        stylize = new Stylize();
        stylize.setName(AddProductController.getNameNewProduct());
        stylize.setConsistency(convertStylizeConsistencyToSymbol(AddProductController.getConsistencyNewProduct()));
        stylize.setType(convertTypeToSymbol(AddProductController.getTypeNewProduct()));
        stylize.setNotes(AddProductController.getNotesNewProduct());
        if(stylize.getType() == null){
            stylize.setType("0");
        }
        if(stylize.getNotes() == null){
            stylize.setNotes(" ");
        }
        if(stylize.getConsistency() == null){
            stylize.setConsistency("0");
        }
    }

    private static void makeNewConditioner(){
        conditioner = new Conditioners();
        conditioner.setName(AddProductController.getNameNewProduct());
        conditioner.setNotes(AddProductController.getNotesNewProduct());
        conditioner.setLeaveOn(convertConditionerLeaveOnToSymbol(AddProductController.isLeaveOnNewProduct()));
        conditioner.setCoWash(convertConditionerCoWashToSymbol(AddProductController.isCoWashNewProduct()));
        conditioner.setType(convertTypeToSymbol(AddProductController.getTypeNewProduct()));
        if(conditioner.getType() == null){
            conditioner.setType("0");
        }
        if(conditioner.getNotes() == null){
            conditioner.setNotes(" ");
        }
    }

    private static void makeNewShampoo(){
        shampoo = new Shampoos();
        shampoo.setStrongLevel(convertShampooStrongLevelToSymbol(AddProductController.getStrongLevelNewProduct()));
        shampoo.setName(AddProductController.getNameNewProduct());
        shampoo.setNotes(AddProductController.getNotesNewProduct());
        if(shampoo.getStrongLevel()== null){
            shampoo.setStrongLevel("0");
        }
        if(shampoo.getNotes() == null){
            shampoo.setNotes(" ");
        }
    }

    private static String convertStylizeConsistencyToSymbol(String consistency){
        if(consistency == null){
            return null;
        }
        String symbol = null;
        if(consistency.equals(bundle.getString("mainApplicationUtils.stylizeTreeItem.gelStylize"))){
            symbol = "G";
        }else if(consistency.equals(bundle.getString("mainApplicationUtils.stylizeTreeItem.creamStylize"))){
            symbol = "C";
        }else if(consistency.equals(bundle.getString("mainApplicationUtils.stylizeTreeItem.foamStylize"))){
            symbol = "F";
        }
        return symbol;
    }


    private static String convertTypeToSymbol(String type){
        if(type == null){
            return null;
        }
        String symbol = null;
        if(type.equals(bundle.getString("mainApplicationUtils.conditionerTreeItem.emollients"))){
            symbol = "E";
        }else if(type.equals(bundle.getString("mainApplicationUtils.conditionerTreeItem.humectants"))){
            symbol = "H";
        }else if(type.equals(bundle.getString("mainApplicationUtils.conditionerTreeItem.protein"))){
            symbol = "P";
        }
        return symbol;
    }

    private static String convertConditionerLeaveOnToSymbol(Boolean leaveOn){
        if(leaveOn == null){
            return null;
        }
        String symbol = null;
        if(leaveOn.booleanValue()){
            symbol = "1";
        }else if (!leaveOn.booleanValue()) {
            symbol = "0";
        }
        return  symbol;
    }
    private static  String convertConditionerCoWashToSymbol(Boolean coWash){
        if(coWash == null){
            return null;
        }
        String symbol = null;
        if(coWash.booleanValue()){
            symbol = "1";
        }else if (!coWash.booleanValue()) {
            symbol = "0";
        }
        return  symbol;
    }

    private static String convertShampooStrongLevelToSymbol(String strongLevel){
        if(strongLevel == null){
            return null;
        }
        String symbol = null;
        if(strongLevel.equals(bundle.getString("mainApplicationUtils.shampooTreeItem.hard"))){
            symbol = "H";
        }else if(strongLevel.equals(bundle.getString("mainApplicationUtils.shampooTreeItem.mild"))){
            symbol = "M";
        }
        return symbol;
    }
































}
