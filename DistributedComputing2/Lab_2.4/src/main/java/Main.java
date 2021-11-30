import Client.Client;
import Server.ServerRMI;
import Werehouse.Product;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, NotBoundException, ClassNotFoundException {
        Client client = new Client();
        System.out.println(client.addSection("Musical instruments", 1);
        System.out.println(client.addProduct("China", "Keyboard instruments","Yamaha GB1K", 30000);
        System.out.println(client.addSection("Musical instruments", 5);
        System.out.println(client.addProduct("China", "Stringered instruments","Kavai SK-EX EP", 3000);
        ArrayList<Product> products = client.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("List of products : ");
        products = client.getProductsInCurrentSection("Musical instruments");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
