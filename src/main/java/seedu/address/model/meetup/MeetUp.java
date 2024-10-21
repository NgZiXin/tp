package seedu.address.model.meetup;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a MeetUp in the buyer list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class MeetUp {

    // Identity fields
    private final Name name;
    private final Info info;
    private final From from;
    private final To to;

    /**
     * Every field must be present and not null.
     */
    public MeetUp(Name name, Info info, From from, To to) {
        requireAllNonNull(name, info, from, to);
        this.name = name;
        this.info = info;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns true if both meetups have the same name.
     * This defines a weaker notion of equality between two meetups.
     */
    public boolean isSameMeetUp(MeetUp otherMeetUp) {
        if (otherMeetUp == this) {
            return true;
        }

        return otherMeetUp != null // Can have different info
                && otherMeetUp.getName().equals(getName())
                && otherMeetUp.getFrom().equals(getFrom())
                && otherMeetUp.getTo().equals(getTo());
    }

    /**
     * Returns true if both meetups have the same identity and data fields.
     * This defines a stronger notion of equality between two meetUps.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetUp)) {
            return false;
        }

        MeetUp otherMeetUp = (MeetUp) other;
        return name.equals(otherMeetUp.name)
                && info.equals(otherMeetUp.info)
                && from.equals(otherMeetUp.from)
                && to.equals(otherMeetUp.to);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, info, from, to);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("info", info)
                .add("from", from)
                .add("to", to)
                .toString();
    }

    public Name getName() {
        return this.name;
    }

    public Info getInfo() {
        return this.info;
    }

    public From getFrom() {
        return this.from;
    }

    public To getTo() {
        return this.to;
    }
}
