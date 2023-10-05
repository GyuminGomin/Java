package pattern.adapter.after;

public class CreditCardAdapter implements Payment{

    private CreditCard card;

    public CreditCardAdapter(CreditCard card) {
        this.card = card;
    }
    @Override
    public void pay(int amount) {
        card.chargeCreditCard(amount);
    }
    
}
