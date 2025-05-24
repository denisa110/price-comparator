package ro.accesa;

import jakarta.persistence.EntityManager;
import ro.accesa.repository.*;
import ro.accesa.service.*;
import ro.accesa.util.ConsoleUtils;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * MenuHandler manages user interaction through a console-based menu.
 * It orchestrates input collection, service invocation, and output display
 * for discount listings, price alerts, and product recommendations.
 */
public class MenuHandler {
    private final Scanner scanner;
    private final IDiscountService discountService;
    private final IPriceAlertService priceAlertService;

    private final IRecommendationService recommendationService;

    /**
     * Constructs a MenuHandler and initializes all necessary services using the provided EntityManager.
     *
     * @param em the EntityManager used to instantiate repositories and services
     */
    public MenuHandler(EntityManager em) {
        this.scanner = new Scanner(System.in);
        IDiscountHistoryRepository discountRepository = DiscountHistoryRepository.getInstance(em);
        this.discountService = new DiscountService(discountRepository);
        IPriceAlertRepository priceAlertRepository = new PriceAlertRepository(em);
        ProductRepository productRepository = new ProductRepository(em);
        IPriceHistoryRepository priceHistoryRepository = new PriceHistoryRepository(em);
        this.priceAlertService = new PriceAlertService(priceAlertRepository, productRepository, priceHistoryRepository);
        this.recommendationService = new RecommendationService(priceHistoryRepository);
    }

    public void start() {
        while (true) {
            ConsoleUtils.showMenu();
            int choice = ConsoleUtils.getChoice(scanner);

            switch (choice) {
                case 0:
                    ConsoleUtils.printSuccess("\nExiting Price Comparator. Goodbye!");
                    return;

                case 1:
                    displayDiscountsForDate();
                    break;

                case 2:
                    handleNewDiscountsInput();
                    break;

                case 3:
                    createPriceAlert();
                    break;

                case 4:
                    System.out.print("Enter the product name: ");
                    String productName = scanner.nextLine();
                    priceAlertService.checkTriggeredAlerts(productName);
                    break;

                case 5:
                    showBestValueProductsByCategory();
                    break;

                default:
                    ConsoleUtils.printError("Invalid option. Please try again.");
                    break;
            }

            ConsoleUtils.waitForEnter(scanner);
        }
    }

    private void handleNewDiscountsInput() {
        try {
            System.out.print("Enter the period in hours to search for new discounts: ");
            int hours = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Do you want to view only active discounts? (Y/N): ");
            String active = scanner.nextLine().trim();
            boolean isActive = active.equalsIgnoreCase("Y");

            discountService.displayNewDiscounts(hours, isActive);
        } catch (NumberFormatException e) {
            ConsoleUtils.printError("The period entered is not valid. ");
        }
    }

    private void createPriceAlert() {
        System.out.print("Introduceți numele produsului: ");
        String productName = scanner.nextLine().trim();

        System.out.print("Introduceți prețul țintă: ");
        double targetPrice = Double.parseDouble(scanner.nextLine().trim());

        priceAlertService.create(productName, targetPrice);
    }

    private void showBestValueProductsByCategory() {
        System.out.print("Enter the category: ");
        String category = scanner.nextLine().trim();
        this.recommendationService.showBestValueProductsByCategory(category);
    }

    private void displayDiscountsForDate() {
        System.out.print("Enter the date (format YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate targetDate = LocalDate.parse(dateInput);

        System.out.print("Enter retailer (or leave blank for all): ");
        String retailer = scanner.nextLine();

        System.out.print("Enter the number of results you want: ");
        int limit = Integer.parseInt(scanner.nextLine());

        discountService.displayDiscountsForDate(targetDate, retailer, limit);
    }
}
