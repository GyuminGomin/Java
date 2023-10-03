package pattern.singleton.before;

public class Main {
    public static void main(String[] args) {
        Cart cart1 = new Cart();

        cart1.addItem("Apple");
        cart1.addItem("Banana");

        cart1.printItems();

        Cart cart2 = new Cart();

        cart2.addItem("ABC");

        cart2.printItems();
    }
}
