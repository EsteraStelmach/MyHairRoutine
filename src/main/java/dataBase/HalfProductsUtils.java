package dataBase;

import dataBase.domain.Conditioners;
import dataBase.domain.HalfProducts;
import dataBase.domain.Products;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class HalfProductsUtils {

    private static List<HalfProducts>  humectantsHafProducts = new ArrayList<>();
    private static List<HalfProducts>  proteinHafProducts = new ArrayList<>();
    private static List<HalfProducts>  emollientsHafProducts = new ArrayList<>();
    private static List<HalfProducts>  allHafProducts = new ArrayList<>();

    public static List<HalfProducts> getHumectantsHafProducts(EntityManager entityManager) {
        humectantsHafProducts.clear();
        getAllHafProducts(entityManager);
        for(HalfProducts halfProducts:allHafProducts){
            if(halfProducts.getType().equals("H")){
                humectantsHafProducts.add(halfProducts);
            }
        }
        return humectantsHafProducts;
    }

    public static List<HalfProducts> getProteinHafProducts(EntityManager entityManager) {
        proteinHafProducts.clear();
        getAllHafProducts(entityManager);
        for(HalfProducts halfProducts:allHafProducts){
            if(halfProducts.getType().equals("P")){
                proteinHafProducts.add(halfProducts);
            }
        }
        return proteinHafProducts;
    }

    public static List<HalfProducts> getEmollientsHafProducts(EntityManager entityManager) {
        emollientsHafProducts.clear();
        getAllHafProducts(entityManager);
        for(HalfProducts halfProducts:allHafProducts){
            if(halfProducts.getType().equals("E")){
                emollientsHafProducts.add(halfProducts);
            }
        }
        return emollientsHafProducts;
    }



    public static List<HalfProducts> getAllHafProducts(EntityManager entityManager) {
        allHafProducts.clear();
        int id;
        for(Products product : ProductsUtils.getUserProducts(entityManager)){
            if(product.getCategory().equals("H")){
                entityManager.getTransaction().begin();
                id = product.getIdProduct();
                allHafProducts.add(entityManager.find(HalfProducts.class,id));
                entityManager.getTransaction().commit();
            } }
        return allHafProducts;

    }
}
