package baseball;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseballGame {
    private static final int INPUT_SIZE = 3;
    private Score score = new Score();

    public void run() throws IllegalArgumentException {

    }

    private String readIntegerFromUser()  {
        return Console.readLine();
    }

    private List<Integer> castStringToIntegerList(String inputString) {
        return inputString.chars().map(charNumber -> charNumber - '0').boxed().collect(Collectors.toList());
    }

    private void validateIntegerRange(List<Integer> input) throws IllegalArgumentException {
        if (input.stream().anyMatch(number -> number < 0 || number > 9)) {
            throw new IllegalArgumentException("입력이 [0, 9]사이의 숫자 이외의 문자를 포함합니다.");
        }
    }

    private void validateInputLength(List<Integer> input) throws IllegalArgumentException {
        if (input.size() != INPUT_SIZE) {
            throw new IllegalArgumentException(String.format("입력한 숫자의 길이가 %d이 아닙니다.", INPUT_SIZE));
        }
    }

    private void validateInputDistinct(List<Integer> input) throws IllegalArgumentException {
        if (input.stream().distinct().count() != INPUT_SIZE) {
            throw new IllegalArgumentException("입력한 숫자에 중복이 발생했습니다.");
        }
    }

    private void validateInput(List<Integer> input) throws IllegalArgumentException {
        validateIntegerRange(input);
        validateInputLength(input);
        validateInputDistinct(input);
    }

    private List<Integer> generateComputerNumber() {
        return Stream.generate(() -> Randoms.pickNumberInRange(0, 9))
                .distinct()
                .limit(INPUT_SIZE)
                .collect(Collectors.toList());
    }

    private int calculateStrikeScore(List<Integer> input, List<Integer> computer) {
        int strikeScore = 0;
        for(int i = 0; i < INPUT_SIZE; ++i) {
            if (input.get(i).equals(computer.get(i))) {
                ++strikeScore;
            }
        }
        return strikeScore;
    }

    private int calculateBallScore(List<Integer> input, List<Integer> computer) {
        int ballScore = 0;
        for(int i = 0; i < INPUT_SIZE; ++i) {
            for (int j = 0; j < INPUT_SIZE; ++j) {
                if (i == j) {
                    continue;
                }

                if (input.get(i).equals(computer.get(j))) {
                    ++ballScore;
                }
            }
        }
        return ballScore;
    }
    private void calculateScore(List<Integer> input, List<Integer> computer) {
        int strikeScore = calculateStrikeScore(input, computer);
        int ballScore = calculateBallScore(input, computer);
        this.score.setScores(strikeScore, ballScore);
    }

    public static class Score {
        public int strike;
        public int ball;

        void setScores(int strikeScore, int ballScore) {
            this.strike = strikeScore;
            this.ball = ballScore;
        }

        public void print() {
            if (strike == 0 && ball == 0) {
                System.out.println("낫싱");
                return;
            }
            if (ball != 0) {
                System.out.printf("%d볼 ", ball);
            }
            if (strike != 0) {
                System.out.printf("%d스트라이크", strike);
            }
            System.out.println();
        }

        public void clear() {
            this.ball = this.strike = 0;
        }
    }
}
