package baseball;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class Application {

    private static final String END = "2";

    public static void main(String[] args) {

        // 컴퓨터 숫자 받기.
        String computerNumber = createComputerNumbers();

        while (true) {
            // 입력
            String userNumber = getUserNumbers();

            // 결과
            boolean isAnswer = computeResult(computerNumber, userNumber);

            if (isAnswer) {
                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력해주세요.");
                String isRestart = Console.readLine();

                if (isRestart.equals(END)) {
                    break;
                }
                computerNumber = createComputerNumbers();
            }
        }
    }

    private static String createComputerNumbers() {
        StringBuilder computerNumberBuilder = new StringBuilder();

        while (!isThreeDigit(computerNumberBuilder.toString())) {
            String newNumber = String.valueOf(Randoms.pickNumberInRange(1, 9));

            if (!isExistNumber(computerNumberBuilder.toString(), newNumber)) {
                computerNumberBuilder.append(newNumber);
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

    private static String getUserNumbers() {

        while (true) {
            System.out.print("숫자를 입력해 주세요 : ");
            String userInput = Console.readLine();

            if (isValidate(userInput)) {
                return userInput;
            }
        }
    }

    private static boolean isValidate(String userInput) {
        boolean isValid = true;

        if (!isValidateLength(userInput)) {
            System.out.println("[Error] 3자리 숫자를 입력해 주세요.");
            isValid = false;
        }
        if (!isValidateNumberRange(userInput)) {
            System.out.println("[Error] 1-9 범위의 숫자만 입력해 주세요.");
            isValid = false;
        }
        if (!isUniqueDigit(userInput)) {
            System.out.println("[Error] 각 자릿수가 중복되지 않게 입력해 주세요.");
            isValid = false;
        }

        return isValid;
    }

    private static boolean isValidateLength(String userInput) {
        return userInput.length() == 3;
    }

    private static boolean isValidateNumberRange(String userInputNumbers) {
        for (char number : userInputNumbers.toCharArray()) {
            if (number == '0' || !Character.isDigit(number)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isUniqueDigit(String userInput) {
        Set<String> uniqueSet = new HashSet<>();
        uniqueSet.addAll(Arrays.asList(userInput.split("")));

        return uniqueSet.size() == userInput.length();
    }

    private static boolean computeResult(String computerNumbers, String userInputNumbers) {
        String[] inputNumbers = userInputNumbers.split("");
        int strikeCount = 0;
        int ballCount = 0;

        for (int i = 0; i < inputNumbers.length; i++) {
            int findIndex = computerNumbers.indexOf(inputNumbers[i]);

            if (findIndex < 0) {
                continue;
            }

            if (findIndex == i) {
                strikeCount++;
            }
            else{
                ballCount++;
            }

        }
        printResult(strikeCount, ballCount);

        return strikeCount == 3;
    }

    private static void printResult(int strikeCount, int ballCount) {
        String result = makeResultString(strikeCount, ballCount);
        System.out.println(result);
    }

    private static String makeResultString(int strikeCount, int ballCount) {
        StringBuilder result = new StringBuilder();

        if (strikeCount == 3) {
            result.append("3스트라이크!! 게임 끝.\n");
        } else if (ballCount == 0 && strikeCount == 0) {
            result.append("낫싱");
        } else {
            if (strikeCount > 0) {
                result.append(strikeCount).append("스트라이크 ");
            }
            if (ballCount > 0) {
                result.append(ballCount).append("볼");
            }
        }
        return result.toString();
    }
}
