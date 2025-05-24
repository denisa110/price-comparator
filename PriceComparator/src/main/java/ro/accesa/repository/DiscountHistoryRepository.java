package ro.accesa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ro.accesa.entity.DiscountHistory;

import java.time.LocalDate;
import java.util.List;

public class DiscountHistoryRepository implements IDiscountHistoryRepository {

    private final EntityManager entityManager;
    private static DiscountHistoryRepository instance;

    private DiscountHistoryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static synchronized DiscountHistoryRepository getInstance(EntityManager entityManager) {
        if (instance == null) {
            instance = new DiscountHistoryRepository(entityManager);
        }
        return instance;
    }

    @Override
    public List<DiscountHistory> getDiscountsForDate(LocalDate targetDate, String retailerName, int limit) {

        String baseQuery = "SELECT d FROM DiscountHistory d " +
                "JOIN d.priceHistory ph " +
                "JOIN ph.retailer r " +
                "WHERE :targetDate BETWEEN d.startDate AND d.endDate ";

        if (retailerName != null && !retailerName.isBlank()) {
        baseQuery += "AND r.name = :retailerName ";
        }
        baseQuery += "ORDER BY d.percentageOfDiscount DESC";

        TypedQuery<DiscountHistory> query = entityManager.createQuery(baseQuery, DiscountHistory.class)
                .setParameter("targetDate", targetDate)
                .setMaxResults(limit);
        if (retailerName != null && !retailerName.isBlank()) {
            query.setParameter("retailerName", retailerName);
        }
        return query.getResultList();
    }

    @Override
    public List<DiscountHistory> getNewDiscountsSince(LocalDate fromDate, boolean isActive) {
        String query = "SELECT d FROM DiscountHistory d WHERE ";

        if (isActive) {
            query += ":now BETWEEN d.startDate AND d.endDate AND d.discountCreatedDate >= :fromDate";
        } else {
            query += "d.discountCreatedDate >= :fromDate";
        }

        query += " ORDER BY d.discountCreatedDate DESC";

        TypedQuery<DiscountHistory> typedQuery = entityManager.createQuery(query, DiscountHistory.class)
                .setParameter("fromDate", fromDate);

        if (isActive) {
            typedQuery.setParameter("now", LocalDate.now());
        }

        return typedQuery.getResultList();
    }
}
