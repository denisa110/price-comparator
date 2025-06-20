package ro.accesa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import static ro.accesa.entity.Category.CATEGORY_ID;
import static ro.accesa.entity.Category.CATEGORY_TABLE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = CATEGORY_TABLE)
@AttributeOverride(name = "id", column = @Column(name = CATEGORY_ID))
public class Category extends PersistenceEntity {

    public static final String CATEGORY_TABLE = "CATEGORY";
    public static final String CATEGORY_ID = "category_id";
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;
}
