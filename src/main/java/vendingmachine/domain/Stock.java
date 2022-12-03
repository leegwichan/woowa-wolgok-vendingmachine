package vendingmachine.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Stock {
    private final Map<Product, Integer> stock = new HashMap<>();

    public void addStock(Product product, int amount) {
        stock.put(product, amount);
    }
    public Optional<Product> findByName(String productName) {
        return stock.keySet().stream()
                .filter(product -> product.equals(productName) && isExists(product))
                .findAny();
    }

    private boolean isExists(Product product) {
        if (stock.get(product)>0) {
            return true;
        }
        return false;
    }
}