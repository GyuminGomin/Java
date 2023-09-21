package chap07;

public class ProductNew {
    
    private String name;
    private int price;
    private String category;

    public String getCategory() {
        return category;
    }
    public int getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }

    public ProductNew(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
