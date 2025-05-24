package ro.accesa.repository;

import ro.accesa.entity.PriceAlert;

import java.util.List;

/**
 * Interface for managing PriceAlert entities in the system.
 * Provides methods to persist and retrieve price alerts.
 */
public interface IPriceAlertRepository {
    /**
     * Retrieves all price alerts stored in the database.
     *
     * @return a list of all {@link PriceAlert} objects.
     */
    List<PriceAlert> findAllAlerts();

    /**
     * Saves a new price alert to the database.
     *
     * @param alert the {@link PriceAlert} to be persisted.
     */
    void save(PriceAlert alert);
}
