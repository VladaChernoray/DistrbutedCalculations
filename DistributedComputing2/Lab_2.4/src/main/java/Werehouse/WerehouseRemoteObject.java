package Werehouse;

import DatabaseConnection.WerehouseDatabaseConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class WerehouseRemoteObject extends WCastRemoteObject implements IWerehouse {
    public WerehouseRemoteObject() throws RemoteException {
        super();
    }

    public synchronized boolean addSection(String name, int type) throws RemoteException {
        return WerehouseDatabaseConnection.addSection(name, type);
    }

    public synchronized boolean addProduct(String country, String name, int price, String sectionName) throws RemoteException {
        return WerehouseDatabaseConnection.addProductToSection(sectionName, country, name, price);
    }

    public synchronized boolean deleteSection(int id) throws RemoteException {
        return WerehouseDatabaseConnection.deleteGroupByID(id);
    }

    public synchronized boolean deleteProduct(int id) throws RemoteException {
        return WerehouseDatabaseConnection.deleteProductByID(id);
    }

    public synchronized boolean changeSectionForProduct(int productID, int newSectionID) throws RemoteException {
        return WerehouseDatabaseConnection.changeSectionForProduct(newSectionID, productID);
    }

    public ArrayList<Product> getProductsInCurrentSection(String sectionName) throws RemoteException {
        return WerehouseDatabaseConnection.getProductsBySectionName(sectionName);
    }

    public ArrayList<Product> getAllProduct() throws RemoteException {
        return WerehouseDatabaseConnection.getAllProduct();
    }

    public ArrayList<Section> getAllSection() throws RemoteException {
        return WerehouseDatabaseConnection.getAllSection();
    }
}
