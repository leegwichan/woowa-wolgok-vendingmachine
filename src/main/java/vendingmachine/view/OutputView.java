package vendingmachine.view;

import vendingmachine.domain.dto.CoinStatus;

import java.util.Map;

public class OutputView {
    private static final String PRINT_COIN_FORMAT = "%d원 - %d개";
    private static final String PRINT_MONEY_FORMAT = "투입 금액: %d원";

    public void printVendingMachineCoin(CoinStatus status) {
        Map<Integer, Integer> coinStatus = status.getCoinStatus();
        coinStatus.keySet().stream().sorted();
        for (Integer coin : coinStatus.keySet()) {
            System.out.printf(PRINT_COIN_FORMAT, coin, coinStatus.get(coin));
        }
    }

    public void printRemainMoney(int money) {
        System.out.printf(PRINT_MONEY_FORMAT, money);
    }
}