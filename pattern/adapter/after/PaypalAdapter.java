package pattern.adapter.after;

public class PaypalAdapter implements Payment{

    private Paypal paypal;

    public PaypalAdapter(Paypal paypal) {
        this.paypal = paypal;
    }
    @Override
    public void pay(int amount) {
        Paypal paypal = new Paypal();
        paypal.paWithPayPal(amount);
    }
    
}
