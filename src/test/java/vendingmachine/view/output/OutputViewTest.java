package vendingmachine.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vendingmachine.domain.Coin;
import java.util.LinkedHashMap;

public class OutputViewTest extends PrintTestTool {

    @Test
    void printInputAmountTest() {
        OutputView.printInputAmount(3000);

        assertThat(output()).isEqualTo("투입 금액: 3000원");
    }

    @Test
    void printChangesTest() {
        LinkedHashMap<Coin, Integer> changes = new LinkedHashMap<>();
        changes.put(Coin.COIN_100, 4);
        changes.put(Coin.COIN_50, 1);

        OutputView.printChanges(changes);

        assertThat(output()).contains("잔돈", "100원 - 4개", "50원 - 1개");
    }

    @Test
    void printHoldingCoinsTest() {
        LinkedHashMap<Coin, Integer> coins = new LinkedHashMap<>();
        coins.put(Coin.COIN_500, 0);
        coins.put(Coin.COIN_100, 4);
        coins.put(Coin.COIN_50, 1);
        coins.put(Coin.COIN_10, 0);

        OutputView.printHoldingCoins(coins);

        assertThat(output()).contains(
                "자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개");
    }
}
