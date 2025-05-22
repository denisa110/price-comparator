package ro.accesa.service;

import ro.accesa.entity.DiscountHistory;
import ro.accesa.entity.PriceHistory;
import ro.accesa.entity.Product;
import ro.accesa.repository.DiscountHistoryRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class DiscountService {
    private final Scanner scanner;
    private final DiscountHistoryRepository discountRepository;

    public DiscountService(DiscountHistoryRepository discountRepository) {
        this.discountRepository = discountRepository;
        this.scanner=new Scanner(System.in);
    }

    public void displayDiscountsForDate() {
        try {
            System.out.print("Enter the date (format YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            LocalDate targetDate = LocalDate.parse(dateInput);

            System.out.print("Enter retailer (or leave blank for all): ");
            String retailer = scanner.nextLine();

            System.out.print("Enter the number of results you want: ");
            int limit = Integer.parseInt(scanner.nextLine());

            List<DiscountHistory> discounts = discountRepository.getDiscountsForDate(targetDate, retailer, limit);
            if (discounts.isEmpty()) {
                System.out.println("There are no discounts available on the date " + targetDate);
            } else {
                System.out.println("Discounts available on " + targetDate);
                for (DiscountHistory discount : discounts) {
                    PriceHistory priceHistory = discount.getPriceHistory();
                    Product product = priceHistory.getProduct();

                    System.out.printf("- %s | Brand: %s | Retailer: %s | Price: %.2f %s | Discount: %.2f%% | Valid during: %s - %s%n",
                            product.getName(),
                            product.getBrand(),
                            priceHistory.getRetailer() != null ? priceHistory.getRetailer().getName() : "N/A",
                            priceHistory.getPrice(),
                            priceHistory.getCurrency(),
                            discount.getPercentageOfDiscount(),
                            discount.getStartDate(),
                            discount.getEndDate()
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error processing date. Make sure you are using the correct format (YYYY-MM-DD).");
        }
    }

    public void displayNewDiscounts(int hours, boolean isActive) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate fromDate = currentDateTime.minusHours(hours).toLocalDate();

        List<DiscountHistory> discounts = discountRepository.getNewDiscountsSince(fromDate, isActive);

        if (discounts.isEmpty()) {
            System.out.println("There are no new discounts in the last " + hours + " hours.");
            return;
        }

        System.out.println("=== New discounts found in the last " + hours + " hours ===");
        for (DiscountHistory discount : discounts) {
            PriceHistory ph = discount.getPriceHistory();
            Product product = ph.getProduct();

            System.out.printf("- %s | Brand: %s | Price: %.2f %s | Discount: %.2f%% | Added date: %s | Final date: %s%n",
                    product.getName(), product.getBrand(),
                    ph.getPrice(), ph.getCurrency(),
                    discount.getPercentageOfDiscount(),
                    discount.getDiscountCreatedDate().toString(),
                    discount.getEndDate().toString()
            );
        }
    }
}
