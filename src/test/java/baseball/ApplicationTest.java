package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.*;

class ApplicationTest extends NsTest {
    private static final BaseballGame baseballGame = new BaseballGame();

    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

    @DisplayName("String to List<integer> 성공 테스트 - digit")
    @Test
    public void castStringToIntegerListDigitSuccessTest() throws Exception {
        // given
        String input = "123";
        Method method = baseballGame.getClass().getDeclaredMethod("castStringToIntegerList", String.class);
        method.setAccessible(true);

        // when
        List<Integer> result = (List<Integer>) method.invoke(baseballGame, input);

        // then
        assertThat(result).isEqualTo(List.of(1, 2, 3));
    }

    @DisplayName("String to List<integer> 성공 테스트 - alphabet")
    @Test
    public void castStringToIntegerListAlphabetSuccessTest() throws Exception {
        // given
        String input = "abc";
        Method method = baseballGame.getClass().getDeclaredMethod("castStringToIntegerList", String.class);
        method.setAccessible(true);

        // when
        List<Integer> result = (List<Integer>) method.invoke(baseballGame, input);

        // then
        assertThat(result).isEqualTo(List.of('a' - '0', 'b' - '0', 'c' - '0'));
    }

    @DisplayName("String to List<integer> 실패 테스트 - alphabet")
    @Test
    public void castStringToIntegerListAlphabetFailTest() throws Exception {
        // given
        String input = "abc";
        Method method = baseballGame.getClass().getDeclaredMethod("castStringToIntegerList", String.class);
        method.setAccessible(true);

        // when
        List<Integer> result = (List<Integer>) method.invoke(baseballGame, input);

        // then
        assertThat(result).isNotEqualTo(List.of((int)'a', (int)'b', (int)'c'));
    }

    @DisplayName("validateIntegerRange 성공 테스트")
    @Test
    public void validateIntegerRangeSuccessTest() throws Exception {
        // given
        List<Integer> input = List.of(1, 2, 3);
        Method method = baseballGame.getClass().getDeclaredMethod("validateIntegerRange", List.class);
        method.setAccessible(true);

        // when

        // then
        assertThatCode(() -> method.invoke(baseballGame, input))
                .doesNotThrowAnyException();
    }

    @DisplayName("validateIntegerRange 실패 테스트 - 음수")
    @Test
    public void validateIntegerRangeFailTestNegative() throws Exception {
        // given
        List<Integer> input = List.of(-1, 0, 1);
        Method method = baseballGame.getClass().getDeclaredMethod("validateIntegerRange", List.class);
        method.setAccessible(true);

        // when

        // then
        assertThatThrownBy(() -> method.invoke(baseballGame, input))
                .isInstanceOf(InvocationTargetException.class)
                .getCause()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력이 [0, 9]사이의 숫자 이외의 문자를 포함합니다.");
    }

    @DisplayName("validateIntegerRange 실패 테스트 - 양수")
    @Test
    public void validateIntegerRangeFailTestPositive() throws Exception {
        // given
        List<Integer> input = List.of(8, 9, 10);
        Method method = baseballGame.getClass().getDeclaredMethod("validateIntegerRange", List.class);
        method.setAccessible(true);

        // when

        // then
        assertThatThrownBy(() -> method.invoke(baseballGame, input))
                .isInstanceOf(InvocationTargetException.class)
                .getCause()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력이 [0, 9]사이의 숫자 이외의 문자를 포함합니다.");
    }

    @DisplayName("validateInputLength 성공 테스트")
    @Test
    public void validateInputLengthSuccessTest() throws Exception {
        // given
        List<Integer> input = List.of(1, 2, 3);
        Method method = baseballGame.getClass().getDeclaredMethod("validateInputLength", List.class);
        method.setAccessible(true);

        // when

        // then
        assertThatCode(() -> method.invoke(baseballGame, input))
                .doesNotThrowAnyException();
    }

    @DisplayName("validateInputLength 실패 테스트")
    @Test
    public void validateInputLengthFailTest() throws Exception {
        // given
        List<Integer> input = List.of(1, 2);
        Method method = baseballGame.getClass().getDeclaredMethod("validateInputLength", List.class);
        method.setAccessible(true);

        // when

        // then
        assertThatThrownBy(() -> method.invoke(baseballGame, input))
                .isInstanceOf(InvocationTargetException.class)
                .getCause()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력한 숫자의 길이가 3이 아닙니다.");
    }

    @DisplayName("validateInputDistinct 성공 테스트")
    @Test
    public void validateInputDistinctSuccessTest() throws Exception {
        // given
        List<Integer> input = List.of(1, 2, 3);
        Method method = baseballGame.getClass().getDeclaredMethod("validateInputDistinct", List.class);
        method.setAccessible(true);

        // when

        // then
        assertThatCode(() -> method.invoke(baseballGame, input))
                .doesNotThrowAnyException();
    }

    @DisplayName("validateInputDistinct 실패 테스트")
    @Test
    public void validateInputDistinctFailTest() throws Exception {
        // given
        List<Integer> input = List.of(2, 2, 3);
        Method method = baseballGame.getClass().getDeclaredMethod("validateInputDistinct", List.class);
        method.setAccessible(true);

        // when

        // then
        assertThatThrownBy(() -> method.invoke(baseballGame, input))
                .isInstanceOf(InvocationTargetException.class)
                .getCause()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력한 숫자에 중복이 발생했습니다.");
    }
}
