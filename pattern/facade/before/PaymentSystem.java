package pattern.facade.before;

public class PaymentSystem {
    boolean processPayment(double price) {
        System.out.println("결제를 처리합니다.");
        if (price <= 50000.00) {
            System.out.println("결제가 성공했습니다.");
        return true;
    }
        else {
            System.out.println("결제가 실패했습니다.");
            return false;
        }
    }
    
}
