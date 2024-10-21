package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's asking price in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class AskingPrice {


    public static final String MESSAGE_CONSTRAINTS =
            "Asking prices should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code AskingPrice}.
     *
     * @param askingPrice A valid asking price.
     */
    public AskingPrice(String askingPrice) {
        requireNonNull(askingPrice);
        checkArgument(isValidPrice(askingPrice), MESSAGE_CONSTRAINTS);
        value = askingPrice;
    }

    /**
     * Returns true if a given string is a valid asking price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AskingPrice)) {
            return false;
        }

        AskingPrice otherPrice = (AskingPrice) other;
        return value.equals(otherPrice.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}