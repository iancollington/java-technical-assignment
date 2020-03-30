package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link PricingDiscount} implementation for Buy one get one free (BOGOF) discount on product {@link Item items} that
 * are in the same {@link Department}.
 * <br/>
 * This discount applies the cheapest product as the free one!
 */
public class BogofPricingDiscount implements PricingDiscount {

    private String name;

    private Department department;

    public BogofPricingDiscount(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    @Override
    public BigDecimal priceDiscount(List<Item> items) {
        final List<Item> discountableItems = items.stream()
                                                  .filter(this::discountApplies)
                                                  .sorted(Comparator.comparing(Item::price))
                                                  .collect(Collectors.toList());

        return cheapestItems(discountableItems).stream()
                                               .map(Item::price)
                                               .reduce(BigDecimal::add)
                                               .orElse(BigDecimal.ZERO)
                                               .setScale(2, RoundingMode.HALF_UP);
    }

    private List<Item> cheapestItems(List<Item> items) {
        int midIndex = items.size() / 2;

        return items.subList(0, midIndex);
    }

    private boolean discountApplies(Item item) {
        return item.department().equals(department);
    }
}
