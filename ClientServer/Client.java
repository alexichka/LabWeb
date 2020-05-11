package ClientServer;

import Product.DeliveryPoint;
import Product.DeliveryPointInterface;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;


public class Client {
    public static final String UNIQUE_BINDING_NAME = "server.DeliveryPointInterface";

    public static void main(String[] args) {

        try {
            FileWriter writer = null;
            FileReader reader = null;
            try {
                reader = new FileReader(new File(args[0]));
                writer = new FileWriter(new File(args[1]));
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }

            if (reader == null || writer == null)
                return;

            try {

                // получаем доступ к регистру удаленных объектов.
                final Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);

                //Получаем из регистра нужный объект

                DeliveryPointInterface server = (DeliveryPointInterface) registry.lookup("server.DeliveryPointInterface");

                //считываем
                DeliveryPoint readProducts = DeliveryPoint.readProducts(reader, ';');

                //передаем на сервер и удаленно вызов метода на сервере
                DeliveryPoint ansOfServer = server.sortAndSaveUnique(readProducts);

                //записываем в файл тут)
                DeliveryPoint.writeProducts(writer, ansOfServer, ';');

            } catch (RemoteException e) {
                writer.write("Exception: " + e.getMessage());
            } catch (NotBoundException e) {
                writer.write("Object under name " + UNIQUE_BINDING_NAME + " is not binded\n");
                writer.write(e.getMessage());
            } catch (ParseException e) {
                writer.write("Can not read the OrderList from " + args[0]);
                writer.write(e.getMessage());
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
