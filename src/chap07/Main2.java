package chap07;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main2 {
    public static void main(String[] args) {
        List<ProductNew> products = Arrays.asList(
            new ProductNew("Product1", 1000, "Clothing"),
            new ProductNew("Product2", 2000, "Clothing"),
            new ProductNew("Product3", 3000, "Electronics"),
            new ProductNew("Product4", 4000, "Food"),
            new ProductNew("Product5", 5000, "Food")
        );

        Predicate<ProductNew> clothingFilter = p -> "Clothing".equals(p.getCategory());
        products.stream().filter(clothingFilter).forEach(p -> System.out.println("의류인 것 : " + p.getName()));
        Predicate<ProductNew> priceFilter = p -> p.getPrice() >= 2000;
        products.stream().filter(priceFilter).forEach(p -> System.out.println("2000원이 넘는 것 : " + p.getName()));
        products.stream().filter(clothingFilter).filter(priceFilter).forEach(p -> System.out.println("살아남은것 : " + p.getName()));
    }
}
