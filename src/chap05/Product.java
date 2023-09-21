package chap05;

public abstract class Product {
    protected String name;
    protected double price;

    public abstract String toString(); // toString이란 객체를 설명하고자 할 때,

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public Product() {
    }
    
}
