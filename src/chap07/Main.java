package chap07;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Product1", 1000),
            new Product("Product2", 2000),
            new Product("Product3", 3000),
            new Product("Product4", 4000),
            new Product("Product5", 5000)
        );

        Predicate<Product> priceFilter = product -> product.getPrice() >= 3000;
        for(Product p : products) {
            if(priceFilter.test(p)) System.out.println(p.getName());
        }
    }
}
