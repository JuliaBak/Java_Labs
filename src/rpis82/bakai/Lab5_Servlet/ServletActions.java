package Lab5_Servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.sql.*;

public class ServletActions extends HttpServlet {

    double averagePrice;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Insert Meal";
        String docType = "<!DOCTYPE html>";

        try {
            Connection connection = DB_Connection.getDBConnection();

            DB_Connection.getAllMeals(connection);

            writer.println(docType + "<html><head><title>" + title + "</title><link rel=\"stylesheet\" type=\"text/css\" href=\"myCSSTemp.css\"></head><body>");
            writer.println("<h1>Changes</h1>");

            String id =  (request.getParameter("id_meal"));
            String name = request.getParameter("mname");
            String price = (request.getParameter("price"));

            String action = request.getParameter("action");

            if(action.equals("Add")) //если нажата кнопка Add, то выводятся сведения о добавленном объекте
            {
                Statements.insertStatement(connection, Integer.parseInt(id), name, Integer.parseInt(price));
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

                for (Meal meal: DB_Connection.meals
                ) {
                    writer.println(" <tr> <td> " + meal.id + "</td> <td>" + meal.name + "</td>" +  "<td>" +  meal.price
                            + "</td></tr>");

                }
                writer.println("</table> <br/>");

                averagePrice = Statements.getAveragePrice(connection);
                writer.println("<p>Average price: " + averagePrice + "</p>");
            }

                DB_Connection.closeConnectionDB(connection);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
                    writer.println("</body></html>");
        }
    }

