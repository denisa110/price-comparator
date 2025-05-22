package ro.accesa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ro.accesa.entity.Product;
import ro.accesa.repository.ProductRepository;
import ro.accesa.service.SQLScriptRunner;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        logger.log(Level.INFO, "The application started!");

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try  {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();

            ProductRepository productRepo = ProductRepository.getInstance(em);
            Product product = productRepo.findByName("banane").orElse(null);

            if (product == null) {
                SQLScriptRunner scriptRunner = new SQLScriptRunner(em);
                scriptRunner.runAllScripts();
            }

            new MenuHandler(em).start();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Application error :( ");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}