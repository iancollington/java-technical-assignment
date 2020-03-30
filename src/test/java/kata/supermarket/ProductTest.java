package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    private static final Department UNKNOWN_DEPARTMENT = new Department("UNKNOWN");

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new Product(price, UNKNOWN_DEPARTMENT).oneOf().price());
    }
}
