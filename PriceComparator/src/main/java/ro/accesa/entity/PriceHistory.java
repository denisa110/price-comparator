package ro.accesa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder  //-Builder pattern
@Entity
@Table(name = "PRICE_HISTORY")
public class PriceHistory extends PersistenceEntity {
    private Integer id;
    private LocalDateTime date;
    private Double price;
    private String currency;
}
