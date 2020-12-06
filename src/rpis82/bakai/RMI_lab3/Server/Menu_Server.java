package rpis82.bakai.RMI_lab3.Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Menu_Server {
// сервер предоставляет методы

    public static void main(String args[]) {

      try {

          Menu_Impl Meal1 = new Menu_Impl("Coffee", 90);
          Menu_Impl Meal2 = new Menu_Impl("Tartar", 420);
          Menu_Impl Meal3 = new Menu_Impl("Omelet", 130);

            //получаем удаленный объект
            Menu_RMI meal1 = (Menu_RMI) UnicastRemoteObject.exportObject(Meal1, 0);
            Menu_RMI meal2 = (Menu_RMI) UnicastRemoteObject.exportObject(Meal2, 0);
            Menu_RMI meal3 = (Menu_RMI) UnicastRemoteObject.exportObject(Meal3, 0);
            // для передачи удаленного объекта клиентам

           //получаем реест с портом 1099 (по умолчанию) на хосте localhost
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("meal1", meal1); //связываем объект с именем
            registry.bind("meal2", meal2);
            registry.bind("meal3", meal3);

          System.err.println("Server ready!");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

    }
}
