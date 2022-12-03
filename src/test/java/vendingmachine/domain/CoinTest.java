package vendingmachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoinTest {

    @DisplayName("가장 금액이 적은 동전을 반환하는 기능")
    @Test
    void getMinimumCoin() {
        Assertions.assertThat(Coin.getMinimumCoin()).isEqualTo(Coin.COIN_10);
    }
}