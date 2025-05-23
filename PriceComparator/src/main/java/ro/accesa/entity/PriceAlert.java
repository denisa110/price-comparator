package ro.accesa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = PriceAlert.PRICE_ALERT_TABLE)
@AttributeOverride(name = "id", column = @Column(name = PriceAlert.PRICE_ALERT_ID))
public class PriceAlert extends PersistenceEntity {
    public static final String PRICE_ALERT_TABLE = "price_alert";
    public static final String PRICE_ALERT_ID = "price_alert_id";

    @Column(name = "target_price")
    private Double targetPrice;

    @Column(name = "is_notified")
    private boolean isNotified = false;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
