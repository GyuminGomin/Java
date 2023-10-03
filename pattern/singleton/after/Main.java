package pattern.singleton.after;

public class Main {
    public static void main(String[] args) {
        Cart cart1 = Cart.getInstance();

        cart1.addItem("Apple");
        cart1.addItem("Banana");

        cart1.printItems();

        Cart cart2 = Cart.getInstance();

        cart2.addItem("ABC");

        cart2.printItems();

        if(cart1 == cart2) {
            System.out.println("same");
        }
    }
}
