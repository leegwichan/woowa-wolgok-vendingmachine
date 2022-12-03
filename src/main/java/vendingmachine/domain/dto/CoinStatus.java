package vendingmachine.domain.dto;

import vendingmachine.domain.Coin;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class CoinStatus {
    private final EnumMap<Coin, Integer> coinStatus;

    public CoinStatus(EnumMap<Coin, Integer> coinStatus) {
        this.coinStatus = coinStatus;
    }

    public Map<Integer, Integer> getCoinStatus() {
        Map<Integer, Integer> result = new HashMap<>();
        coinStatus.keySet().stream()
                .forEach(coin -> result.put(coin.getAmount(), coinStatus.get(coin)));
        return result;
    }
}