package vendingmachine.domain;

import vendingmachine.exception.ExceptionMessage;

public class Goods {

    private static final int PRICE_UNIT = 10;
    private static final int MIN_PRICE = 100;

    private final String name;
    private final int price;

    Goods(String name, int price) {
        validateName(name);
        validatePrice(price);
        this.name = name;
        this.price = price;
    }

    private void validateName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException(ExceptionMessage.GOODS_NAME_NULL.getMessage());
        }
    }

    private void validatePrice(int price) {
        if (price % PRICE_UNIT != 0) {
            throw new IllegalArgumentException(priceUnitErrorMessage());
        }
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException(priceMinimumErrorMessage());
        }
    }

    private String priceUnitErrorMessage() {
        return String.format(ExceptionMessage.PRICE_UNIT_ERROR_FORMAT.getMessage(), PRICE_UNIT);
    }

    private String priceMinimumErrorMessage() {
        return String.format(ExceptionMessage.MIN_PRICE_ERROR_FORMAT.getMessage(), MIN_PRICE);
    }
}
