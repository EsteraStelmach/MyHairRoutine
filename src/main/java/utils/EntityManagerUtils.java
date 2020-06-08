package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerUtils {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyRoutine");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static void closeConnection(){
        entityManager.close();
        entityManagerFactory.close();

    }


}
