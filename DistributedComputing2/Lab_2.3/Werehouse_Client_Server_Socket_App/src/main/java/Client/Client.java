package Client;

import Werehouse.Section;
import Werehouse.Product;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket sock;
    private DataOutputStream out;
    private DataInputStream in;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Client(String ip, int port) throws IOException {
        sock = new Socket(ip, port);
        in = new DataInputStream(sock.getInputStream());
        out = new DataOutputStream(sock.getOutputStream());
        objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
        objectInputStream = new ObjectInputStream(sock.getInputStream());
    }

    public boolean addSection(String name, int number) throws IOException {
        Query operation = Query.addSection;
        objectOutputStream.writeObject(operation);
        out.writeUTF(name);
        out.writeInt(number);
        return in.readBoolean();
    }

    public boolean deleteSection(int sectionID) throws IOException {
        Query operation = Query.deleteGroup;
        objectOutputStream.writeObject(operation);
        out.writeInt(sectionID);
        return in.readBoolean();
    }

    public boolean addProduct(String country, String name, int price, String sectionName) throws IOException {
        Query operation = Query.addProduct;
        objectOutputStream.writeObject(operation);
        out.writeUTF(country);
        out.writeUTF(name);
        out.writeInt(price);
        out.writeUTF(sectionName);
        return in.readBoolean();
    }

    public boolean deleteProduct(int productID) throws IOException {
        Query operation = Query.deleteProduct;
        objectOutputStream.writeObject(operation);
        out.writeInt(productID);
        return in.readBoolean();
    }

    public boolean changeSectionForProduct(int productID,int newSectionID) throws IOException {
        Query operation = Query.changeSectionForProduct;
        objectOutputStream.writeObject(operation);
        out.writeInt(productID);
        out.writeInt(newSectionID);
        return in.readBoolean();
    }

    public ArrayList<Product> getProductsInCurrentSection(String sectionName) throws IOException, ClassNotFoundException {
        Query operation = Query.changeSectionForProduct;
        objectOutputStream.writeObject(operation);
        out.writeUTF(sectionName);
        return (ArrayList<Product>)objectInputStream.readObject();
    }

    public ArrayList<Product> getAllProducts() throws IOException, ClassNotFoundException {
        Query operation = Query.getProducts;
        objectOutputStream.writeObject(operation);
        return (ArrayList<Product>)objectInputStream.readObject();
    }

    public ArrayList<Section> getAllSections() throws IOException, ClassNotFoundException {
        Query operation = Query.getSections;
        objectOutputStream.writeObject(operation);
        return (ArrayList<Section>)objectInputStream.readObject();
    }

    public void disconnect() throws IOException {
        sock.close();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 12345);
            System.out.println(client.addSection("Name_1",3));//6
            System.out.println(client.addProduct("Ukraine","ProductName1",20,"Name_1"));//1
            System.out.println(client.addProduct("Italy","ProductName2",20,"Name_1"));//2
            System.out.println(client.addSection("Name_2",3));//7
            System.out.println(client.addProduct("Ukraine","ProductName3",20,"Name_2"));//3
            System.out.println(client.addProduct("USA","ProductName4",20,"Name_2"));//4
            System.out.println(client.changeSectionForProduct(4,6));
            ArrayList<Product>products = client.getAllProducts();
            for(Product product:products){
                System.out.println(product);
            }
            System.out.println(client.deleteSection(7);
            products = client.getAllProducts();
            for(Product product:products){
                System.out.println(product);
            }
            client.disconnect();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection error");
        }
    }
}
