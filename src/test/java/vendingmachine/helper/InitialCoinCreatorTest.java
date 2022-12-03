package vendingmachine.helper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.Coin;
import java.util.LinkedHashMap;
import java.util.List;

public class InitialCoinCreatorTest extends MockRandoms{

    @Test
    void initializeCoinConditionTest() {
        List<Coin> coinTypes = List.of(Coin.COIN_100, Coin.COIN_10, Coin.COIN_50, Coin.COIN_500);
        int amount = 370;
        when(Randoms.pickNumberInList(anyList())).thenReturn(100,100,100,10,50,10);

        LinkedHashMap<Coin, Integer> result = new InitialCoinCreator(coinTypes).initializeCoinCondition(amount);

        assertThat(result.get(Coin.COIN_500)).isEqualTo(0);
        assertThat(result.get(Coin.COIN_100)).isEqualTo(3);
        assertThat(result.get(Coin.COIN_10)).isEqualTo(2);
    }
}
