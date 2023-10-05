package pattern.facade.after;

public class Main {
    public static void main(String[] args) {

        Orderfacade order = new Orderfacade();
        order.processOrder(1, 40000);
        
    }
}
