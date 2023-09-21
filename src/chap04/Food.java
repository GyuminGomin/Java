package chap04;

public class Food extends Product implements Sellable {

    private String expiryDate;

    public Food(String name, double price, String expiryDate) {
        super(name, price);
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return name + " (Price: " + price + ", Expiry Date: " + expiryDate + ")";
    }

    @Override
    public double sell(int quantity) {
        if(quantity >= 3) return price * quantity * 0.8; 
        return price * quantity;
    }

    
}
