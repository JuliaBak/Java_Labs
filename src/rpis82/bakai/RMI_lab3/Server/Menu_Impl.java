package rpis82.bakai.RMI_lab3.Server;

import org.w3c.dom.NodeList;

import java.rmi.RemoteException;
import java.util.LinkedList;

public class Menu_Impl implements Menu_RMI{
    private String name;
    private int price;

    public Menu_Impl(String meal, int price) {
        name = meal;
        this.price = price;
    }

    public String getMealInfo()
    {
        return "Name: " + name + " Price: " + price;
    }

    public int getPrice()
    {
        return price;
    }

    public String getAveragePricePerMeal(LinkedList<Menu_RMI> menu) throws RemoteException {
        int totalPrice = 0; //общая цена за все блюда
        int numberOfMeals = 0; //кол-во блюд

        for(int index = 0; index < menu.size(); index++) {
            //увеличиваем кол-во блюд при нахождении и его цену прибаляем к общей цене меню
            numberOfMeals++;
            totalPrice += (menu.get(index).getPrice());
        }
        //Выводим среднюю цену за блюдо и кол-во блюд
        return  ("\nAverage price per meal: " + (double)totalPrice/numberOfMeals + " Number of meals:" + numberOfMeals);
    }
}
