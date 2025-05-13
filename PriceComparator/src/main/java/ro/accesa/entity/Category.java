package ro.accesa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static ro.accesa.entity.Category.CATEGORY_TABLE;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder  //-Builder pattern
@Entity
@Table(name = CATEGORY_TABLE)
@AttributeOverride(name = "id", column = @Column(name = "CATEGORY_ID"))
public class Category extends PersistenceEntity {

    public static final String CATEGORY_TABLE = "CATEGORY";
    private static final String CATEGORY_ID = "category_id";
    private String name;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Category subCategory;
}
