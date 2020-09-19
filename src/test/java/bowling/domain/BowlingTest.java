package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BowlingTest {

    public static final String USERNAME = "kyd";
    public static final String EMPTY_USERNAME = "";
    public static final String ERROR_USERNAME_1 = USERNAME + "_";
    public static final String ERROR_USERNAME_2 = "권용대";

    @Test
    void from() {
        assertThat(Bowling.from(USERNAME)).isNotNull();
    }

    @Test
    void from_exception() {
        assertThatThrownBy(() -> {
            Bowling.from(EMPTY_USERNAME);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            Bowling.from(ERROR_USERNAME_1);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            Bowling.from(ERROR_USERNAME_2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isEnd() {
        Bowling bowling = Bowling.from(USERNAME);

        int tryCount = 0;

        while (!bowling.isEnd()) {
            tryCount++;
            bowling.hit(0);
        }

        assertThat(tryCount).isEqualTo(20);
    }

    @Test
    void isEndWithAllStrike() {
        Bowling bowling = Bowling.from(USERNAME);

        int tryCount = 0;

        while (!bowling.isEnd()) {
            tryCount++;
            bowling.hit(10);
        }

        assertThat(tryCount).isEqualTo(12);
    }

    @Test
    void isEndWithLastSpare() {
        Bowling bowling = Bowling.from(USERNAME);

        int tryCount = 0;

        while (!bowling.isEnd()) {
            tryCount++;

            if (tryCount == 10) {
                bowling.hit(9);
            } else if (tryCount == 11) {
                bowling.hit(1);
            } else {
                bowling.hit(10);
            }
        }

        assertThat(tryCount).isEqualTo(12);
    }

    private Bowling setLastGame() {
        Bowling bowling = Bowling.from(USERNAME);

        for (int index = 0; index < 9; index++) {
            bowling.hit(10);
        }

        return bowling;
    }

    @Test
    void hit_strike_last() {
        Bowling bowling = setLastGame();

        Frame frame = bowling.hit(10).hit(10).hit(10);

        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(10), Pin.of(10), Pin.of(10)));
        assertThat(frame.getScore().toInt()).isEqualTo(30);
    }

    @Test
    void hit_spare_last() {
        Bowling bowling = setLastGame();

        Frame frame = bowling.hit(1).hit(9).hit(10);

        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(1), Pin.of(9), Pin.of(10)));
        assertThat(frame.getScore().toInt()).isEqualTo(20);
    }

    @Test
    void hit_miss_last() {
        Bowling bowling = setLastGame();

        Frame frame = bowling.hit(1).hit(8);

        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(1), Pin.of(8)));
        assertThat(frame.getScore().toInt()).isEqualTo(9);
    }

    @Test
    void hit_gutter_last() {
        Bowling bowling = setLastGame();

        Frame frame = bowling.hit(0).hit(0);

        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(0), Pin.of(0)));
        assertThat(frame.getScore().toInt()).isEqualTo(0);
    }

    @Test
    void next_last() {
        Bowling bowling = Bowling.from(USERNAME);
        Frame frame = null;

        for (int index = 0; index < 10; index++) {
            frame = bowling.hit(10);
        }

        assertThat(frame).isInstanceOf(LastFrame.class);
    }
}