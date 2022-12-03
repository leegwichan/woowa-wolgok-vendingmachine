package vendingmachine.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public Coin from(int amount) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.amount == amount)
                .findAny()
                .get();
    }

    public static List<Coin> getCoins() {
        return Arrays.stream(Coin.values()).collect(Collectors.toUnmodifiableList());
    }

    public int countOfCoinMakeByMoney(int money) {
        return money / this.amount;
    }

    public int amountOfMoney(int count) {
        return this.amount * count;
    }

    private int getAmount() {
        return amount;
    }

    public static Coin getMinimumCoin() {
        return Arrays.stream(Coin.values())
                .min(Comparator.comparingInt(Coin::getAmount))
                .get();
    }
}