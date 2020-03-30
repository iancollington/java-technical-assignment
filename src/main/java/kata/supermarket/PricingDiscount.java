package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public interface PricingDiscount {

    BigDecimal priceDiscount(List<Item> items);
}
