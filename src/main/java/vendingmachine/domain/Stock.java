package vendingmachine.domain;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private final Map<Product, Integer> stock = new HashMap<>();

    public void addStock(Product product, int amount) {
        stock.put(product, amount);
    }
}