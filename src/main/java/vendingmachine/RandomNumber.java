package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumber {
    public static int generateRandomCoinCount(int minCount, int maxCount) {
        return Randoms.pickNumberInRange(minCount, maxCount);
    }
}