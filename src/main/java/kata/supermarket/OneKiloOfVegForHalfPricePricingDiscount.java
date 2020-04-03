package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class OneKiloOfVegForHalfPricePricingDiscount implements PricingDiscount {

    private static final Department VEG_DEPARTMENT = new Department("VEG_DEPARTMENT");

    private String name;

    private Department department;

    public OneKiloOfVegForHalfPricePricingDiscount(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    @Override
    public BigDecimal priceDiscount(List<Item> items) {
        if (items.isEmpty()) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        final List<Item> discountableItems = items.stream()
                                                  .filter(this::discountApplies)
                                                  .collect(Collectors.toList());

        BigDecimal combinedWeight =
            discountableItems.stream()
                             .map(item -> getWeight(item))
                             .reduce(BigDecimal::add)
                             .orElse(BigDecimal.ZERO)
                             .setScale(2, RoundingMode.HALF_UP);

        int discountMultiplier = combinedWeight.intValue();

        ItemByWeight firstItem = (ItemByWeight) items.get(0);

        BigDecimal discountPrice = firstItem.pricePerKilo().divide(new BigDecimal("2"));
        return discountPrice.multiply(new BigDecimal(discountMultiplier));
    }

    private BigDecimal getWeight(Item item) {
        return ((ItemByWeight) item).weightInKilos();
    }

    private boolean discountApplies(Item item) {
        return item instanceof ItemByWeight;
    }
}
