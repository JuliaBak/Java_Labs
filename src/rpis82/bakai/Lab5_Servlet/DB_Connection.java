package Lab5_Servlet;

import Lab4_DB.StatementsDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DB_Connection {

    // JDBC URL, username and password of MySQL server
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/menudb?useUnicode=true&serverTimezone=UTC";
    static final String DB_USER =  "menudb";
    static final String DB_PASSWORD =  "menudb";
    static final String GET_ALL_MEALS = "SELECT * FROM menu";
    static ArrayList<Meal> meals = new ArrayList<>();

    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {

        Class.forName(JDBC_DRIVER);
        //соединение с БД
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        return connection;
    }

    //получаем все блюда из бд и записываем их в список
    public static void getAllMeals (Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(GET_ALL_MEALS);

        meals.clear();
        while (resultSet.next()) {

            int id_meal = resultSet.getInt(1);
            String name_meal = resultSet.getString(2);
            int price_meal = resultSet.getInt(3);

            Meal meal = new Meal(id_meal, name_meal, price_meal);
            meals.add(meal);

        }

        resultSet.close();
        statement.close();
    }

    public static void closeConnectionDB(Connection connection ) throws SQLException {

        connection.close();
    }

}




