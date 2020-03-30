package kata.supermarket;

/**
 * {@link PricingDiscount} implementation for Buy one get one free (BOGOF) discount on product {@link Item items} that
 * are in the same {@link Department}.
 */
public class BogofPricingDiscount implements PricingDiscount {

    private String name;

    private Department department;

    public BogofPricingDiscount(String name, Department department) {
        this.name = name;
        this.department = department;
    }
}
