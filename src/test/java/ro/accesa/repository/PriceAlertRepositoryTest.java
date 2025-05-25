package ro.accesa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.accesa.entity.PriceAlert;
import ro.accesa.entity.Product;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PriceAlertRepositoryTest {
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private PriceAlertRepository repository;

    @BeforeEach
    void setUp() {
        entityManager = mock(EntityManager.class);
        transaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transaction);

        repository = new PriceAlertRepository(entityManager);
    }

    @Test
    void test_save_shouldPersistAndCommit() {
        PriceAlert alert = createMockAlert(19.99, false);

        repository.save(alert);

        verify(transaction).begin();
        verify(entityManager).persist(alert);
        verify(transaction).commit();
        verify(transaction, never()).rollback();
    }

    @Test
    void test_save_shouldRollbackOnException() {
        PriceAlert alert = createMockAlert(29.99, false);

        doThrow(RuntimeException.class).when(entityManager).persist(alert);
        when(transaction.isActive()).thenReturn(true);

        assertThrows(RuntimeException.class, () -> repository.save(alert));

        verify(transaction).begin();
        verify(transaction).rollback();
        verify(transaction, never()).commit();
    }

    @Test
    void test_findAllAlerts_shouldQueryDatabaseCorrectly() {
        PriceAlert alert1 = createMockAlert(10.0, false);
        PriceAlert alert2 = createMockAlert(5.0, true);
        List<PriceAlert> expected = Arrays.asList(alert1, alert2);

        TypedQuery<PriceAlert> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT a FROM PriceAlert a ", PriceAlert.class))
                .thenReturn(query);
        when(query.getResultList()).thenReturn(expected);

        List<PriceAlert> result = repository.findAllAlerts();

        verify(entityManager).createQuery("SELECT a FROM PriceAlert a ", PriceAlert.class);
        verify(query).getResultList();
        assertThat(result).containsExactly(alert1, alert2);
    }

    private PriceAlert createMockAlert(Double price, boolean isNotified) {
        Product product = new Product();
        product.setName("Mock Product");
        product.setBrand("MockBrand");
        product.setPackageQuantity(1.0);
        product.setPackageUnit("L");

        PriceAlert alert = new PriceAlert();
        alert.setTargetPrice(price);
        alert.setNotified(isNotified);
        alert.setProduct(product);

        return alert;
    }
}
