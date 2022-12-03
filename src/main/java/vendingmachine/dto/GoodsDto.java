package vendingmachine.dto;

public class GoodsDto {

    private final String name;
    private final int price;
    private final int amount;

    GoodsDto(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
