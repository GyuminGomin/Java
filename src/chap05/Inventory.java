package chap05;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Inventory<T extends Product> {
    private List<Product> stock = new ArrayList<>();

    void addStock(Product item) {
        stock.add(item);
    }

    Product search(String name) {
        for (Product item : stock) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        throw new NoSuchElementException("찾는 상품이 없습니다.");
    }
}
