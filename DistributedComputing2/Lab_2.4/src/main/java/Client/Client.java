package Client;

import Werehouse.Section;
import Werehouse.IWerehouse;
import Werehouse.Product;

import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.ArrayList;

public class Client {
    private IWerehouse werehouseRemote;
    private String remoteURL = "//127.0.0.1:123/Werehouse";

    public Client() throws IOException, NotBoundException {
        werehouseRemote = (IWerehouse) Naming.lookup(remoteURL);
        System.out.println("RMI object found");
    }

    public boolean addSection(String name, int type) throws IOException {
        return werehouseRemote.addSection(name, type);
    }

    public boolean deleteSection(int sectionID) throws IOException {
        return werehouseRemote.deleteSection(sectionID);
    }

    public boolean addProduct(String country, String name, int price, String sectionName) throws IOException {
        return werehouseRemote.addProduct(country, name, price, sectionName);
    }

    public boolean deleteProduct(int productID) throws IOException {
        return werehouseRemote.deleteProduct(productID);
    }

    public boolean changeSectionForProduct(int productID, int newSectionID) throws IOException {
        return werehouseRemote.changeSectionForStudent(productID, newSectionID);
    }

    public ArrayList<Product> getProductsInCurrentSection(String sectionName) throws IOException, ClassNotFoundException {
        return werehouseRemote.getProductsInCurrentSection(sectionName);
    }

    public ArrayList<Product> getAllProducts() throws IOException, ClassNotFoundException {
        return werehouseRemote.getAllProducts();
    }

    public ArrayList<Section> getAllSections() throws IOException, ClassNotFoundException {
        return werehouseRemote.getAllSections();
    }
}
