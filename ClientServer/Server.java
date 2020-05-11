package ClientServer;

import Product.DeliveryPoint;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static final String UNIQUE_BINDING_NAME = "server.DeliveryPointInterface";
    public static void main(String[] args) {

        try {
            DeliveryPoint dp = new DeliveryPoint();
            //dp.sortAndSaveUnique(dp);
            final Registry registry = LocateRegistry.createRegistry(1099);
            Remote stub = UnicastRemoteObject.exportObject(dp, 0);
            registry.bind("server.DeliveryPointInterface", dp);
        } catch ( AlreadyBoundException  | RemoteException e){
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
    }
}
