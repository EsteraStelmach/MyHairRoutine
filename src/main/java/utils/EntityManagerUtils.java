package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerUtils {

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyRoutine");
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }


}
