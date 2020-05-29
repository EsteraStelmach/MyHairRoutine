package dataBase;

import dataBase.domain.HalfProducts;
import dataBase.domain.Oils;
import dataBase.domain.Products;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class OilsUtils {

    private static List<Oils> allOils = new ArrayList<>();

    public static List<Oils> getAllOils(EntityManager entityManager) {
        allOils.clear();
        int id;
        for(Products product : ProductsUtils.getUserProducts(entityManager)){
            if(product.getCategory().equals("O")){
                entityManager.getTransaction().begin();
                id = product.getIdProduct();
                allOils.add(entityManager.find(Oils.class,id));
                entityManager.getTransaction().commit();
            } }
        return allOils;
    }
}
