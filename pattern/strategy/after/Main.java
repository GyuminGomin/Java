package pattern.strategy.after;

public class Main {
    public static void main(String[] args) {
        Customer vip = new Customer("VIP");
        vip.setDiscountStrategy(new VIPDiscountStrategy());
        Customer normal = new Customer("Noraml");
        normal.setDiscountStrategy(new NormalDiscountStrategy());

        double price = 95000.0;

        double discountedPrice_vip = vip.getDiscountedPrice(price);
        double discountedPrice_normal = normal.getDiscountedPrice(price);

        System.out.println("VIP 요금은 " + discountedPrice_vip + "원 입니다.");
        System.out.println("일반 최종 요금은 " + discountedPrice_normal + "원 입니다.");

    }
}
