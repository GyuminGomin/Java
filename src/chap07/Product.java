package chap07;

public class Product {
    
    private String name;
    private int price;

    public int getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
