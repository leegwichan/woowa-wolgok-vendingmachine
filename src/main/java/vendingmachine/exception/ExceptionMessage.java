package vendingmachine.exception;

public enum ExceptionMessage {
    NOT_COIN_EXIST("해당하는 동전이 존재하지 않습니다."),
    AMOUNT_NOT_DIVIDED("금액이 동전 최소 단위로 나누어 떨어지지 않습니다."),
    GOODS_NAME_NULL("이름이 빈칸이 아니어야 합니다."),
    PRICE_UNIT_ERROR_FORMAT("상품 금액의 최소 단위는 %s 이어야 합니다."),
    MIN_PRICE_ERROR_FORMAT("상품 금액은 %s 보다 크거나 같아야 합니다."),
    GOODS_ALREADY_ENROLLED("상품이 이미 등록되어 있습니다."),
    GOODS_OVERLAPPED("상품의 이름이 중복되었습니다."),
    GOODS_NOT_EXIST("상품이 존재하지 않습니다."),
    GOODS_OVER_PRICE("상품 가격이 투입 금액보다 높습니다."),
    GOODS_SOLD_OUT("해당 상품은 이미 품절되었습니다."),

    NOT_INTEGER("양수를 입력하여야 합니다."),
    ;

    ExceptionMessage(String message) {
        this.message = message;
    }

    private static final String PREFIX = "[ERROR] ";

    private String message;

    public String getMessage() {
        return PREFIX + message;
    }
}
