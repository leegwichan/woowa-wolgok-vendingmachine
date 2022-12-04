package vendingmachine.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import vendingmachine.util.Coin;
import vendingmachine.util.RandomNumberGenerator;

public class VendingMachine {
    private final List<Merchandise> merchandises = new ArrayList<>();
    private final Map<Coin, Integer> coins = new EnumMap<>(Coin.class);

    public VendingMachine(Money money) {
        setCoins(money);
    }

    public void addMerchandises(List<Merchandise> merchandises){
        this.merchandises.addAll(merchandises);
    }

    private Merchandise findMerchandise(String name) {
        return merchandises.stream()
                .filter(merchandise -> merchandise.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 제품이 없습니다."));
    }

    public Money lowestPrice() {
        return merchandises.stream()
                .filter(Merchandise::isAvailable)
                .min(Comparator.comparing(Merchandise::getPrice))
                .orElseThrow(IllegalArgumentException::new)
                .getPrice();
    }

    public void buyMerch(Money money, String name) {
        Merchandise merch = findMerchandise(name);
        money.subtract(merch.getPrice());
        merch.buy();
    }

    public boolean isAvailable() {
        return merchandises.stream().anyMatch(Merchandise::isAvailable);
    }

    private void setCoins(Money money) {
        int totalMoney = money.getMoney();
        while (totalMoney > 0) {
            Coin coin = Coin.findCoin(RandomNumberGenerator.generateRandomNumber());
            if (totalMoney >= coin.amount()) {
                totalMoney -= coin.amount();
                coins.put(coin, coins.getOrDefault(coin, 0) + 1);
            }
        }
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }

    public Map<Coin, Integer> getChange(Money money) {
        Map<Coin, Integer> changes = new EnumMap<>(Coin.class);
        int leftMoney = money.getMoney();
        while (leftMoney > 0) {
            Optional<Coin> maxCoin = maxCoin(leftMoney);
            if (maxCoin.isEmpty()) {
                break;
            }
            leftMoney -= maxCoin.get().amount();
            coins.put(maxCoin.get(), coins.get(maxCoin.get()) - 1);
            changes.put(maxCoin.get(), coins.getOrDefault(maxCoin.get(), 0) + 1);
        }
        return changes;
    }

    private Optional<Coin> maxCoin(int leftMoney) {
        return coins.keySet().stream()
                .filter(coin -> leftMoney > coin.amount())
                .max(Comparator.comparingInt(Coin::amount));
    }
}
