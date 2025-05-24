package ro.accesa.service;

/**
 * Interface for managing price alert services.
 * Provides methods for creating alerts and checking if any have been triggered.
 */
public interface IPriceAlertService {
    /**
     * Checks if any price alerts have been triggered for the given product name.
     *
     * @param productName the name of the product to check alerts for
     */
    void checkTriggeredAlerts(String productName);

    /**
     * Creates a new price alert for the specified product and target price.
     *
     * @param productName the name of the product to track
     * @param targetPrice the target price to trigger the alert
     */
    void create(String productName, Double targetPrice);
}
