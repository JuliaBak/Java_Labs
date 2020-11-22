package rpis82.bakai.RMI_lab3.Client;

import rpis82.bakai.RMI_lab3.Server.Menu_RMI;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

public class Menu_Client {
    private Menu_Client() {}

    //на клиенте вызываем методы
    public static void main(String[] args) throws RemoteException {
        //Запуск менеджера безопасности
     System.setProperty("java.security.policy", "client.policy");
    System.setSecurityManager(new RMISecurityManager());

        String host = "localhost";
        Registry registry = LocateRegistry.getRegistry(host);//создаем ссылку на удаленный реестр
        try {
            //создадим список блюд - меню
            LinkedList<Menu_RMI> menu = new LinkedList<>();
            //имя, по которому будем находить объект
            String bindingName;
            for (int i = 0; i < registry.list().length; i++ )
            {
                bindingName = "meal" + Integer.toString(i+1); //получаем имя объекта
                Menu_RMI meal = (Menu_RMI) registry.lookup(bindingName); //находим этот удаленный объект
                menu.add(meal);
                System.out.println(meal.getMealInfo()); //выводим информацию о нем
                //получаем информацию о меню, среднюю стоимость цен и кол-во блюд в нем
                System.out.println(meal.getAveragePricePerMeal(menu));

            }

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
