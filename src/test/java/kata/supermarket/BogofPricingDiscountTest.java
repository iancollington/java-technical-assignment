package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BogofPricingDiscountTest {

    private static final Department APPLICABLE_DEPARTMENT = new Department("APPLICABLE_DEPARTMENT");

    private static final Department ANOTHER_DEPARTMENT = new Department("A_N_OTHER_DEPARTMENT");

    private static final PricingDiscount BOGOF_DISCOUNT = new BogofPricingDiscount("BOGOF on dairy", APPLICABLE_DEPARTMENT);

    @Test
    void whenTwoItemsInApplicableDepartmentThenDiscountedIsApplied() {
        List<Item> items = Arrays.asList(
            aPintOfMilk(APPLICABLE_DEPARTMENT),
            aPintOfMilk(APPLICABLE_DEPARTMENT)
        );

        BigDecimal priceDiscount = BOGOF_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("0.49"), priceDiscount);
    }

    @Test
    void whenOneItemInApplicableDepartmentAndOneItemNotThenDiscountNotApplied() {
        List<Item> items = Arrays.asList(
            aPintOfMilk(APPLICABLE_DEPARTMENT),
            aPintOfMilk(ANOTHER_DEPARTMENT)
        );

        BigDecimal priceDiscount = BOGOF_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("0.00"), priceDiscount);
    }

    @Test
    void whenThreeItemsInApplicableDepartmentThenDiscountAppliedOnce() {
        List<Item> items = Arrays.asList(
            aPintOfMilk(APPLICABLE_DEPARTMENT),
            aPintOfMilk(APPLICABLE_DEPARTMENT),
            aPintOfMilk(APPLICABLE_DEPARTMENT)
        );

        BigDecimal priceDiscount = BOGOF_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("0.49"), priceDiscount);
    }

    @Test
    void whenFourItemsInApplicableDepartmentThenDiscountAppliedTwice() {
        List<Item> items = Arrays.asList(
            aPintOfMilk(APPLICABLE_DEPARTMENT),
            aPintOfMilk(APPLICABLE_DEPARTMENT),
            aPintOfMilk(APPLICABLE_DEPARTMENT),
            aPintOfMilk(APPLICABLE_DEPARTMENT)
        );

        BigDecimal priceDiscount = BOGOF_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("0.98"), priceDiscount);
    }

    @Test
    void whenFourItemsNotInApplicableDepartmentThenDiscountNotApplied() {
        List<Item> items = Arrays.asList(
            aPintOfMilk(ANOTHER_DEPARTMENT),
            aPintOfMilk(ANOTHER_DEPARTMENT),
            aPintOfMilk(ANOTHER_DEPARTMENT),
            aPintOfMilk(ANOTHER_DEPARTMENT)
        );

        BigDecimal priceDiscount = BOGOF_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("0.00"), priceDiscount);
    }

    private static Item aPintOfMilk(final Department department) {
        return new Product(new BigDecimal("0.49"), department).oneOf();
    }
}
