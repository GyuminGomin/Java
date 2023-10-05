package pattern.strategy.after;

public class VIPDiscountStrategy implements DiscountStrategy{

    @Override
    public double applyDiscount(double price) {
        return price * 0.9;
    }
    
}
