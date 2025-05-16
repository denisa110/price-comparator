package ro.accesa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder  //-Builder pattern
@Entity
@Table(name = "DISCOUNT_HISTORY")
@AttributeOverride(name = "id", column = @Column(name = DiscountHistory.DISCOUNT_HISTORY_ID))
public class DiscountHistory extends PersistenceEntity {
    public static final String DISCOUNT_HISTORY_TABLE = "DISCOUNT_HISTORY";
    public static final String DISCOUNT_HISTORY_ID = "discount_history_id";
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "percentage_of_discount")
    private Float percentageOfDiscount;
    @Column(name = "discount_created_date")
    private LocalDate discountCreatedDate;

    @ManyToOne
    @JoinColumn(name = PriceHistory.PRICE_HISTORY_ID)
    private PriceHistory priceHistory;
}
