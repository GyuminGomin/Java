package pattern.observer.after;

public class Main {
    public static void main(String[] args) {
        Product product = new Product("샤오미 패드", 100);
        Product product2 = new Product("삼성 갤럭시 탭", 10);
        Customer customer1 = new Customer("철수");
        Customer customer2 = new Customer("영희");
        product.registerObserver(customer1);
        product.registerObserver(customer2);
        product2.registerObserver(customer2);
        product.removeObserver(customer2);

        product.changeStock(0);
        product.changeStock(500);
        product2.changeStock(5);
    }
}
