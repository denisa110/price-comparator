package ro.accesa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder  //-Builder pattern
@Entity
@Table(name = "DISCOUNT_HISTORY")
public class DiscountHistory extends PersistenceEntity {
    private LocalDate startDate;
    private LocalDate endDate;
    private Float percentageOfDiscount;
}
