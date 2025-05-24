package ro.accesa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import ro.accesa.entity.Product;

public class ProductRepository {
    private static ProductRepository instance;
    private final EntityManager em;

    public ProductRepository(EntityManager em) {
        this.em = em;
    }

    /**
     * Returns a singleton instance of {@link ProductRepository}.
     *
     * @param em the entity manager to use
     * @return the singleton instance
     */
    public static synchronized ProductRepository getInstance(EntityManager em) {
        if (instance == null) {
            instance = new ProductRepository(em);
        }
        return instance;
    }

    public Product findByName(String name) {
        try {
            return em.createQuery(
                            "SELECT p FROM Product p WHERE LOWER(p.name) = :name", Product.class)
                    .setParameter("name", name.toLowerCase())
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
