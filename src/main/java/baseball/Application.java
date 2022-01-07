package baseball;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class Application {

    public static void main(String[] args) {

        // 컴퓨터 숫자 받기.
        String computerNumber = makeComputerNumber();

    }

    private static String makeComputerNumber() {
        StringBuilder computerNumberBuilder = new StringBuilder();

        while (!isThreeDigit(computerNumberBuilder.toString())) {
            String number = String.valueOf(Randoms.pickNumberInRange(1, 9));

            if (!isExistNumber(computerNumberBuilder.toString(), number)) {
                computerNumberBuilder.append(number);
            }
        }

        return computerNumberBuilder.toString();
    }

    private static boolean isThreeDigit(String currComputerNumber) {
        return currComputerNumber.length() == 3;
    }

    private static boolean isExistNumber(String currComputerNumber, String number) {
        return currComputerNumber.indexOf(number) >= 0;
    }

}
