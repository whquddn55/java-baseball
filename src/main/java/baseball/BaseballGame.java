package baseball;
import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.stream.Collectors;

public class BaseballGame {

    public void run() throws IllegalArgumentException {

    }

    private String readIntegerFromUser()  {
        return Console.readLine();
    }

    private List<Integer> castStringToIntegerList(String inputString) {
        return inputString.chars().map(charNumber -> charNumber - '0').boxed().collect(Collectors.toList());
    }
}
