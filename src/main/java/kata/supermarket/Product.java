package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;

    private final Department department;

    public Product(final BigDecimal pricePerUnit, final Department department) {
        this.pricePerUnit = pricePerUnit;
        this.department = department;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
