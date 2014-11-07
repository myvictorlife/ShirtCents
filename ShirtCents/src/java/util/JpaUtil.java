package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaUtil {

    private static EntityManagerFactory emf = null;

    public static EntityManager getEntityManager() {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("ShirtCentsPU");
        }
        return emf.createEntityManager();
    }
    
    public static void closeEntityManager(EntityManager manager) {
        try {
            manager.close();
        }catch(Exception ex) {
            System.err.println("Erro: " + ex);
        }
    }
    
}
