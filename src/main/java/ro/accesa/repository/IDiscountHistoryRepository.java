package ro.accesa.repository;

import ro.accesa.entity.DiscountHistory;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for accessing discount history data from the database.
 * Defines methods to retrieve active and newly added discounts.
 */
public interface IDiscountHistoryRepository {
    /**
     * Retrieves the top discount entries that are valid on the given date,
     * optionally filtered by retailer name.
     *
     * @param targetDate   The date to check for active discounts.
     * @param retailerName (Optional) Filter results to a specific retailer.
     * @param limit        Maximum number of results to return.
     * @return A list of {@link DiscountHistory} entries valid on the target date.
     */
    List<DiscountHistory> getDiscountsForDate(LocalDate targetDate, String retailerName, int limit);

    /**
     * Retrieves all discount entries created since a given date,
     * with optional filtering for currently active discounts.
     *
     * @param fromDate The date from which to look for new discounts.
     * @param isActive If true, only return currently active discounts.
     * @return A list of newly created {@link DiscountHistory} entries.
     */
    List<DiscountHistory> getNewDiscountsSince(LocalDate fromDate, boolean isActive);
}
