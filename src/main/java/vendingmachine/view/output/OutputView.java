package vendingmachine.view.output;

import vendingmachine.domain.Coin;
import java.util.LinkedHashMap;

public class OutputView {

    public static void printHoldingCoins(LinkedHashMap<Coin, Integer> holdingCoins) {

    }

    public static void printInputAmount(int inputAmount) {

    }

    public static void printChanges(LinkedHashMap<Coin, Integer> changes) {

    }

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
