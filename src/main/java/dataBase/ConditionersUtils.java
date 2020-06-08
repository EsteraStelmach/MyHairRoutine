package dataBase;

import dataBase.domain.Conditioners;
import dataBase.domain.Products;
import dataBase.domain.Shampoos;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ConditionersUtils {

    private static List<Conditioners> emollientsConditioners = new ArrayList<>();
    private static List<Conditioners> humectantsConditioners = new ArrayList<>();
    private static List<Conditioners> proteinConditioners = new ArrayList<>();
    private static List<Conditioners> coWashConditioners = new ArrayList<>();
    private static List<Conditioners> leaveOnConditioners = new ArrayList<>();
    private static List<Conditioners> allConditioners = new ArrayList<>();


    public static List<Conditioners> getEmollientsConditioners(EntityManager entityManager) {
        emollientsConditioners.clear();
        getAllConditioners(entityManager);
        for(Conditioners conditioner: allConditioners){
            if(conditioner.getType().equals("E")){
                emollientsConditioners.add(conditioner);
            }
        }
        return emollientsConditioners;
    }

    public static List<Conditioners> getHumectantsConditioners(EntityManager entityManager) {
        humectantsConditioners.clear();
        getAllConditioners(entityManager);
        for(Conditioners conditioner: allConditioners){
            if(conditioner.getType().equals("H")){
                humectantsConditioners.add(conditioner);
            }
        }
        return humectantsConditioners;
    }

    public static List<Conditioners> getProteinConditioners(EntityManager entityManager) {
        proteinConditioners.clear();
        getAllConditioners(entityManager);
        for(Conditioners conditioner: allConditioners){
            if(conditioner.getType().equals("P")){
                proteinConditioners.add(conditioner);
            }
        }
        return proteinConditioners;
    }

    public static List<Conditioners> getCoWashConditioners(EntityManager entityManager) {
        coWashConditioners.clear();
        getAllConditioners(entityManager);
        for(Conditioners conditioner: allConditioners){
            if(conditioner.getCoWash().equals("1")){
                coWashConditioners.add(conditioner);
            }
        }
        return coWashConditioners;
    }

    public static List<Conditioners> getLeaveOnConditioners(EntityManager entityManager) {
        leaveOnConditioners.clear();
        getAllConditioners(entityManager);
        for(Conditioners conditioner: allConditioners){
            if(conditioner.getLeaveOn().equals("1")){
               leaveOnConditioners.add(conditioner);
            }
        }
        return leaveOnConditioners;
    }

    public static List<Conditioners> getAllConditioners(EntityManager entityManager) {
        allConditioners.clear();
        int id;
        for(Products product : ProductsUtils.getUserProducts(entityManager)){
            if(product.getCategory().equals("C")){
                entityManager.getTransaction().begin();
                id = product.getIdProduct();
                allConditioners.add(entityManager.find(Conditioners.class,id));
                entityManager.getTransaction().commit();
            } }
        return allConditioners;
    }
}
