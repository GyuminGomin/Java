package pattern.facade.after;

public class ShippingSystem {
    boolean prepareShipping(long productId) {
        System.out.println("배송을 준비중입니다.");
        if (productId == 1) {
            System.out.println("배송 처리가 정상적으로 이뤄졌습니다.");
            return true;
        }
        else {
            System.out.println("배송준비가 실패되었습니다.");
            return false;
        }
    }
}
