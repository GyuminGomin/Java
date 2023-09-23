package chap08;

public class Main {
    public static void main(String[] args) {
        ProductManager pm = new ProductManager();
        pm.saveProduct(new Product("Milk", "1000"), "./src/chap08/product.txt");

        Product p = null;
        p = pm.loadProduct("./src/chap08/product.txt");
        System.out.println(p);
    }
}
