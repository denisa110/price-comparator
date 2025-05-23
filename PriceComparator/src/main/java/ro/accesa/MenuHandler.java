package ro.accesa;

import jakarta.persistence.EntityManager;
import ro.accesa.repository.DiscountHistoryRepository;
import ro.accesa.repository.PriceAlertRepository;
import ro.accesa.repository.PriceHistoryRepository;
import ro.accesa.repository.ProductRepository;
import ro.accesa.service.DiscountService;
import ro.accesa.service.PriceAlertService;

import java.util.Scanner;

public class MenuHandler {
    private final Scanner scanner;
    private final DiscountService discountService;
    private final PriceAlertService priceAlertService;

    public MenuHandler(EntityManager em) {
        this.scanner = new Scanner(System.in);
        DiscountHistoryRepository discountRepository = DiscountHistoryRepository.getInstance(em);
        this.discountService = new DiscountService(discountRepository);
        PriceAlertRepository priceAlertRepository = new PriceAlertRepository(em);
        ProductRepository productRepository = new ProductRepository(em);
        PriceHistoryRepository priceHistoryRepository = new PriceHistoryRepository(em);
        this.priceAlertService = new PriceAlertService(priceAlertRepository, productRepository, priceHistoryRepository);
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
                    discountService.displayDiscountsForDate();
                    break;

                case 2:
                    handleNewDiscountsInput();
                    break;

                case 3:
                    createPriceAlert();
                    break;

                case 4:
                    System.out.print("Introduceți numele produsului: ");
                    String productName = scanner.nextLine();
                    priceAlertService.checkTriggeredAlerts(productName);
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

        priceAlertService.createPriceAlert(productName, targetPrice);
    }


}
