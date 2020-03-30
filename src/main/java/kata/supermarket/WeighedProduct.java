package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;

    private final Department department;

    public WeighedProduct(final BigDecimal pricePerKilo, final Department department) {
        this.pricePerKilo = pricePerKilo;
        this.department = department;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
