package bowling.domain;

import bowling.bowlingexception.InvalidNameFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {

    @Test
    @DisplayName("정상건")
    void testDefault() {
        new User("BSH");
    }

    @Test
    @DisplayName("Null 테스트")
    void testNull() {
        assertThatThrownBy(() -> new User(null))
                .isInstanceOf(InvalidNameFormatException.class);
    }

    @Test
    @DisplayName("empty string")
    void testEmptyString() {
        assertThatThrownBy(() -> new User(""))
                .isInstanceOf(InvalidNameFormatException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AB", "FEFE"})
    @DisplayName("3글자가 아닌 문자열 테스트")
    void testNotThree(String input) {
        assertThatThrownBy(() -> new User(input))
                .isInstanceOf(InvalidNameFormatException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AB1", "고De", "가나다"})
    @DisplayName("영어 문자 이외 다른 글자를 포함하고 있을 때")
    void testWithInvalidChar(String input) {
        assertThatThrownBy(() -> new User(input))
                .isInstanceOf(InvalidNameFormatException.class);
    }
}
