package ro.accesa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.accesa.entity.PriceHistory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PriceHistoryRepositoryTest {

    private EntityManager entityManager;
    private TypedQuery<PriceHistory> typedQuery;
    private PriceHistoryRepository repository;

    @BeforeEach
    void setUp() {
        entityManager = mock(EntityManager.class);
        typedQuery = mock(TypedQuery.class);
        repository = new PriceHistoryRepository(entityManager);
    }

    @Test
    void test_getLatestPricePerProductByCategory_shouldCreateQueryWithLowercaseCategoryName() {
        List<PriceHistory> mockResult = List.of(mock(PriceHistory.class));

        when(entityManager.createQuery(anyString(), eq(PriceHistory.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("catName"), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(mockResult);

        List<PriceHistory> result = repository.getLatestPricePerProductByCategory("Snacks");

        verify(entityManager).createQuery(startsWith("SELECT ph FROM PriceHistory"), eq(PriceHistory.class));
        verify(typedQuery).setParameter("catName", "snacks");
        verify(typedQuery).getResultList();

        assertThat(result).isEqualTo(mockResult);
    }
}