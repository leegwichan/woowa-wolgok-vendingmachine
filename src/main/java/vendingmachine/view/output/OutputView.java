package vendingmachine.view.output;

import vendingmachine.domain.Coin;
import java.util.LinkedHashMap;

public class OutputView {

    private final static String INPUT_AMOUNT_FORM = "투입 금액: %d원";
    private final static String COIN_AMOUNT_FORM = "%d원 - %d개";
    private final static String HOLDING_COINS = "자판기가 보유한 동전";
    private final static String CHANGES = "잔돈";

    public static void printHoldingCoins(LinkedHashMap<Coin, Integer> holdingCoins) {
        print(HOLDING_COINS);
        printCoins(holdingCoins);
    }

    public static void printInputAmount(int inputAmount) {
        print(String.format(INPUT_AMOUNT_FORM, inputAmount));
    }

    public static void printChanges(LinkedHashMap<Coin, Integer> changes) {
        print(CHANGES);
        printCoins(changes);
    }

    private static void printCoins(LinkedHashMap<Coin, Integer> coins) {
        for (Coin coin : coins.keySet()) {
            print(String.format(COIN_AMOUNT_FORM, coin.getAmount(), coins.get(coin)));
        }
    }

    public static void printErrorMessage(Exception exception) {
        print(exception.getMessage());
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
