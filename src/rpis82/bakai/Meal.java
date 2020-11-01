package rpis82.bakai;

public class Meal {
    private String name, price;

    public Meal(String name,  String price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
}
