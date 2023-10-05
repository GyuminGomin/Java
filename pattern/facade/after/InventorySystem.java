package pattern.facade.after;

public class InventorySystem {
    boolean checkInventory(long productId) {
        System.out.println("재고를 검사 합니다.");
        if(productId == 1L) {
            return true;
        }
        else {
            return false;
        }
    }
}
