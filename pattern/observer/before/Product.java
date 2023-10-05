package pattern.observer.before;

public class Product {
    private String productName;
    private int stock;

    public Product(String productName, int stock) {
        this.productName = productName;
        this.stock = stock;
    }

    public void changeStock(int newStock) {
        this.stock = newStock;
        // 고객에게 알림을 하자!
    }
}
