package chap08;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ProductServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("Server is running");

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                    Product p = (Product) ois.readObject();
                    System.out.println("Received: " + p);

                    ProductManager pm = new ProductManager();
                    pm.saveProduct(p, "./src/chap08/receive.txt");

                    System.out.println("Product saved successfully");

                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

        }
    }
}
