package ro.accesa.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ro.accesa.entity.PriceHistory;

import java.util.List;

@RequiredArgsConstructor
public class PriceHistoryRepository implements IPriceHistoryRepository {
    private final EntityManager entityManager;

    /**
     * Finds the most recent price records for products that match the given partial name.
     *
     * @param partialName part of the product name to filter by
     * @return list of {@link PriceHistory} entities containing the latest prices for matching products
     */
    @Override
    public List<PriceHistory> findLatestPriceByProductName(String partialName) {
        String jpql = """
                    SELECT ph
                    FROM PriceHistory ph
                    JOIN ph.product p
                    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :partialName, '%'))
                      AND ph.datePrice = (
                          SELECT MAX(ph2.datePrice)
                          FROM PriceHistory ph2
                          WHERE ph2.product = ph.product
                      )
                """;

        return entityManager.createQuery(jpql, PriceHistory.class)
                .setParameter("partialName", partialName)
                .getResultList();
    }

    @Override
    public List<PriceHistory> getLatestPricePerProductByCategory(String categoryName) {
        return entityManager.createQuery(
                        "SELECT ph FROM PriceHistory ph " +
                                "JOIN ph.product p JOIN p.category c " +
                                "WHERE LOWER(c.name) = :catName AND ph.datePrice = (" +
                                "   SELECT MAX(ph2.datePrice) FROM PriceHistory ph2 WHERE ph2.product = p" +
                                ")",
                        PriceHistory.class)
                .setParameter("catName", categoryName.toLowerCase())
                .getResultList();
    }
}
