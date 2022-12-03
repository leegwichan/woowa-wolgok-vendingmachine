package vendingmachine.util;

import java.util.Arrays;
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

    public int amount() {
        return amount;
    }

    public static List<Integer> amounts() {
        return Arrays.stream(Coin.values())
                .mapToInt(Coin::amount)
                .boxed()
                .collect(Collectors.toList());
    }

    public static Coin findCoin(int amount) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.amount() == amount)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    // 추가 기능 구현
}
