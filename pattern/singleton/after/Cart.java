package pattern.singleton.after;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    List<String> items;

    private Cart() {
        items = new ArrayList<>();
    }

    //Singleton Pattern
    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        
        return instance;
    }

    void addItem(String item) {
        items.add(item);
    }

    void removeItem(String item) {
        items.remove(item);
    }

    void printItems() {
        System.out.println("--------");
        for (String item : items) {
            System.out.println(item);
        }
        System.out.println("--------");
    }
}
