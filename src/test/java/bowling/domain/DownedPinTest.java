package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DownedPinTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("0 ~ 10 까지의 범위 이외에 대한 예외처리")
    void testRangeValidation(int input) {
        assertThatThrownBy(
                () -> DownedPin.fromNumber(input)
        ).isInstanceOf(InvalidDownedPinNumberException.class);
    }

    @Test
    @DisplayName("연속되는 시도가 범위를 벗어날 때의 때의 검증")
    void testFrameCondition() {
        DownedPin firstTry = DownedPin.fromNumber(5);

        assertThatThrownBy(
                () -> firstTry.fromPreviousTry(DownedPin.fromNumber(6))
        ).isInstanceOf(InvalidDownedPinNumberException.class);
    }
}
