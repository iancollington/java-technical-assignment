package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OneKiloOfVegForHalfPricePricingDiscountTest {

    private static final Department VEG_DEPARTMENT = new Department("VEG_DEPARTMENT");

    private static final Department ANOTHER_DEPARTMENT = new Department("A_N_OTHER_DEPARTMENT");

    private static final PricingDiscount
        ONE_KILO_VEG_HALF_PRICE_DISCOUNT = new OneKiloOfVegForHalfPricePricingDiscount("One kilo of veg half price", VEG_DEPARTMENT);

    @Test
    void whenItemsAreEmptyThenDiscountDoesNotApply() {
        BigDecimal priceDiscount = ONE_KILO_VEG_HALF_PRICE_DISCOUNT.priceDiscount(Collections.emptyList());

        assertEquals(new BigDecimal("0.00"), priceDiscount);
    }

    @Test
    void whenItemIsLessThanOneKiloThenDiscountDoesNotApply() {
        List<Item> items = Arrays.asList(
            halfAKiloOfVeg()
        );

        BigDecimal priceDiscount = ONE_KILO_VEG_HALF_PRICE_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("0.00"), priceDiscount);
    }

    @Test
    void whenItemIsMoreThanOneKiloThenDiscountDoesApply() {
        List<Item> items = Arrays.asList(
            aKiloOfVeg()
        );

        BigDecimal priceDiscount = ONE_KILO_VEG_HALF_PRICE_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("2.50"), priceDiscount);
    }

    @Test
    void whenMultiplyItemsAreLessThanOneKiloThenDiscountDoesNotApply() {
        List<Item> items = Arrays.asList(
            quarterOfAKiloOfVeg(),
            quarterOfAKiloOfVeg()
        );

        BigDecimal priceDiscount = ONE_KILO_VEG_HALF_PRICE_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("0.00"), priceDiscount);
    }

    @Test
    void whenMultiplyItemsAreMoreThanOneKiloThenDiscountDoesApply() {
        List<Item> items = Arrays.asList(
            aKiloOfVeg(),
            aKiloOfVeg(),
            aKiloOfVeg()
        );

        BigDecimal priceDiscount = ONE_KILO_VEG_HALF_PRICE_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("7.50"), priceDiscount);
    }

    @Test
    void whenMultiplyItemsAreMoreThanOneKiloWithFractionThenDiscountDoesApply() {
        List<Item> items = Arrays.asList(
            aKiloOfVeg(),
            aKiloOfVeg(),
            aKiloOfVeg(),
            quarterOfAKiloOfVeg()
        );

        BigDecimal priceDiscount = ONE_KILO_VEG_HALF_PRICE_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("7.50"), priceDiscount);
    }

    @Test
    void whenWeightedItemIsMoreThanOneKiloAndHasNonWeightedProductsThenDiscountDoesApply() {
        List<Item> items = Arrays.asList(
            aKiloOfVeg(),
            aPintOfMilk(ANOTHER_DEPARTMENT)
        );

        BigDecimal priceDiscount = ONE_KILO_VEG_HALF_PRICE_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("2.50"), priceDiscount);
    }

    @Test
    void advancedTest() {
        List<Item> items = Arrays.asList(
            aKiloOfVeg(),
            aKiloOfFruit()
        );

        BigDecimal priceDiscount = ONE_KILO_VEG_HALF_PRICE_DISCOUNT.priceDiscount(items);

        assertEquals(new BigDecimal("7.50"), priceDiscount);
    }

    private static WeighedProduct vegProduct() {
        return new WeighedProduct(new BigDecimal("5.00"), VEG_DEPARTMENT);
    }

    private static WeighedProduct fruitProduct() {
        return new WeighedProduct(new BigDecimal("10.00"), VEG_DEPARTMENT);
    }

    private static Item oneOfAnotherProduct() {
        return fruitProduct().weighing(new BigDecimal(".25"));
    }

    private static Item quarterOfAKiloOfVeg() {
        return vegProduct().weighing(new BigDecimal(".25"));
    }

    private static Item halfAKiloOfVeg() {
        return vegProduct().weighing(new BigDecimal(".5"));
    }

    private static Item aKiloOfVeg() {
        return vegProduct().weighing(new BigDecimal("1"));
    }

    private static Item aKiloOfFruit() {
        return fruitProduct().weighing(new BigDecimal("1"));
    }

    private static Item aPintOfMilk(final Department department) {
        return new Product(new BigDecimal("0.49"), department).oneOf();
    }
}
