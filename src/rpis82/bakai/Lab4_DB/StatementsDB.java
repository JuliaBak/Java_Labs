package rpis82.bakai.Lab4_DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StatementsDB {

    static String mealName;
    static int mealPrice, mealID;


    public static void selectStatement(Connection connection) throws SQLException {
        String selectMeals = "SELECT * FROM menu";
        Statement statSelect = connection.createStatement();

        ResultSet resultSelect = statSelect.executeQuery(selectMeals);
        try {
            while(resultSelect.next())
            {
                System.out.print(resultSelect.getInt("id_meal") +" ");
                System.out.print(resultSelect.getString( "name_meal") +" "); //выводим название блюда
                System.out.print(resultSelect.getInt("price_meal") +" \n"); //выводим цену блюда
            }
        }
        catch (NullPointerException exert)
        {
            System.out.println("There is no stuff");
        }

        resultSelect.close();
        statSelect.close();
    }

    public static void insertStatement(Connection connection, Scanner scanner) throws SQLException {
        String insertMeal;
        Statement statInsert = connection.createStatement();

        System.out.println("Enter meal's id:");
        mealID = scanner.nextInt();

        System.out.println("Please enter meal's name:"); //считываем введенное название блюда
        mealName = scanner.next();

        System.out.println("Enter meal's price:"); //считываем введенное цену блюда
        mealPrice = scanner.nextInt();

        // выполняем запрос на введение данных
        insertMeal = "INSERT INTO menudb.menu (id_meal, name_meal, price_meal) VALUES ('"+ mealID + "', '" + mealName + "', '" + mealPrice +"');";

        //выведем подтверждение изменения столбца
        int rows = statInsert.executeUpdate(insertMeal);
        System.out.printf(" Updated %d rows - true \n", rows);

        statInsert.close();

    }

    public static void deleteStat(Connection connection, Scanner scanner) throws SQLException {
        String deleteS;
        Statement deleteStat = connection.createStatement();

        System.out.println("Please enter meal's name:");
        mealName = scanner.next(); //считываем название юлда, которое хотим удалить

        deleteS = "DELETE FROM menudb.menu WHERE name_meal='" + mealName + "';";

        int rows = deleteStat.executeUpdate(deleteS);
        System.out.printf("Deleted %d rows \n", rows);

        deleteStat.close();
    }

    public static void getAveragePrice(Connection connection) throws SQLException {
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
        System.out.println("Average price per meal: " + averagePrice);

        getPriceStat.close();
    }
}

