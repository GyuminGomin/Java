package pattern.strategy.before;

public class Customer {
    private String customerType;

    public Customer(String customerType) {
        this.customerType = customerType;
    }

    public double applyDiscount(double price) {
        if(customerType.equals("VIP")) {
            return price * 0.9;
        }
        else {
            return price * 0.95;
        }
    }
}
