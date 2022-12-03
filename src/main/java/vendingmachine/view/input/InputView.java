package vendingmachine.view.input;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.GoodsDto;
import vendingmachine.exception.ExceptionMessage;
import java.util.List;

public class InputView {

    private static final String REQUEST_HOLDING_AMOUNT = "\n자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String REQUEST_INPUT_AMOUNT = "\n투입 금액을 입력해 주세요.";
    private static final String REQUEST_GOODS_NAME = "구매할 상품명을 입력해 주세요.";

    public static int readHoldingAmount() {
        print(REQUEST_HOLDING_AMOUNT);
        return readPositiveInteger();
    }

    public static List<GoodsDto> readGoods() {
        return null;
    }

    public static int readInputAmount() {
        print(REQUEST_INPUT_AMOUNT);
        return readPositiveInteger();
    }

    public static String readGoodsName() {
        print(REQUEST_GOODS_NAME);
        return read();
    }

    private static int readPositiveInteger() {
        int number = readNumber();
        if (number <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_INTEGER.getMessage());
        }
        return number;
    }

    private static int readNumber() {
        try {
            return Integer.parseInt(read());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_INTEGER.getMessage());
        }
    }

    private static String read() {
        return Console.readLine();
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
