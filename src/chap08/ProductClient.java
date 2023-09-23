package chap08;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ProductClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            Product p = new Product("Apple", "1500");

            oos.writeObject(p);
            System.out.println("Product sent to the server");

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
