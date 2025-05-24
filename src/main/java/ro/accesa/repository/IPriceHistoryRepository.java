package ro.accesa.repository;

import ro.accesa.entity.PriceHistory;

import java.util.List;

/**
 * Interface for accessing price history data.
 * Defines methods for retrieving the latest prices filtered by product name or category.
 */
public interface IPriceHistoryRepository {
    /**
     * Retrieves the most recent price entries for products whose names contain a given string.
     *
     * @param partialName the partial name to search for within product names
     * @return a list of {@link PriceHistory} entries with the latest prices
     */
    List<PriceHistory> findLatestPriceByProductName(String partialName);

    /**
     * Retrieves the latest price for each product in a given category.
     *
     * @param categoryName the name of the product category
     * @return a list of {@link PriceHistory} entries with the latest prices by category
     */
    List<PriceHistory> getLatestPricePerProductByCategory(String categoryName);
}
