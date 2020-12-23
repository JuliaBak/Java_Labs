package Lab5_Servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Statements {

    static String mealName;
    static int mealPrice, mealID;


    public static void insertStatement(Connection connection, int id_meal, String meal_name, int price_meal) throws SQLException {
        String insertMeal;
        Statement statInsert = connection.createStatement();

        mealID = id_meal;
        //считываем введенное название блюда
        mealName = meal_name;
         //считываем введенное цену блюда
        mealPrice = price_meal;

        // выполняем запрос на введение данных
        insertMeal = "INSERT INTO menudb.menu (id_meal, name_meal, price_meal) VALUES ('"+ mealID + "', '" + mealName + "', '" + mealPrice +"');";

        //выведем подтверждение изменения столбца
        statInsert.executeUpdate(insertMeal);
        statInsert.close();

    }

    public static void deleteStat(Connection connection, String mealN) throws SQLException {
        String deleteS;
        Statement deleteStat = connection.createStatement();

        mealName = mealN; //считываем название юлда, которое хотим удалить

        deleteS = "DELETE FROM menudb.menu WHERE name_meal='" + mealName + "';";

        deleteStat.executeUpdate(deleteS);

        deleteStat.close();
    }

    public static double getAveragePrice(Connection connection) throws SQLException {
        //вывод средней цены за блюдо
        double averagePrice;
        int count = 0;
        int totalPrice = 0;

        Statement getPriceStat = connection.createStatement();
        String selectMeals = "SELECT * FROM menu";
        ResultSet resultSelect = getPriceStat.executeQuery(selectMeals);

        while(resultSelect.next())
        {
            count++;
            totalPrice += (resultSelect.getInt("price_meal")); //выводим цену блюда
        }

        averagePrice = (double) totalPrice/count;

        getPriceStat.close();
        return  averagePrice;
    }

}

