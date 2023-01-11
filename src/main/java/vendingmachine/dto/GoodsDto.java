package vendingmachine.dto;

public class GoodsDto {

    private final String name;
    private final int price;
    private final int amount;

    public GoodsDto(String name, int price, int amount) {
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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof GoodsDto
                && ((GoodsDto) o).getName().equals(name)
                && ((GoodsDto) o).getPrice() == price
                && ((GoodsDto) o).getAmount() == amount) {
            return true;
        }
        return false;
    }
}
