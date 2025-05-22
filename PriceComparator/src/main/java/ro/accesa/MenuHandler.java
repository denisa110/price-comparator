package ro.accesa;

import jakarta.persistence.EntityManager;
import ro.accesa.repository.DiscountHistoryRepository;
import ro.accesa.service.DiscountService;

import java.util.Scanner;

public class MenuHandler {
    private final Scanner scanner;
    private final DiscountService discountService;

    public MenuHandler(EntityManager em) {
        this.scanner = new Scanner(System.in);
        DiscountHistoryRepository discountRepository = DiscountHistoryRepository.getInstance(em);
        this.discountService = new DiscountService(discountRepository);
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
}
