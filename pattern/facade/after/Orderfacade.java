package pattern.facade.after;

public class Orderfacade {
    private InventorySystem inventorySystem;
    private PaymentSystem paymentSystem;
    private ShippingSystem shippingSystem;

    public Orderfacade() {
        inventorySystem = new InventorySystem();
        paymentSystem = new PaymentSystem();
        shippingSystem = new ShippingSystem();
    }

    public void processOrder(long productId, double price) {
        // pure business logic
        inventorySystem.checkInventory(productId);
        paymentSystem.processPayment(price);
        shippingSystem.prepareShipping(productId);
    }
}
