package vendingmachine.helper;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.Coin;
import vendingmachine.exception.ExceptionMessage;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InitialCoinCreator {

    private final List<Coin> coinTypes;
    private LinkedHashMap<Coin, Integer> coins;

    InitialCoinCreator(List<Coin> coinTypes) {
        this.coinTypes = sortCoinType(coinTypes);
    }

    private List<Coin> sortCoinType(List<Coin> coinType) {
        return coinType.stream()
                .sorted(Comparator.comparing(Coin::getAmount).reversed())
                .collect(Collectors.toList());
    }

    public LinkedHashMap<Coin, Integer> initializeCoinCondition(int amount) {
        validateAmount(amount);
        coins = initialCoins();
        List<Integer> sortedCoinAmount = mapCoinAmount(coinTypes);
        setCoins(amount, sortedCoinAmount);

        return coins;
    }

    private void validateAmount(int amount) {
        int minimumCoinType = coinTypes.get(coinTypes.size()- 1).getAmount();
        if (amount % minimumCoinType != 0) {
            throw new IllegalArgumentException(ExceptionMessage.AMOUNT_NOT_DIVIDED.getMessage());
        }
    }

    private LinkedHashMap<Coin, Integer> initialCoins() {
        LinkedHashMap<Coin, Integer> coins = new LinkedHashMap<>();
        for (Coin coin : coinTypes) {
            coins.put(coin, 0);
        }
        return coins;
    }

    private List<Integer> mapCoinAmount(List<Coin> coinType) {
        return coinType.stream()
                .map(Coin::getAmount)
                .collect(Collectors.toList());
    }

    private void setCoins(int amount, List<Integer> sortedCoinAmount) {
        while (amount != 0) {
            removeCoinType(sortedCoinAmount, amount);
            int coinAmount = Randoms.pickNumberInList(sortedCoinAmount);
            Coin coin = getCoin(coinAmount);
            coins.put(coin, coins.get(coin) +1);
            amount -= coinAmount;
        }
    }

    private void removeCoinType(List<Integer> sortedCoinAmount, int amount) {
        while (sortedCoinAmount.get(0) > amount) {
            sortedCoinAmount.remove(0);
        }
    }

    private Coin getCoin(int amount) {
        for (Coin coin : coinTypes) {
            if (coin.getAmount() == amount) {
                return coin;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.NOT_COIN_EXIST.getMessage());
    }
}
