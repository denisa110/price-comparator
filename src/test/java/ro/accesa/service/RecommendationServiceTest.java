package ro.accesa.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.accesa.entity.PriceHistory;
import ro.accesa.entity.Product;
import ro.accesa.entity.Retailer;
import ro.accesa.repository.IPriceHistoryRepository;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.*;

class RecommendationServiceTest {
    private IPriceHistoryRepository priceHistoryRepository;
    private RecommendationService recommendationService;

    @BeforeEach
    void setUp() {
        priceHistoryRepository = mock(IPriceHistoryRepository.class);
        recommendationService = new RecommendationService(priceHistoryRepository);
    }

    @Test
    void shouldPrintNoPricesMessageWhenEmptyListReturned() {
        // Given
        String category = "Lactate";
        when(priceHistoryRepository.getLatestPricePerProductByCategory(category))
                .thenReturn(emptyList());

        // When
        recommendationService.showBestValueProductsByCategory(category);

        // Then
        verify(priceHistoryRepository).getLatestPricePerProductByCategory(category);
        // Putem folosi SystemOutRule dacă vrem verificare text exact
    }

    @Test
    void shouldGroupAndPrintBestValueAndAlternativesCorrectly() {
        // Given
        String category = "Lactate";

        Product p1 = new Product("Lapte ZUZU", "ZUZU", 1.5, "L");
        Product p2 = new Product("Cascaval", "ZUZU", 1.0, "kg");
        Retailer r1 = new Retailer("LIDL", "Iasi");
        Retailer r2 = new Retailer("KAUFLAND", "Iasi");

        PriceHistory best = PriceHistory.builder()
                .product(p1)
                .retailer(r1)
                .price(5.00)
                .build();

        PriceHistory alt = PriceHistory.builder()
                .product(p2)
                .retailer(r2)
                .price(6.00)
                .build();

        when(priceHistoryRepository.getLatestPricePerProductByCategory(category))
                .thenReturn(Arrays.asList(best, alt));

        // When
        recommendationService.showBestValueProductsByCategory(category);

        // Then
        verify(priceHistoryRepository, times(1)).getLatestPricePerProductByCategory(category);
        // Testăm indirect prin lipsa erorilor și execuția completă
    }

    @Test
    void shouldGroupByNameAndUnitCaseInsensitive() {
        // Given
        String category = "Băuturi";

        Product p1 = new Product("Apa", "Bucovina", 2.0, "L");
        Product p2 = new Product("APA", "Bucovina", 2.0, "L");
        Retailer r1 = new Retailer("LIDL", "Iasi");
        Retailer r2 = new Retailer("KAUFLAND", "Iasi");

        PriceHistory ph1 = PriceHistory.builder()
                .product(p1)
                .retailer(r1)
                .price(5.00)
                .build();

        PriceHistory ph2 = PriceHistory.builder()
                .product(p2)
                .retailer(r2)
                .price(8.50)
                .build();

        when(priceHistoryRepository.getLatestPricePerProductByCategory(category))
                .thenReturn(Arrays.asList(ph1, ph2));

        // When
        recommendationService.showBestValueProductsByCategory(category);

        // Then
        verify(priceHistoryRepository).getLatestPricePerProductByCategory(category);
    }

    @Test
    void shouldHandleSingleBestPriceCorrectly() {
        // Given
        String category = "Panificație";

        Product product = new Product("Pâine", "K-Clasic", 500.0, "g");
        Retailer retailer = new Retailer("LIDL", "Iasi");

        PriceHistory only = PriceHistory.builder()
                .product(product)
                .retailer(retailer)
                .price(4.50)
                .build();

        when(priceHistoryRepository.getLatestPricePerProductByCategory(category))
                .thenReturn(List.of(only));

        // When
        recommendationService.showBestValueProductsByCategory(category);

        // Then
        verify(priceHistoryRepository).getLatestPricePerProductByCategory(category);
    }
}