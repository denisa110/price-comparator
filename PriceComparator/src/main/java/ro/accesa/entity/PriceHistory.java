package ro.accesa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder  //-Builder pattern
@Entity
@Table(name = PriceHistory.PRICE_HISTORY_TABLE)
@AttributeOverride(name = "id", column = @Column(name = PriceHistory.PRICE_HISTORY_ID))
public class PriceHistory extends PersistenceEntity {
    public static final String PRICE_HISTORY_TABLE = "PRICE_HISTORY";
    public static final String PRICE_HISTORY_ID = "price_history_id";
    @Column(name = "date_price")
    private LocalDate datePrice;
    private Double price;
    private String currency;

    @ManyToOne
    @JoinColumn(name = Product.PRODUCT_ID)
    private Product product;

    @ManyToOne
    @JoinColumn(name = Retailer.RETAILER_ID)
    private Retailer retailer;
}
