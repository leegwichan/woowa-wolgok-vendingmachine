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
        if (this.goods.get(goods) <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.GOODS_SOLD_OUT.getMessage());
        }
        this.goods.put(goods, this.goods.get(goods) - 1);
        inputPrice -= goods.getPrice();
    }

    public boolean isDoneBuying() {
        return inputPrice < minPriceOfBuyingGoods() || isSoldOut();
    }

    private int minPriceOfBuyingGoods() {
        return goods.keySet().stream()
                .filter(goods1 -> this.goods.get(goods1) != 0)
                .mapToInt(Goods::getPrice)
                .min().orElse(0);
    }

    private boolean isSoldOut() {
        return goods.values().stream()
                .map(count -> count == 0)
                .reduce(true, Boolean::logicalAnd);
    }

    private boolean isGoodsOverlapped(Set<Goods> goods) {
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
