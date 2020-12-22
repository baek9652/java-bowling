package bowling.domain;

import bowling.bowlingexception.InvalidNameFormatException;

import java.util.regex.Pattern;

public class User {

    private static final String allowedFormat = "[a-zA-Z]{3}";

    private final String name;

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || !Pattern.matches(allowedFormat, name)) {
            throw new InvalidNameFormatException();
        }
    }
}
