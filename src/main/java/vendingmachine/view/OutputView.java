package vendingmachine.view;

import java.util.Map;
import vendingmachine.util.Coin;

public class OutputView {
    public void printCoins(Map<Coin, Integer> coins) {
        System.out.println("자판기가 보유한 동전");
        StringBuilder sb = new StringBuilder();
        for (Coin coin : Coin.values()) {
            sb.append(coin.amount()).append("원 - ").append(coins.getOrDefault(coin, 0)).append("개\n");
        }

        System.out.println(sb);
    }

    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void printLeftMoney(int money) {
        System.out.printf("\n투입 금액: %d원\n", money);
    }

    public void printChange(Map<Coin, Integer> changes) {
        System.out.println("잔돈");
        StringBuilder sb = new StringBuilder();
        for (Coin coin : changes.keySet()) {
            sb.append(coin.amount()).append("원 - ").append(changes.getOrDefault(coin, 0)).append("개\n");
        }
        System.out.println(sb);
    }
}
