# Notes

Please add here any notes, assumptions and design decisions that might help up understand your though process.

---

My thinking is that some sort of _discount_ object is required for each type of discount available. The logic to apply the discount will vary so this should be encapsulated in it's own class. I have created a `PricingDiscount` interface to this affect. Implementations of this interface must implement the `priceDiscount` method whereby the current basket contents are passed in. It is up to the implementing class to determine how this discount applies to the products in this list. Any discount could, afterall, apply more than once if enough qualifying products are in the basket.

I have implemented a simple buy one get one free discount class that can be applied to all products in a specific Department. The department is a class in it's own right. This would allow this class to be extended to support sub-departments in a hierarchy which would better represent a real supermarket. e.g. Bakery -> Bread -> White Bread

Whilst my implementation uses the department, some other attribute of the product could be used such as the product ID whereby the BOGOF deal could apply to a specific product only. This would be a separate implementation of the `PricingDiscount` interface.

