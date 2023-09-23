package chap08;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// able to serializable : ObjectOutputStream
// able to deserialization : ObjectInputStream

public class ProductManager {
    public void saveProduct(Product p, String fileName) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Product loadProduct(String fileName) {
        Product p = null;
        try {
            ObjectInputStream ios = new ObjectInputStream(new FileInputStream(fileName));
            p = (Product) ios.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return p;
    }
}