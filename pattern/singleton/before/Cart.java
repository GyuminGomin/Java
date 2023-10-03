package pattern.singleton.before;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<String> items;

    public Cart() {
        items = new ArrayList<>();
    }

    void addItem(String item) {
        items.add(item);
    }

    void removeItem(String item) {
        items.remove(item);
    }

    void printItems() {
        for (String item : items) {
            System.out.println(item);
        }
    }
}
