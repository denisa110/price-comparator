package ro.accesa.service;

import ro.accesa.dto.AlertDTO;
import ro.accesa.entity.PriceAlert;
import ro.accesa.entity.PriceHistory;
import ro.accesa.entity.Product;
import ro.accesa.repository.PriceAlertRepository;
import ro.accesa.repository.PriceHistoryRepository;
import ro.accesa.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PriceAlertService implements IPriceAlertService {

    private final PriceAlertRepository priceAlertRepository;
    private final ProductRepository productRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    public PriceAlertService(PriceAlertRepository priceAlertRepository, ProductRepository productRepository, PriceHistoryRepository priceHistoryRepository) {
        this.priceAlertRepository = priceAlertRepository;
        this.productRepository = productRepository;
        this.priceHistoryRepository = priceHistoryRepository;
    }

    /**
     * Utility method that compares the latest product prices with all stored alerts
     * and returns a list of those that have been triggered.
     *
     * @param latestProductPrices recent price history entries
     * @param alerts all configured price alerts
     * @return a list of {@link AlertDTO} representing the triggered alerts
     */
    private static List<AlertDTO> getTriggeredAlerts(List<PriceHistory> latestProductPrices,
                                                     List<PriceAlert> alerts) {
        List<AlertDTO> triggered = new ArrayList<>();

        for (PriceHistory productPriceHistory : latestProductPrices) {
            for (PriceAlert alert : alerts) {
                if (productPriceHistory.getProduct().getId().equals(alert.getProduct().getId()) &&
                        productPriceHistory.getPrice() <= alert.getTargetPrice()) {
                    triggered.add(new AlertDTO(
                            productPriceHistory.getProduct().getName(),
                            alert.getTargetPrice(),
                            alert.getCreatedDate(),
                            productPriceHistory.getPrice(),
                            productPriceHistory.getCurrency(),
                            productPriceHistory.getDatePrice(),
                            productPriceHistory.getRetailer().getName()
                    ));
                }
            }
        }
        return triggered;
    }

    @Override
    public void create(String productName, Double targetPrice) {
        if (productName == null) {
            System.out.println("The product was not found.");
            return;
        }

        Product product = productRepository.findByName(productName);

        if (product == null) {
            System.out.println("The product was not found.");
            return;
        }

        PriceAlert alert = new PriceAlert();
        alert.setProduct(product);
        alert.setTargetPrice(targetPrice);
        alert.setCreatedDate(LocalDateTime.now());

        priceAlertRepository.save(alert);

        System.out.println("Alert successfully created for " + product.getName() +
                " at a price below " + targetPrice + " RON.");
    }

    @Override
    public void checkTriggeredAlerts(String productName) {
        List<PriceHistory> latestProductPrices = priceHistoryRepository.findLatestPriceByProductName(productName); //return: 4 - 3
        List<PriceAlert> alerts = priceAlertRepository.findAllAlerts();

        List<AlertDTO> alertsDTO = getTriggeredAlerts(latestProductPrices, alerts);

        if (alertsDTO.isEmpty()) {
            System.out.println("There are no alerts triggered at this time.");
            return;
        }

        System.out.println("Alerts triggered:");
        for (AlertDTO dto : alertsDTO) {
            System.out.printf("- %s | Current price: %.2f %s | Target price: %.2f RON | Price added on date: %s | Retailer: %s%n",
                    dto.productName(),
                    dto.price(),
                    dto.currency(),
                    dto.targetPrice(),
                    dto.datePrice(),
                    dto.retailerName()
            );
        }
    }
}
