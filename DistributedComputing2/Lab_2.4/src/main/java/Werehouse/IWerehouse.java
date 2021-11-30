package Werehouse;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IWerehouse extends Remote {
    boolean addSection(String name, int ttpe ) throws RemoteException;

    boolean addProduct(String country, String name, int price, String sectionName) throws RemoteException;

    boolean deleteSection(int id) throws RemoteException;

    boolean deleteProduct(int id) throws RemoteException;

    boolean changeSectionForProduct(int productID, int newSectionID) throws RemoteException;

    ArrayList<Product> getProductInCurrentSection(String sectionName) throws RemoteException;

    ArrayList<Product> getAllProducts() throws RemoteException;

    ArrayList<Section> getAllSections() throws RemoteException;
}
