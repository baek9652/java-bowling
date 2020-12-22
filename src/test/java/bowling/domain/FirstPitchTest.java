package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class FirstPitchTest {

    @Test
    @DisplayName("일반 생성")
    void testValidation() {
        new FirstPitch(DownedPin.fromNumber(5));
    }

    @Test
    @DisplayName("영향을 받는 피치 생성")
    void testAdditionalPitch() {
        FirstPitch firstPitch = new FirstPitch(DownedPin.fromNumber(4));
        AdditionalPitch additionalPitch = firstPitch.additionalPitch(DownedPin.fromNumber(6));
    }
}
