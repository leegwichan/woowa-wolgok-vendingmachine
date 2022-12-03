package vendingmachine.domain;

import vendingmachine.exception.ExceptionMessage;
import vendingmachine.helper.InitialCoinCreator;
import java.util.LinkedHashMap;
import java.util.List;

public class VendingMachine {

    private final LinkedHashMap<Coin, Integer> coins;
    private List<Goods> goods;
    private int inputPrice = 0;

    VendingMachine(List<Coin> coinTypes, int amount) {
        coins = new InitialCoinCreator(coinTypes).initializeCoinCondition(amount);
    }

    public void enrollGoods(List<Goods> goods) {
        if (isGoodsEnrolled()) {
            throw new IllegalArgumentException(ExceptionMessage.GOODS_ALREADY_ENROLLED.getMessage());
        }
        if (isGoodsOverlapped(goods)) {
            throw new IllegalArgumentException(ExceptionMessage.GOODS_OVERLAPPED.getMessage());
        }
        this.goods = goods;
    }

    public void addInputPrice(int price) {
        inputPrice += price;
    }

    public LinkedHashMap<Coin, Integer> getCoins() {
        return (LinkedHashMap) coins.clone();
    }

    private boolean isGoodsOverlapped(List<Goods> goods) {
        return goods.stream()
                .map(Goods::getName).distinct()
                .count() != goods.size();
    }

    private boolean isGoodsEnrolled() {
        return this.goods != null;
    }
}
