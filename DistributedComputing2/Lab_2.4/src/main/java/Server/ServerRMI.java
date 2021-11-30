package Server;

import Werehouse.WerehouseRemoteObject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {
    public static void main(String [] args) throws RemoteException {
        WerehouseRemoteObject werehouseRemoteObject = new WerehouseRemoteObject();
        Registry registry = LocateRegistry.createRegistry(123);
        registry.rebind("Werehouse",werehouseRemoteObject);
    }
}
