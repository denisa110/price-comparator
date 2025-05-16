package ro.accesa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = Product.PRODUCT_TABLE)
@AttributeOverride(name = "id", column = @Column(name = Product.PRODUCT_ID))
public class Product extends PersistenceEntity {
    public static final String PRODUCT_TABLE = "PRODUCT";
    public static final String PRODUCT_ID = "product_id";
    private String name;
    private String brand;
    @Column(name = "package_quantity")
    private Double packageQuantity;

    @Column(name = "package_unit")
    private String packageUnit;
    @ManyToOne
    @JoinColumn(name = Category.CATEGORY_ID)
    private Category category;

    public Product() {
        super();
    }

    public Product(Integer id, String name, String brand, Double packageQuantity, String packageUnit) {
        super(id);
        this.name = name;
        this.brand = brand;
        this.packageQuantity = packageQuantity;
        this.packageUnit = packageUnit;
    }

    public Product(String name, String brand, Double packageQuantity, String packageUnit) {
        this.name = name;
        this.brand = brand;
        this.packageQuantity = packageQuantity;
        this.packageUnit = packageUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(Double packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        if (!super.equals(o)) return false;

        if (getName() != null ? !getName().equals(product.getName()) : product.getName() != null) return false;
        if (getBrand() != null ? !getBrand().equals(product.getBrand()) : product.getBrand() != null) return false;
        if (getPackageQuantity() != null ? !getPackageQuantity().equals(product.getPackageQuantity()) : product.getPackageQuantity() != null)
            return false;
        return getPackageUnit() != null ? getPackageUnit().equals(product.getPackageUnit()) : product.getPackageUnit() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 31 * result + (getPackageQuantity() != null ? getPackageQuantity().hashCode() : 0);
        result = 31 * result + (getPackageUnit() != null ? getPackageUnit().hashCode() : 0);
        return result;
    }
}
