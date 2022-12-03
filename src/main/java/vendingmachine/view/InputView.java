package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.constant.Constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> readProductInfo() {
        String input = Console.readLine();
        Arrays.stream(input.split(Constant.PRODUCT_DELIMITER))
                .forEach(this::validProductInfoShape);
        return Arrays.stream(input.split(Constant.PRODUCT_DELIMITER))
                .collect(Collectors.toList());
    }

    private void validProductInfoShape(String info) {
        validPrefixAndSuffix(info);
        validDelimiter(info);
        validProductInfoValueType(info);
    }

    private void validPrefixAndSuffix(String info) {
        if (!(info.startsWith(Constant.PRODUCT_INFO_PREFIX)
                && info.endsWith(Constant.PRODUCT_INFO_SUFFIX))) {
            throw new IllegalArgumentException("[ERROR] 개별 상품은 대괄호로 구분해야 합니다.");
        }
    }

    private void validDelimiter(String info) {
        if (info.split(Constant.PRODUCT_INFO_DELIMITER).length != Constant.PRODUCT_INFO_SIZE) {
            throw new IllegalArgumentException(
                    "[ERROR] 상품은 상품명, 가격, 수량은 쉼표로 구분하여 입력해야 합니다.");
        }
    }

    private void validProductInfoValueType(String info) {
        String[] split = info.split(Constant.PRODUCT_INFO_DELIMITER);
        try {
            validIsNumber(split[Constant.PRODUCT_PRICE_INDEX]);
            validIsNumber(split[Constant.PRODUCT_AMOUNT_INDEX]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 가격과 수량은 숫자로 입력해야 합니다.");
        }
    }

    public int readMoney() {
        String money = Console.readLine();
        validIsNumber(money);
        return Integer.parseInt(money);
    }
}