package vendingmachine.util;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.util.Coin;

public class RandomNumberGenerator {
    public static int generateRandomNumber() {
        return Randoms.pickNumberInList(Coin.amounts());
    }
}
