package rpis82.bakai.Lab4_DB;

import java.sql.*;
import java.util.Scanner;

public class DB_Connection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner scanner = new Scanner(System.in); //определим сканнер для считывания введенных данных

        // JDBC URL, username and password of MySQL server
        String url = "jdbc:mysql://localhost:3306/menudb?useUnicode=true&serverTimezone=UTC";
        String user = "menudb";
        String password = "menudb";
        Class.forName("com.mysql.cj.jdbc.Driver");

        //соединение с БД
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("You can see the menu now!");
        StatementsDB.selectStatement(connection); //выведем все меню, имеющиеся на данный момент

        // boolean isNextLine = true; проверка введена ли строка
        //Выполняем запросы на запись данных и удаление данных
        consoleInsertAsk(connection, scanner, true);
        consoleDeleteAsk(connection, scanner, true);
        StatementsDB.selectStatement(connection); //выведем  меню
        StatementsDB.getAveragePrice(connection); //выведем средний счет за блюдо

        connection.close();
        scanner.close();
    }

    public static void consoleInsertAsk(Connection connection, Scanner scanner, Boolean isNextLine) throws SQLException {
        //запрос на добавление данных
        System.out.println("\nWould you like to add a new meal? (yes/no)");
        while (isNextLine) {
            if (scanner.next().equals("yes")) { //если пользватель хочет добавить данные
                StatementsDB.insertStatement(connection, scanner); //обращаемся к методу класса StatementsDB на добавление данных в бд

                System.out.println("\nHere you can see the updated menu!");
                StatementsDB.selectStatement(connection); //выведем измененное меню

                System.out.println("\nAdd another one? (yes/no)"); //повторим вопрос
            } else {
                System.out.println("Ok, see you next time! \n"); //если пользователь не хочет изменять данные, выходим
                isNextLine = false;
            }
        }
    }

    public static void consoleDeleteAsk(Connection connection, Scanner scanner, Boolean isNextLine) throws SQLException {
        //запрос на удаление данных
        System.out.println("\nMaybe you wanna delete some meals? (yes/no)");
        while (isNextLine) {
            if (scanner.next().equals("yes")) { //если пользватель хочет удалить данные
                StatementsDB.deleteStat(connection, scanner); //обращаемся к методу класса StatementsDB на удаление данных в бд

                System.out.println("\nHere you can see the updated menu!");
                StatementsDB.selectStatement(connection);//выведем измененное меню

                System.out.println("\nDelete another one? (yes/no)"); //повторим вопрос
            } else {
                System.out.println("Ok, see you next time! \n");//если пользователь не хочет изменять данные, выходим
                isNextLine = false;
            }
        }
    }
}




