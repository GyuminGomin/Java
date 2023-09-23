package chap08;

import java.io.Serializable;
// Seriaizable ? -> simple to say just load (file to byte, file to text, byte to file, byte to text)

public class Product implements Serializable{
    
    private String name;
    private String price;

    

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product(name='" + name + "', price=" + price + ")";
    }
    
    public Product(String name, String price) {
        this.name = name;
        this.price = price;
    }
    public Product() {
    }
}
