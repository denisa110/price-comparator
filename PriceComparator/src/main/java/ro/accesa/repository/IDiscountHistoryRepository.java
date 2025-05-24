package ro.accesa.repository;

import ro.accesa.entity.DiscountHistory;

import java.time.LocalDate;
import java.util.List;

public interface IDiscountHistoryRepository {

    List<DiscountHistory> getDiscountsForDate(LocalDate targetDate, String retailerName, int limit);

    List<DiscountHistory> getNewDiscountsSince(LocalDate fromDate, boolean isActive);
}
