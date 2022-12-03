package vendingmachine.domain;

import vendingmachine.RandomNumber;
import vendingmachine.domain.dto.CoinStatus;

import java.util.EnumMap;
import java.util.List;

public class MachineCoin {
    private static final int NONE = 0;
    private EnumMap<Coin, Integer> machineCoin = new EnumMap<>(Coin.class);

    public void createRandomCoin(int money) {
        List<Coin> coins = Coin.getCoins();
        for (Coin coin : coins) {
            int coinCount = generateRandomCoinCount(coin, money);
            machineCoin.put(coin, coinCount);
            money = calculateRemainMoney(money, coin, coinCount);
        }
        Coin minimumCoin = Coin.getMinimumCoin();
        machineCoin.put(minimumCoin, machineCoin.get(minimumCoin)+ convertRemainMoney(minimumCoin, money));
    }

    private int generateRandomCoinCount(Coin coin, int money) {
        int maxCount = coin.countOfCoinMakeByMoney(money);
        return RandomNumber.generateRandomCoinCount(0, maxCount);
    }

    private int calculateRemainMoney(int money, Coin coin, int coinCount) {
        return money - coin.amountOfMoney(coinCount);
    }

    private int convertRemainMoney(Coin coin, int money) {
        return coin.countOfCoinMakeByMoney(money);
    }

    public CoinStatus getCoinStatus() {
        return new CoinStatus(machineCoin);
    }

    public CoinStatus returnCoin(int money) {
        EnumMap<Coin, Integer> result = new EnumMap<>(Coin.class);
        List<Coin> coins = Coin.getCoins();
        for (Coin coin : coins) {
            int coinCount = coin.countOfCoinMakeByMoney(money);
            if (coinCount != NONE) {
                result.put(coin, coinCount);
                money = calculateRemainMoney(money, coin, coinCount);
            }
        }
        return new CoinStatus(result);
    }
}