package rpis82.bakai.RMI_lab3.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

//интерфейс коммуникации клиента и сервера
//Определение интерфейса удаленного объекта
public interface Menu_RMI extends Remote { //расширяем интерфейс remote
    String getMealInfo() throws RemoteException;
    int getPrice() throws RemoteException;
    String getAveragePricePerMeal(LinkedList<Menu_RMI> menu)  throws RemoteException;
}
