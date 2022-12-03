package vendingmachine.domain;

import vendingmachine.exception.ExceptionMessage;
import vendingmachine.helper.InitialCoinCreator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VendingMachine {

    private final LinkedHashMap<Coin, Integer> coins;
    private Map<Goods, Integer> goods;
    private int inputPrice = 0;

    VendingMachine(List<Coin> coinTypes, int amount) {
        coins = new InitialCoinCreator(coinTypes).initializeCoinCondition(amount);
    }

    public void enrollGoods(Map<Goods, Integer> goods) {
        if (isGoodsEnrolled()) {
            throw new IllegalArgumentException(ExceptionMessage.GOODS_ALREADY_ENROLLED.getMessage());
        }
        if (isGoodsOverlapped(goods.keySet())) {
            throw new IllegalArgumentException(ExceptionMessage.GOODS_OVERLAPPED.getMessage());
        }
        this.goods = goods;
    }

    public void addInputPrice(int price) {
        inputPrice += price;
    }

    public void buyGoods(String goodsName) {
        for (Goods singleGoods : goods.keySet()) {
            if (singleGoods.isEqualName(goodsName)) {
                buyGoods(singleGoods);
                return;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.GOODS_NOT_EXIST.getMessage());
    }

    private void buyGoods(Goods goods) {
        if (goods.getPrice() > inputPrice) {
            throw new IllegalArgumentException(ExceptionMessage.GOODS_OVER_PRICE.getMessage());
        }
        inputPrice -= goods.getPrice();
    }

    private boolean isGoodsOverlapped(List<Goods> goods) {
        return goods.stream()
                .map(Goods::getName).distinct()
                .count() != goods.size();
    }

    private boolean isGoodsEnrolled() {
        return this.goods != null;
    }

    public LinkedHashMap<Coin, Integer> getCoins() {
        return (LinkedHashMap) coins.clone();
    }

    public int getInputPrice() {
        return inputPrice;
    }
}
