package vendingmachine.view.output;

import vendingmachine.domain.Coin;
import java.util.LinkedHashMap;

public class OutputView {

    private final String INPUT_AMOUNT = "투입 금액 : ";

    public static void printHoldingCoins(LinkedHashMap<Coin, Integer> holdingCoins) {

    }

    public static void printInputAmount(int inputAmount) {

    }

    public static void printChanges(LinkedHashMap<Coin, Integer> changes) {

    }

    public static void printErrorMessage(Exception exception) {
        print(exception.getMessage());
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
