package dataBase;

import dataBase.domain.Conditioners;
import dataBase.domain.Oils;
import dataBase.domain.Products;
import dataBase.domain.Shampoos;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ShampoosUtils {

    private static List<Shampoos> hardShampoos = new ArrayList<>();
    private static List<Shampoos> mildShampoos = new ArrayList<>();
    private static List<Shampoos> allShampoos = new ArrayList<>();


    public static List<Shampoos> getHardShampoos(EntityManager entityManager){
        hardShampoos.clear();
        getAllShampoos(entityManager);
        for(Shampoos shampoo: allShampoos) {
            if (shampoo.getStrongLevel().equals("H")) {
                hardShampoos.add(shampoo);
            }
        }
        return hardShampoos;
    }

    public static List<Shampoos> getMildShampoos(EntityManager entityManager){
        mildShampoos.clear();
        getAllShampoos(entityManager);
        for(Shampoos shampoo: allShampoos){
            if(shampoo.getStrongLevel().equals("M")){
                mildShampoos.add(shampoo);
            }
        }
        return mildShampoos;
    }

    public static List<Shampoos> getAllShampoos(EntityManager entityManager) {
        allShampoos.clear();
        int id;
        for(Products product : ProductsUtils.getUserProducts(entityManager)){
            if(product.getCategory().equals("S")){
                entityManager.getTransaction().begin();
                id = product.getIdProduct();
                allShampoos.add(entityManager.find(Shampoos.class,id));
                entityManager.getTransaction().commit();
            } }
        return allShampoos;
    }
}
