package vendingmachine.domain;

public final class Money implements Comparable<Money> {
    private static final int MONEY_UNIT = 10;
    private int money;

    public Money(int money) {
        this.money = money;
    }

    public Money(String money) {
        try {
            validate(Integer.parseInt(money));
            this.money = Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금액은 숫자여야 합니다.");
        }
    }

    private void validate(int money) {
        isPositive(money);
        isRightUnit(money);
    }

    public void subtract(Money money) {
        if (this.compareTo(money) < 0) {
            throw new IllegalArgumentException("금액이 모자랍니다.");
        }
        this.money -= money.money;
    }

    private void isPositive(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("금액은 양수여야 합니다.");
        }
    }

    private void isRightUnit(int money) {
        if (money % MONEY_UNIT != 0) {
            throw new IllegalArgumentException("금액은 10으로 나누어 떨어져야 합니다.");
        }
    }

    public int getMoney() {
        return money;
    }

    @Override
    public int compareTo(Money o) {
        return money - o.money;
    }
}
