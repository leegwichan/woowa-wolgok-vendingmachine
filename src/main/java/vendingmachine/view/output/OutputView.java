package vendingmachine.view.output;

import vendingmachine.domain.Coin;
import java.util.LinkedHashMap;

public class OutputView {

    private final static String INPUT_AMOUNT_FORM = "투입 금액: %d원";

    public static void printHoldingCoins(LinkedHashMap<Coin, Integer> holdingCoins) {

    }

    public static void printInputAmount(int inputAmount) {
        print(String.format(INPUT_AMOUNT_FORM, inputAmount));
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
