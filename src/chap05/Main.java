package chap05;

public class Main {
    public static void main(String[] args) {
        Inventory<Product> lotteGangnam = new Inventory<>();
        lotteGangnam.addStock(new Food("매일우유", 1500, "2023-12-31"));
        lotteGangnam.addStock(new Clothing("블랙티셔츠", 12000, "M"));
        lotteGangnam.addStock(new Food("도라에몽초코우유", 800, "2023-12-31"));

        System.out.println(lotteGangnam.search("매일우유").toString());
    }
}