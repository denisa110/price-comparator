package ro.accesa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ro.accesa.entity.Product;
import ro.accesa.repository.DiscountHistoryRepository;
import ro.accesa.service.DiscountService;
import ro.accesa.service.SQLScriptRunner;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    // ANSI color codes for terminal
    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        logger.log(Level.INFO, "The application started!");

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try  {
            emf = Persistence.createEntityManagerFactory("persistence-unit");
            em = emf.createEntityManager();

            String productName = "banane";
            Product product = em.find(Product.class, productName);

            if (product == null) {
                SQLScriptRunner scriptRunner = new SQLScriptRunner(em);
                scriptRunner.runAllScripts();
            }
            DiscountHistoryRepository discountRepository = DiscountHistoryRepository.getInstance(em);
            DiscountService discountService = new DiscountService(discountRepository);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println();
                System.out.println(BLUE + "╔════════════════════════════════════╗" + RESET);
                System.out.println(BLUE + "║       " + GREEN + "PRICE COMPARATOR" + BLUE + "             ║" + RESET);
                System.out.println(BLUE + "╚════════════════════════════════════╝" + RESET);
                System.out.println();

                // Menu options
                System.out.println("Choose an option:");
                System.out.println(YELLOW + "1." + RESET + " Best Discounts");
                System.out.println(YELLOW + "2." + RESET + " New Discounts");
                System.out.println(RED + "0." + RESET + " Exit Application");
                System.out.println();
                System.out.print("Enter your choice: ");

                switch (getChoise(scanner)) {
                    case 0:
                        System.out.println(GREEN + "\nExiting Price Comparator. Goodbye!" + RESET);
                        em.close();
                        emf.close();
                        return;
                    case 1:
                        discountService.displayDiscountsForDate();
                        break;
                    case 2:
                        System.out.print("Introduceți perioada în ore pentru căutarea reducerilor noi: ");
                        int hours = Integer.parseInt(scanner.nextLine());

                        System.out.print("Doriti vizualizarea doar a discount-urilor active? (Y/N) ");
                        String active = scanner.nextLine().trim();
                        boolean isActive = active.equalsIgnoreCase("Y");
                        discountService.displayNewDiscounts(hours, isActive);
                        break;

                    default:
                        System.out.println(RED + "\nInvalid option. Please try again." + RESET);
                        break;
                }

                // Pause before showing the menu again
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                scanner.nextLine();
            }

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

    private static int getChoise(Scanner scanner) {
        int choice = 0;
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println(RED + "\nInvalid input. Please enter a number." + RESET);
            scanner.nextLine();
        }
        return choice;
    }
}