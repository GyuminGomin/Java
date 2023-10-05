package pattern.factoryMethod.before;

public class Main {
    public static void main(String[] args) {
        Cloth item = new Cloth();
        item.name = "T-shirt";
        item.price = 20.00;

        Electronic item2 = new Electronic();
        item2.name = "Laptop";
        item2.price = 1000.00;

        System.out.println("Cloth Item: " + item.name + ", Price: "+ item.price);        System.out.println("Electronic Item: " + item2.name + ", Price: "+ item2.price);

    }
}
