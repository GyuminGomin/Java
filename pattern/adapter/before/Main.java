package pattern.adapter.before;

public class Main {
    public static void main(String[] args) {
        Paypal paypal = new Paypal();
        paypal.paWithPayPal(100);
        CreditCard card = new CreditCard();
        card.chargeCreditCard(200);
    }
}
