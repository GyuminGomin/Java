package pattern.strategy.after;

public class NormalDiscountStrategy implements DiscountStrategy{

    @Override
    public double applyDiscount(double price) {
        return price * 0.95;
    }

}
