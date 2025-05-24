package ro.accesa.service;

/**
 * Interface for product recommendation services.
 * Provides a method to display best value products based on unit price within a category.
 */
public interface IRecommendationService {

    /**
     * Retrieves the latest prices per product in the specified category and prints a comparison
     * based on value per unit (VPU), highlighting the best value.
     *
     * @param category product category for which to show recommendations
     */
    void showBestValueProductsByCategory(String category);

}
