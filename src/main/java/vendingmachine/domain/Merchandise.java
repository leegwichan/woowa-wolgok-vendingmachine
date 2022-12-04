package vendingmachine.domain;

public class Merchandise {

    private final String name;
    private final Money price;
    private int stock;

    public Merchandise(String name, Money price, int stock) {
        validateStock(stock);
        validateName(name);
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    private void validateName(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException();
        }
    }

    private void validateStock(int stock) {
        if (stock <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public void buy() {
        if (!isAvailable()) {
            throw new IllegalArgumentException();
        }
        stock--;
    }

    public boolean isAvailable() {
        return stock > 0;
    }

    public Money getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

}
