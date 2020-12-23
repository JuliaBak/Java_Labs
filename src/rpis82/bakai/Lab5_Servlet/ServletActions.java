package Lab5_Servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.sql.*;

public class ServletActions extends HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/menudb?useUnicode=true&serverTimezone=UTC";
    static final String DB_USER =  "menudb";
    static final String DB_PASSWORD =  "menudb";
    static final String GET_ALL_MEALS = "SELECT * FROM menu";
    double averagePrice;
    String title = "Some actions done";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Insert Meal";
        String docType = "<!DOCTYPE html>";

        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement statement = connection.createStatement();

           ResultSet resultSet = statement.executeQuery(GET_ALL_MEALS);

            writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
            writer.println("<h1>Changes</h1>");

            String id =  (request.getParameter("id_meal"));
            String name = request.getParameter("mname");
            String price = (request.getParameter("price"));

            int newID = Integer.parseInt(id);
            int newPrice = Integer.parseInt(price);

            String action = request.getParameter("action");

            if(action.equals("Add")) //если нажата кнопка Add, то выводятся сведения о добавленном объекте
            {
                Statements.insertStatement(connection, newID, name, newPrice);
                writer.println("<p>You've added</p>");

                writer.println("<p>ID: " + id + "</p>");
                writer.println("<p>Name: " + name + "</p>");
                writer.println("<p>Price: " + price + "</p></br>");
            }
            else if(action.equals("Delete")) //если нажата кнопка Delete, то выводятся сведения об удаленном объекте
            {
                Statements.deleteStat(connection, name);
                writer.println("<p>You've deleted</p>");

                writer.println("<p>ID: " + id + "</p>");
                writer.println("<p>Name: " + name + "</p>");
                writer.println("<p>Price: " + price + "</p></br>");
            }
            else if(action.equals("Show")) //если нажата кнопка Show, то выводятся сведения о всех объектах в БД
            {
                writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
                writer.println("<h1>Menu</h1>");
                writer.println("<br/>");
                writer.println("<table border=\"1px\" id=\"table\">\n" +
                        "    <head id=\"head\"> <td class=\"top\">№</td>  <td class=\"top\">Meal</td> <td class=\"top\">Price</td> </head>");
                while (resultSet.next()) {

                    int id_meal = resultSet.getInt(1);
                    String name_meal = resultSet.getString(2);
                    int price_meal = resultSet.getInt(3);

                    writer.println(" <tr> <td> " + id_meal + "</td> <td>" + name_meal + "</td>" +  "<td>" + price_meal
                            + "</td></tr>");

                }
                writer.println("</table> <br/>");

                averagePrice = Statements.getAveragePrice(connection);
                writer.println("<p>Average price: " + averagePrice + "</p>");
            }

             resultSet.close();
               statement.close();
                connection.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
                    writer.println("</body></html>");
        }
    }

