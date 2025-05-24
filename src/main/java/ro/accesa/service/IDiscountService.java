package ro.accesa.service;

import java.time.LocalDate;

/**
 * Interface for the service layer responsible for displaying discount-related data.
 * Provides methods to display discounts for a specific date and new discounts within a time range.
 */
public interface IDiscountService {
    /**
     * Displays discounts that are valid for a user-specified date.
     * Reads input from the console for date, retailer, and result limit.
     */
    void displayDiscountsForDate(LocalDate targetDate, String retailer, int limit);

    /**
     * Displays new discounts added within the specified number of hours.
     *
     * @param hours    how many hours back to look for new discounts
     * @param isActive whether to filter for currently active discounts
     */
    void displayNewDiscounts(int hours, boolean isActive);

}
