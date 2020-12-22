package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class DownedPin {

    public static final int MAXIMUM_DOWNED_PIN_NUM = 10;
    public static final int MINIMUM_DOWNED_PIN_NUM = 0;

    private static final List<DownedPin> cachedPin = IntStream.rangeClosed(MINIMUM_DOWNED_PIN_NUM, MAXIMUM_DOWNED_PIN_NUM)
            .mapToObj(DownedPin::new)
            .collect(toList());

    private final int numDownedPin;

    private DownedPin(int numDownedPin) {
        this.numDownedPin = numDownedPin;
    }

    public static DownedPin fromNumber(int number) {
        validateRange(number);
        return cachedPin.get(number);
    }

    private static void validateRange(int numDownedPin) {
        if (numDownedPin < MINIMUM_DOWNED_PIN_NUM || numDownedPin > MAXIMUM_DOWNED_PIN_NUM) {
            throw new InvalidDownedPinNumberException();
        }
    }

    public DownedPin fromPreviousTry(DownedPin downedPin) {
        validateRange(this.numDownedPin + downedPin.numDownedPin);
        return fromNumber(downedPin.numDownedPin);
    }
}
