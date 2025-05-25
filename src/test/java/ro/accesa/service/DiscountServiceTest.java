package ro.accesa.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ro.accesa.entity.DiscountHistory;
import ro.accesa.entity.PriceHistory;
import ro.accesa.entity.Product;
import ro.accesa.entity.Retailer;
import ro.accesa.repository.IDiscountHistoryRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class DiscountServiceTest {

    private IDiscountHistoryRepository discountRepository;
    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        discountRepository = mock(IDiscountHistoryRepository.class);
        discountService = new DiscountService(discountRepository);
    }

    @Test
    void test_displayDiscountsForDate_shouldCallRepositoryWithCorrectParams() {
        LocalDate date = LocalDate.of(2024, 12, 1);
        String retailer = "MegaImage";
        int limit = 5;

        discountService.displayDiscountsForDate(date, retailer, limit);

        verify(discountRepository, times(1)).getDiscountsForDate(date, retailer, limit);
    }

    @Test
    void test_displayDiscountsForDate_shouldHandleEmptyList() {
        when(discountRepository.getDiscountsForDate(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());

        discountService.displayDiscountsForDate(LocalDate.now(), "LIDL", 3);

        verify(discountRepository).getDiscountsForDate(any(), any(), anyInt());
        // No exception should be thrown, and method should complete gracefully
    }

    @Test
    void test_displayDiscountsForDate_shouldHandleOneValidDiscount() {
        Product product = new Product("Pâine", "K-Clasic", 500.0, "g");
        Retailer retailer = new Retailer("LIDL", "Iasi");
        PriceHistory priceHistory = PriceHistory.builder()
                .product(product)
                .retailer(retailer)
                .price(5.00)
                .currency("RON")
                .build();

        DiscountHistory discount = new DiscountHistory(LocalDate.of(2024, 12, 1),
                LocalDate.of(2024, 12, 5), 10f, priceHistory);
        discount.setDiscountCreatedDate(LocalDate.now());

        when(discountRepository.getDiscountsForDate(any(), any(), anyInt()))
                .thenReturn(List.of(discount));

        discountService.displayDiscountsForDate(LocalDate.of(2024, 12, 1), "LIDL", 1);

        verify(discountRepository, times(1)).getDiscountsForDate(any(), any(), anyInt());
    }

    @Test
    void test_displayDiscountsForDate_shouldHandleRepositoryExceptionGracefully() {
        when(discountRepository.getDiscountsForDate(any(), any(), anyInt()))
                .thenThrow(new RuntimeException("DB error"));

        // No exception should propagate
        assertDoesNotThrow(() ->
                discountService.displayDiscountsForDate(LocalDate.now(), "LIDL", 1)
        );
        verify(discountRepository, times(1)).getDiscountsForDate(any(), eq("LIDL"), eq(1));
    }

    @Test
    void test_displayNewDiscounts_shouldCallRepositoryWithCorrectFromDate() {
        int hours = 12;
        boolean isActive = true;

        discountService.displayNewDiscounts(hours, isActive);

        ArgumentCaptor<LocalDate> dateCaptor = ArgumentCaptor.forClass(LocalDate.class);
        verify(discountRepository, times(1))
                .getNewDiscountsSince(dateCaptor.capture(), eq(isActive));

        // Assert that the captured date is not in the future
        assertThat(dateCaptor.getValue()).isBeforeOrEqualTo(LocalDate.now());
    }

    @Test
    void test_displayNewDiscounts_shouldHandleEmptyListGracefully() {
        when(discountRepository.getNewDiscountsSince(any(), anyBoolean()))
                .thenReturn(Collections.emptyList());

        discountService.displayNewDiscounts(24, true);

        verify(discountRepository, times(1)).getNewDiscountsSince(any(), eq(true));
    }

    @Test
    void test_displayNewDiscounts_shouldHandleOneValidDiscount() {
        Product product = new Product("Pâine", "K-Clasic", 500.0, "g");
        Retailer retailer = new Retailer("LIDL", "Iasi");
        PriceHistory priceHistory = PriceHistory.builder()
                .product(product)
                .retailer(retailer)
                .price(5.00)
                .currency("RON")
                .build();

        DiscountHistory discount = new DiscountHistory(LocalDate.of(2024, 12, 1),
                LocalDate.of(2024, 12, 5), 10f, priceHistory);
        discount.setDiscountCreatedDate(LocalDate.now());

        when(discountRepository.getNewDiscountsSince(any(), eq(true)))
                .thenReturn(List.of(discount));

        discountService.displayNewDiscounts(3, true);

        verify(discountRepository, times(1)).getNewDiscountsSince(any(), eq(true));
    }
}
