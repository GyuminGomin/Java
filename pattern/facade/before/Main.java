package pattern.facade.before;

public class Main {
    public static void main(String[] args) {
        InventorySystem is = new InventorySystem();
        PaymentSystem ps = new PaymentSystem();
        ShippingSystem ss = new ShippingSystem();

        // business logic
        is.checkInventory(1);
        ps.processPayment(30000);
        ss.prepareShipping(1);
    }
}
