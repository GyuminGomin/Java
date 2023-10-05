package pattern.observer.after;

import java.util.ArrayList;
import java.util.List;

public class Product implements Observerable{
    private List<Observer> observers;
    private String productName;
    private int stock;

    public Product(String productName, int stock) {
        this.observers = new ArrayList<>();
        this.productName = productName;
        this.stock = stock;
    }

    public void changeStock(int newStock) {
        this.stock = newStock;
        notifyObservers();
        // 고객에게 알림을 하자!
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(productName, stock);
        }
    }
}
