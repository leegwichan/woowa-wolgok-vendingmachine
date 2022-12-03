package vendingmachine.domain;

import vendingmachine.domain.dto.CoinStatus;

import java.util.Optional;

public class VendingMachine {
    private Stock stock = new Stock();
    private MachineCoin machineCoin;
    private int money;
    private boolean canBuy = true;

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
        if (!product.get().canBuy(this.money)) {
            canBuy = false;
            return;
        }
        stock.minusStock(product.get());
        this.money -= product.get().getPrice();
    }

    public CoinStatus returnCoin() {
        return machineCoin.returnCoin(this.money);
    }

    public int getRemainMoney() {
        return this.money;
    }

    public boolean isCanBuy() {
        return canBuy;
    }
}
