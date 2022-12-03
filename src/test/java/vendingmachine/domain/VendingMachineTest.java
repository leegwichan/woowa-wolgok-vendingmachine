package vendingmachine.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import java.util.List;

public class VendingMachineTest {

    private final List<Goods> NORMAL_GOODS = List.of(
            new Goods("사이다", 100),
            new Goods("콜라", 150));

    VendingMachine newVendingMachine() {
        return new VendingMachine(
                List.of(Coin.COIN_10, Coin.COIN_50), 100);
    }

    @Test
    void enrollGoodsTest_WhenAlreadyEnrolled() {
        VendingMachine vendingMachine = newVendingMachine();
        vendingMachine.enrollGoods(NORMAL_GOODS);

        assertThrows(IllegalArgumentException.class, () -> {
            vendingMachine.enrollGoods(NORMAL_GOODS);
        });
    }

    @Test
    void enrollGoodsTest_WhenGoodsOverLapped() {
        VendingMachine vendingMachine = newVendingMachine();
        List<Goods> overlappedGoods = List.of(
                new Goods("사이다", 100),
                new Goods("사이다", 150));

        assertThrows(IllegalArgumentException.class, () -> {
            vendingMachine.enrollGoods(overlappedGoods);
        });
    }
}
