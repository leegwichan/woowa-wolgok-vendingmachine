package vendingmachine.exception;

public enum ExceptionMessage {
    NOT_COIN_EXIST("해당하는 동전이 존재하지 않습니다."),
    AMOUNT_NOT_DIVIDED("금액이 동전 최소 단위로 나누어 떨어지지 않습니다.");

    ExceptionMessage(String message) {
        this.message = message;
    }

    private static final String PREFIX = "[ERROR] ";

    private String message;

    public String getMessage() {
        return PREFIX + message;
    }
}
