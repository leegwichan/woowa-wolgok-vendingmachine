package vendingmachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.dto.CoinStatus;

import java.util.HashMap;
import java.util.Map;

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

    @DisplayName("가장 큰 금액으로 잔돈을 반환한다.")
    @Test
    void returnCoin() {
        MachineCoin machineCoin = new MachineCoin();
        CoinStatus coinStatus = machineCoin.returnCoin(1600);
        Map<Integer, Integer> result = coinStatus.getCoinStatus();

        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(500, 3);
        expected.put(100, 1);
        Assertions.assertThat(result).isEqualTo(expected);
    }
}