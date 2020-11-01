package rpis82.bakai;

public class Product {
    private String name, cost_price;

    public Product(String name, String cost_price) {
        this.name = name;
        this.cost_price = cost_price;
    }

    public String getName() {
        return name;
    }

    public String getCost_Price() {
        return cost_price;
    }
}
