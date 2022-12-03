package vendingmachine;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Goods;
import vendingmachine.domain.VendingMachine;
import vendingmachine.dto.GoodsDto;
import vendingmachine.mapper.Mapper;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;
import java.util.List;
import java.util.Map;

public class VendingMachineApplication {

    private static final List<Coin> COIN_TYPE = List.of(Coin.COIN_500, Coin.COIN_100, Coin.COIN_50, Coin.COIN_10);
    private VendingMachine vendingMachine;

    public void run() {
        createVendingMachine();
        printHoldingCoins();
        enrollGoods();
        inputAmounts();
        while (!vendingMachine.isDoneBuying()) {
            buyGoods();
        }
        printChanges();
    }

    private void createVendingMachine() {
        int holdingAmount = InputView.readHoldingAmount();
        vendingMachine = new VendingMachine(COIN_TYPE, holdingAmount);
    }

    private void printHoldingCoins() {
        OutputView.printHoldingCoins(vendingMachine.getCoins());
    }

    private void enrollGoods() {
        List<GoodsDto> goodsDtos = InputView.readGoods();
        Map<Goods, Integer> goods = Mapper.goodsDtosToGoods(goodsDtos);
        vendingMachine.enrollGoods(goods);
    }

    private void inputAmounts() {
        int inputAmount = InputView.readInputAmount();
        vendingMachine.addInputPrice(inputAmount);
    }

    private void buyGoods() {
        OutputView.printInputAmount(vendingMachine.getInputPrice());
        String goodsName = InputView.readGoodsName();
        vendingMachine.buyGoods(goodsName);
    }

    private void printChanges() {
        OutputView.printChanges(vendingMachine.getChanges());
    }
}
