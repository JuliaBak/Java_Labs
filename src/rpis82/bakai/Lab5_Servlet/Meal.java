package Lab5_Servlet;

public class Meal {
     int id, price;
     String name ;

    public Meal(int id, String name, int price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
