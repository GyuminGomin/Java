package pattern.adapter.after;

public class Main {
    public static void main(String[] args) {
        Paypal paypal = new Paypal();
        CreditCard card = new CreditCard();
        
        Payment paypalAdapter = new PaypalAdapter(paypal);
        Payment CreditCardAdapter = new CreditCardAdapter(card);

        paypalAdapter.pay(100);
        CreditCardAdapter.pay(200);
        
        paypal.paWithPayPal(100);
        card.chargeCreditCard(200);
    }
}
