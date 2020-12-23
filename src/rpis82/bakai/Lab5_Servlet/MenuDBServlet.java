package Lab5_Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MenuDBServlet extends HttpServlet {

    // JDBC URL, username and password of MySQL server
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/menudb?useUnicode=true&serverTimezone=UTC";
    static final String DB_USER =  "menudb";
    static final String DB_PASSWORD =  "menudb";
    static final String GET_ALL_MEALS = "SELECT * FROM menu";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();

            String title = "Menu_Meals";
            String docType = "<!DOCTYPE html>";

            try {
                Class.forName(JDBC_DRIVER);
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(GET_ALL_MEALS);

                writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
                writer.println("<h1>Menu</h1>");
                writer.println("<br/>");
                writer.println("<table border=\"1px\" id=\"table\">\n" +
                        "    <head id=\"head\"> <td class=\"top\">№</td>  <td class=\"top\">Meal</td> <td class=\"top\">Price</td> <td class=\"top\"></td> </head>");
                while (resultSet.next()) {

                    int id_meal = resultSet.getInt(1);
                    String name_meal = resultSet.getString(2);
                    int price_meal = resultSet.getInt(3);


                    writer.println(" <tr> <td> " + id_meal + "</td> <td>" + name_meal + "</td>" +  "<td>" + price_meal
                            + "</td>"+"<td><input type=\"checkbox\" class=\"boxes\" name=\"check\"></td> </tr>");

                }
                writer.println("</table> <br/>");

                writer.println("<form name=\"mainform\" action=\"insert_delete_item\" method=\"post\">\n" +
                        "    ID:\n" +
                        "    <input type=\"text\" name=\"id_meal\"/><br/>\n" +
                        "    Meal:\n" +
                        "    <input type=\"text\" name=\"mname\"/><br/>\n" +
                        "    Price:\n" +
                        "    <input type=\"text\" name=\"price\"/><br/>\n" +
                        "    <br/>\n" +
                        "    <input type=\"submit\" name=\"action\" value=\"Add\">\n" +
                        "    <input type=\"submit\" name=\"action\" value=\"Delete\">\n" +
                        "    <input type=\"submit\" name=\"action\" value=\"Show\">\n" +
                        "</form>");

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

