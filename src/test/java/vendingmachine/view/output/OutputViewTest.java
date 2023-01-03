package vendingmachine.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class OutputViewTest extends PrintTestTool {

    @Test
    void printInputAmountTest() {
        OutputView.printInputAmount(3000);

        assertThat(output()).isEqualTo("투입 금액: 3000원");
    }
}
