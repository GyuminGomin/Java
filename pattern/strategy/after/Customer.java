package pattern.strategy.after;

public class Customer {
    private String customerType;
    private DiscountStrategy discountStrategy;

    public Customer(String customerType) {
        this.customerType = customerType;
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double getDiscountedPrice(double price) {
        return discountStrategy.applyDiscount(price);
    }
}
