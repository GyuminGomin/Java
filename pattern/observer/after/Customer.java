package pattern.observer.after;

public class Customer implements Observer{

    private String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void update(String productName, int newStock) {
        if(newStock == 0) {
            System.out.println(customerName + "님은 " + productName + "의 재고가 없음을 아쉬워 합니다.");
        }
        else {
            System.out.println(customerName + "님은 " + productName + "의 재고가 " + newStock + "개 임을 알았습니다.");
        }
    }
    
}
