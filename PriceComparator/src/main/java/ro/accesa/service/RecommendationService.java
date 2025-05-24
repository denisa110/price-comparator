package ro.accesa.service;

import ro.accesa.entity.PriceHistory;
import ro.accesa.entity.Product;
import ro.accesa.repository.PriceHistoryRepository;
import ro.accesa.util.ConsoleUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecommendationService implements IRecommendationService {
    private final PriceHistoryRepository priceHistoryRepository;

    public RecommendationService(PriceHistoryRepository priceHistoryRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @Override
    public void showBestValueProductsByCategory(String category) {
        List<PriceHistory> prices = priceHistoryRepository.getLatestPricePerProductByCategory(category);

        if (prices.isEmpty()) {
            System.out.println("There are no prices recorded for this category.");
            return;
        }

        Map<String, List<PriceHistory>> grouped = groupByProductAndUnit(prices);

        System.out.println("\nRecommendations in the category \"" + category + "\" (value per unit):");

        grouped.forEach(this::printRecommendationGroup);
    }

    /**
     * Groups price history entries by product name and packaging unit.
     *
     * @param prices list of price entries
     * @return a map of grouped entries
     */
    private Map<String, List<PriceHistory>> groupByProductAndUnit(List<PriceHistory> prices) {
        return prices.stream()
                .filter(ph -> ph.getValuePerUnit() != null)
                .collect(Collectors.groupingBy(ph -> {
                    Product p = ph.getProduct();
                    return p.getName().toLowerCase() + " (" + p.getPackageUnit() + ")";
                }));
    }

    /**
     * Displays the best and alternative recommendations within a group of products.
     *
     * @param groupName name of the product group
     * @param group list of price entries within the group
     */
    private void printRecommendationGroup(String groupName, List<PriceHistory> group) {
        group.sort(Comparator.comparing(PriceHistory::getValuePerUnit));
        PriceHistory bestPrice = group.getFirst();
        Product bestProduct = bestPrice.getProduct();
        double bestVPU = bestPrice.getValuePerUnit();

        System.out.printf("%n-- %s --%n", capitalize(groupName));
        System.out.printf("%s Recommended:%s %.2f RON/%s | %s (%.2f RON)%n",
                ConsoleUtils.GREEN, ConsoleUtils.RESET,
                bestVPU,
                bestProduct.getPackageUnit(),
                bestPrice.getRetailer().getName(),
                bestPrice.getPrice());

        group.stream().skip(1).forEach(ph -> printAlternative(ph, bestVPU));
    }

    /**
     * Prints alternative product suggestions with their price difference vs. the best value.
     *
     * @param priceHistory the price history entry
     * @param bestVPU value per unit of the best recommendation
     */
    private void printAlternative(PriceHistory priceHistory, double bestVPU) {
        Product product = priceHistory.getProduct();
        double vpu = priceHistory.getValuePerUnit();
        double diffPercent = ((vpu - bestVPU) / bestVPU) * 100;

        System.out.printf("   %.2f RON/%s | %s (%.2f RON)  %s(+%.1f%%)%s%n",
                vpu,
                product.getPackageUnit(),
                priceHistory.getRetailer().getName(),
                priceHistory.getPrice(),
                ConsoleUtils.YELLOW,
                diffPercent,
                ConsoleUtils.RESET);
    }

    public String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}


