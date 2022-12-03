package vendingmachine.domain;

import java.util.Optional;

public class VendingMachine {
    private Stock stock = new Stock();
    private MachineCoin machineCoin;
    private int money;

    public void addStock(Product product, int amount) {
        stock.addStock(product, amount);
    }

    public void setMachineCoin(int money) {
        this.machineCoin = new MachineCoin(money);
    }

    public void insertMoney(int money) {
        this.money = money;
    }

    public void sellProduct(String productName) {
        Optional<Product> product = stock.findByName(productName);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 해당 상품이 존재하지 않습니다.");
        }
        if (product.get().canBuy(this.money)) {
            stock.minusStock(product.get());
            this.money -= product.get().getPrice();
        }
    }
}
