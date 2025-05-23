package ro.accesa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import ro.accesa.entity.PriceAlert;

import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class PriceAlertRepository {
    private static final Logger logger = Logger.getLogger(PriceAlertRepository.class.getName());
    private final EntityManager entityManager;

    public void save(PriceAlert alert) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(alert);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            logger.severe("Problems when saving the alert!");
            throw e;
        }
    }

    public List<PriceAlert> findAllAlerts() {
        return entityManager.createQuery(
                "SELECT a FROM PriceAlert a ", PriceAlert.class
        ).getResultList();
    }

}
