package ro.accesa.repository;

import jakarta.persistence.EntityManager;
import ro.accesa.entity.Product;

import java.util.Optional;

public class ProductRepository {
    private static ProductRepository instance;
    private final EntityManager em;

    public ProductRepository(EntityManager em) {
        this.em = em;
    }

    public static synchronized ProductRepository getInstance(EntityManager em) {
        if (instance == null) {
            instance = new ProductRepository(em);
        }
        return instance;
    }

    public Optional<Product> findByName(String name) {
        return Optional.ofNullable(em.createQuery(
                        "SELECT p FROM Product p WHERE p.name = :name", Product.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getSingleResult());
    }
}
