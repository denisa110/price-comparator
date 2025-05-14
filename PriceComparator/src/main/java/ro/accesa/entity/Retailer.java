package ro.accesa.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = Retailer.RETAILER_TABLE)
@AttributeOverride(name = "id", column = @Column(name = Retailer.RETAILER_ID))
public class Retailer extends PersistenceEntity {
    public static final String RETAILER_TABLE = "RETAILER";
    public static final String RETAILER_ID = "retailer_id";
    private String name;
    private String address;
}
