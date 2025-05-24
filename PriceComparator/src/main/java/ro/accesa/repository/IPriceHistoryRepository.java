package ro.accesa.repository;

import ro.accesa.entity.PriceHistory;

import java.util.List;

public interface IPriceHistoryRepository {
    List<PriceHistory> findLatestPriceByProductName(String partialName);

    List<PriceHistory> getLatestPricePerProductByCategory(String categoryName);
}
