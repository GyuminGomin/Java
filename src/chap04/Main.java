package chap04;

public class Main {
    public static void main(String[] args) {
        Food f = new Food("Milk", 1000, "2025-01-01");
        Clothing c = new Clothing("Black T-s", 150000,"M");
        Sellable s = new Food("Black Milk", 2000, "2026-01-01");

        System.out.println(f.toString());
        System.out.println(c.toString());
        System.out.println(f.sell(10));
        System.out.println(s.sell(10));
    }
}
