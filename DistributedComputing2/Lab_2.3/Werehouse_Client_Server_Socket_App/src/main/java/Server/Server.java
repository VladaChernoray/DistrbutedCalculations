package Server;

import Client.Query;
import DatabaseConnection.WerehouseDatabaseConnection;
import Werehouse.Section;
import Werehouse.Product;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket server = null;
    private Socket sock = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;

    public void start(int port) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        while (true) {
            sock = server.accept();
            in = new DataInputStream(sock.getInputStream());
            objectInputStream = new ObjectInputStream(sock.getInputStream());
            objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
            out = new DataOutputStream(sock.getOutputStream());
            while (true) {
                processQuery();
            }
        }
    }

    private void processQuery() throws IOException, ClassNotFoundException {
        Query operation = (Query) objectInputStream.readObject();
        if (operation.equals(Query.addSection)) {
            out.writeBoolean(addSection(in.readUTF(), in.readInt()));
        } else if (operation.equals(Query.addProduct)) {
            out.writeBoolean(addProduct(in.readUTF(), in.readUTF(), in.readInt(), in.readUTF()));
        }else if (operation.equals(Query.deleteSection)){
            out.writeBoolean(deleteSection(in.readUTF()));
        }else if (operation.equals(Query.deleteProduct)){
            out.writeBoolean(deleteProduct(in.readInt()));
        }else if (operation.equals(Query.changeSectionForProduct)){
            out.writeBoolean(changeSectionForProduct(in.readInt(),in.readInt()));
        }else if (operation.equals(Query.getProductsInCurrentSection)){
            objectOutputStream.writeObject(getProductInCurrentSection(in.readUTF()));
        }else if (operation.equals(Query.getProduct)){
            objectOutputStream.writeObject(getAllProducts());
        }else if (operation.equals(Query.getSections)){
            objectOutputStream.writeObject(getAllSections());
        }
    }

    private boolean addSection(String name, int number) {
        return WerehouseDatabaseConnection.addSection(name, number);
    }

    private boolean addProduct(String country, String name, int price, String sectionName) {
        return WerehouseDatabaseConnection.addProductInSection(sectionName, country, name, price);
    }
    private boolean deleteSection(String name) {
        return WerehouseDatabaseConnection.deleteSectionByName(name);
    }

    private boolean deleteProduct(int id) {
        return WerehouseDatabaseConnection.deleteProductByID(id);
    }

    public boolean changeSectionForProduct(int productID,int newSectionID){
        return WerehouseDatabaseConnection.changeSectionForProduct(newSectionID,productID);
    }

    public ArrayList<Product> getProductsInCurrentSection(String sectionName){
        return WerehouseDatabaseConnection.getProductsBySectionName(sectionName);
    }

    public ArrayList<Product> getAllProducts(){
        return WerehouseDatabaseConnection.getAllProducts();
    }

    public ArrayList<Section> getAllSections(){
        return WerehouseDatabaseConnection.getAllSections();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();
        server.start(12345);
    }

}