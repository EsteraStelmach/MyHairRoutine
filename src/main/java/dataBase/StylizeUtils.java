package dataBase;

import dataBase.domain.Products;
import dataBase.domain.Shampoos;
import dataBase.domain.Stylize;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class StylizeUtils {


    private static List<Stylize> gelStylize = new ArrayList<>();
    private static List<Stylize> creamStylize = new ArrayList<>();
    private static List<Stylize> foamStylize = new ArrayList<>();
    private static List<Stylize> allStylize = new ArrayList<>();


    public static List<Stylize> getGelStylize(EntityManager entityManager) {
        gelStylize.clear();
        getAllStylize(entityManager);
        for(Stylize stylize:allStylize){
            if((stylize.getConsistency()).equals("G")){
                gelStylize.add(stylize);
            }
        }
        return gelStylize;
    }

    public static List<Stylize> getCreamStylize(EntityManager entityManager) {
        creamStylize.clear();
        getAllStylize(entityManager);
        for(Stylize stylize:allStylize){
            if((stylize.getConsistency()).equals("C")){
                creamStylize.add(stylize);
            }
        }
        return creamStylize;
    }

    public static List<Stylize> getFoamStylize(EntityManager entityManager) {
        foamStylize.clear();
        getAllStylize(entityManager);
        for(Stylize stylize:allStylize){
            if((stylize.getConsistency()).equals("F")){
                foamStylize.add(stylize);
            }
        }
        return foamStylize;
    }

    public static List<Stylize> getAllStylize(EntityManager entityManager) {
        allStylize.clear();
        int id;
        for(Products product : ProductsUtils.getUserProducts(entityManager)){
            if(product.getCategory().equals("ST")){
                entityManager.getTransaction().begin();
                id = product.getIdProduct();
                allStylize.add(entityManager.find(Stylize.class,id));
                entityManager.getTransaction().commit();
            } }
        return allStylize;
    }





















}
