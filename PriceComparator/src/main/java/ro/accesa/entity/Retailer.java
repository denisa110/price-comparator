package ro.accesa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "RETAILER")
public class Retailer extends PersistenceEntity {
    private String name;
    private String address;
}
