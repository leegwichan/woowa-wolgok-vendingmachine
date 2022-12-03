package vendingmachine.controller;

import vendingmachine.domain.MachineCoin;
import vendingmachine.view.InputView;

public class VendingMachineController {
    private final InputView inputView = new InputView();
    public void start() {
        int money = inputView.readVendingMachineMoney();

    }
}