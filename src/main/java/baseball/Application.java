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

        while (true) {
            // 입력
            String userNumber = inputUserNumber();


        }

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

    private static String inputUserNumber() {
        String userInput;

        while (true) {
            System.out.print("숫자를 입력해 주세요 : ");
            userInput = Console.readLine();

            if (isValidate(userInput)) {
                break;
            }
        }

        return userInput;
    }

    private static boolean isValidate(String userInput) {

        if (!isValidateLength(userInput)) {
            System.out.println("[Error] 3자리 숫자를 입력해 주세요.");

            return false;
        }
        if (!isValidateNumberRange(userInput)) {
            System.out.println("[Error] 0이 아닌 숫자만 입력해 주세요.");
            return false;
        }
        if (!isUniqueDigit(userInput)) {
            System.out.println("[Error] 각 자릿수가 중복되지 않게 입력해 주세요.");
            return false;
        }

        return true;
    }

    private static boolean isValidateLength(String userInput) {
        return userInput.length() == 3;
    }

    private static boolean isValidateNumberRange(String userInput) {
        for (char number : userInput.toCharArray()) {
            if (number == '0') {
                return false;
            }
            if (!Character.isDigit(number)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isUniqueDigit(String userInput) {
        Set<String> uniqueSet = new HashSet<>();
        uniqueSet.addAll(Arrays.asList(userInput.split("")));

        return uniqueSet.size() == 3;
    }

}
