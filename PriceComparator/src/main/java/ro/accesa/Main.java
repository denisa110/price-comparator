package ro.accesa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ro.accesa.repository.DiscountHistoryRepository;
import ro.accesa.service.DiscountService;
import ro.accesa.service.SQLScriptRunner;

import java.util.Scanner;
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

        DiscountHistoryRepository discountRepository = DiscountHistoryRepository.getInstance(em);
        DiscountService discountService = new DiscountService(discountRepository);

        logger.log(Level.INFO, "------PriceComparator started!------");
        logger.log(Level.INFO, "Choose an option: ");
        logger.log(Level.INFO, "1. Best Discounts  ");
        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                discountService.displayDiscountsForDate();
                break;
            default:
                logger.log(Level.SEVERE, "Invalid choice!");
                break;
        }

        em.close();
        emf.close();
    }
}