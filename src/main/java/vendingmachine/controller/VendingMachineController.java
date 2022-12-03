package vendingmachine.controller;

import vendingmachine.constant.Constant;
import vendingmachine.domain.Product;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;

import java.util.List;

public class VendingMachineController {

    private final InputView inputView = new InputView();
    private final VendingMachine vendingMachine = new VendingMachine();

    public void init() {
        while (true) {
            try {
                int money = inputView.readVendingMachineMoney();
                vendingMachine.setMachineCoin(money);
                readProductInfo();
                start();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void start() {
        boolean canBuy = true;
        while (canBuy) {
            vendingMachine.sellProduct(inputView.readProductName());
            canBuy = vendingMachine.isCanBuy();
        }
    }

    private void readProductInfo(){
        List<String> productInfo = inputView.readProductInfo();
        for (String info : productInfo) {
            addStock(splitProductInfo(info));
        }
    }
    private String[] splitProductInfo(String info) {
        String substring = info.substring(1, info.length() - 1);
        return substring.split(Constant.PRODUCT_INFO_DELIMITER);
    }

    private void addStock(String[] info) {
        String productName = info[Constant.PRODUCT_NAME_INDEX];
        int productPrice = Integer.parseInt(info[Constant.PRODUCT_PRICE_INDEX]);
        int productAmount = Integer.parseInt(info[Constant.PRODUCT_AMOUNT_INDEX]);
        vendingMachine.addStock(Product.of(productName, productPrice), productAmount);
    }
}