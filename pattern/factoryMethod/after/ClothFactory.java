package pattern.factoryMethod.after;

public class ClothFactory implements ProductFactory{

    @Override
    public Product createProduct(String name, double price) {
        Product result = new Cloth();
        result.name = name;
        result.price = price;
        return result;
    }
    
}
