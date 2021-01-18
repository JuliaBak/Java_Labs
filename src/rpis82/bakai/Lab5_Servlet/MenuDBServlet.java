package Lab5_Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MenuDBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();

            String title = "Menu_Meals";
            String docType = "<!DOCTYPE html>";

            try {
                Connection connection = DB_Connection.getDBConnection();

                writer.println(docType + "<html><head><title>" + title + "</title> <link rel=\"stylesheet\" type=\"text/css\" href=\"myCSSTemp.css\"></head><body>");
                writer.println("<h1>Menu</h1>");
                writer.println("<br/>");
                writer.println("<table border=\"1px\" id=\"table\">\n" +
                        "    <head id=\"head\"> <td class=\"top\">№</td>  <td class=\"top\">Meal</td> <td class=\"top\">Price</td> <td class=\"top\"></td> </head>");

                DB_Connection.getAllMeals(connection);

                for (Meal meal: DB_Connection.meals
                     ) {
                    writer.println(" <tr> <td> " + meal.id + "</td> <td>" + meal.name + "</td>" +  "<td>" + meal.price
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

             DB_Connection.closeConnectionDB(connection);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            writer.println("</body></html>");
        }

}

