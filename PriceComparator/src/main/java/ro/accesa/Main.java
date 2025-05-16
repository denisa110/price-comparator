package ro.accesa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ro.accesa.service.SQLScriptRunner;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    private static final Logger logger = Logger.getLogger("Main");

    public static void main(String[] args) {

        logger.log(Level.INFO, "The application started!");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");
        EntityManager em = emf.createEntityManager();

        SQLScriptRunner scriptRunner = new SQLScriptRunner(em);
        scriptRunner.runAllScripts();

        em.close();
        emf.close();
    }
}