package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineTest {

    private final Map<Goods, Integer> NORMAL_GOODS;

    VendingMachineTest() {
        NORMAL_GOODS = new HashMap();
        NORMAL_GOODS.put(new Goods("사이다", 100), 1);
        NORMAL_GOODS.put(new Goods("콜라", 150), 1);
    }

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
        Map<Goods, Integer> overlappedGoods = new HashMap<>();
        overlappedGoods.put(new Goods("사이다", 100), 1);
        overlappedGoods.put(new Goods("사이다", 150), 1);

        assertThrows(IllegalArgumentException.class, () -> {
            vendingMachine.enrollGoods(overlappedGoods);
        });
    }

    @Test
    void buyGoodsTest_WhenNotExist() {
        VendingMachine vendingMachine = newVendingMachine();
        vendingMachine.enrollGoods(NORMAL_GOODS);
        vendingMachine.addInputPrice(500);

        assertThrows(IllegalArgumentException.class, () -> {
            vendingMachine.buyGoods("사탕");
        });
    }

    @Test
    void buyGoodsTest_OverPrice() {
        VendingMachine vendingMachine = newVendingMachine();
        vendingMachine.enrollGoods(NORMAL_GOODS);
        vendingMachine.addInputPrice(50);

        assertThrows(IllegalArgumentException.class, () -> {
            vendingMachine.buyGoods("콜라");
        });
    }

    @Test
    void buyGoodsTest_NormalCase() {
        VendingMachine vendingMachine = newVendingMachine();
        vendingMachine.enrollGoods(NORMAL_GOODS);
        vendingMachine.addInputPrice(500);

        vendingMachine.buyGoods("콜라");

        assertThat(vendingMachine.getInputPrice()).isEqualTo(350);
    }
}
