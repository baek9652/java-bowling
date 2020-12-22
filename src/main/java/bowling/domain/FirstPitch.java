package bowling.domain;

public class FirstPitch {

    private final DownedPin downedPin;

    public FirstPitch(DownedPin downedPin) {
        this.downedPin = downedPin;
    }

    public AdditionalPitch additionalPitch(DownedPin additionalTry) {
        return null;
    }
}
