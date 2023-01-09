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
}
