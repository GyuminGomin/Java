package chap03;

public class Electronics extends Product {
    
    private String manufacturer;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Electronics(String name, double price, int quantity, String manufacturer) {
        super(name, price, quantity); // new Product(name, price, quantity)
        this.manufacturer = manufacturer;
    }

    @Override
    public void description() {
        System.out.println(
            manufacturer +  
            getName() + " 상품의 가격은 "+
            getPrice() + "원 이고, 잔여 수량은 " +
            getQuantity() + "개 입니다.");
    }

    public void description(boolean detail) {
        description();
        System.out.println("이 제품의 리뷰는 좋습니다.");
    }
}
