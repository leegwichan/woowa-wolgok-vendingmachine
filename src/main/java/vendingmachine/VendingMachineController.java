package vendingmachine;

import java.util.List;
import vendingmachine.domain.Merchandise;
import vendingmachine.domain.Money;
import vendingmachine.domain.VendingMachine;
import vendingmachine.util.MerchandiseParser;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        VendingMachine vendingMachine = new VendingMachine(getInputVendingMachineMoney());
        outputView.printCoins(vendingMachine.getCoins());
        vendingMachine.addMerchandises(getInputVendingMachineMerchandises());
        Money purchaseMoney = new Money(inputView.readPurchaseMoney());
        sale(vendingMachine, purchaseMoney);
        outputView.printChange(vendingMachine.getChange(purchaseMoney));
    }

    private void sale(VendingMachine vendingMachine, Money purchaseMoney) {
        while (!vendingMachine.isAvailable() || purchaseMoney.compareTo(vendingMachine.lowestPrice()) > 0) {
            outputView.printLeftMoney(purchaseMoney.getMoney());
            try {
                vendingMachine.buyMerch(purchaseMoney, inputView.readPurchaseMerchandise());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Money getInputVendingMachineMoney() {
        try {
            return new Money(inputView.readVendingMachineMoney());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getInputVendingMachineMoney();
        }
    }

    private List<Merchandise> getInputVendingMachineMerchandises() {
        try {
            return MerchandiseParser.parseMerchandises(inputView.readVendingMachineMerchandises());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getInputVendingMachineMerchandises();
        }
    }
}
