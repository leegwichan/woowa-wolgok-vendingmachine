package vendingmachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.dto.CoinStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MachineCoinTest {

    @DisplayName("금액에 해당하는 랜덤 동전을 생성한다.")
    @Test
    void createRandomCoin() {
        int money = 3000;
        MachineCoin machineCoin = new MachineCoin();
        machineCoin.createRandomCoin(money);
        Map<Integer, Integer> coinStatus = machineCoin.getCoinStatus().getCoinStatus();
        int result = 0;
        for (Integer coin : coinStatus.keySet()) {
            System.out.println(coin +" "+ coinStatus.get(coin));
            result += (coin*coinStatus.get(coin));
        }
        Assertions.assertThat(result).isEqualTo(money);
    }
}