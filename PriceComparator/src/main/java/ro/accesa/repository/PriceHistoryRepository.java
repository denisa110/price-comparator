package ro.accesa.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ro.accesa.entity.PriceHistory;

import java.util.List;

@RequiredArgsConstructor
public class PriceHistoryRepository {
    private final EntityManager entityManager;

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


}
