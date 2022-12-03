package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int readVendingMachineMoney() {
        String money = Console.readLine();
        validIsNumber(money);
        return Integer.parseInt(Console.readLine());
    }

    private void validIsNumber(String value) {
        try {
            Integer.parseInt(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해야 합니다.");
        }
    }
}
