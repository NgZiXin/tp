package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMeetUp.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.meetup.TypicalMeetUps.FIRST_MEETUP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meetup.From;
import seedu.address.model.meetup.Info;
import seedu.address.model.meetup.Name;
import seedu.address.model.meetup.To;

public class JsonAdaptedMeetUpTest {
    private static final String INVALID_NAME = "Z!x!n";
    private static final String INVALID_INFO = " ";
    private static final String INVALID_FROM = "2023213-0231-03";
    private static final String INVALID_TO = "2023-02-31 37:78";

    private static final String VALID_NAME = FIRST_MEETUP.getName().toString();
    private static final String VALID_INFO = FIRST_MEETUP.getInfo().toString();
    private static final String VALID_FROM = FIRST_MEETUP.getFrom().toString();
    private static final String VALID_TO = FIRST_MEETUP.getTo().toString();

    @Test
    public void toModelType_validMeetUpDetails_returnsMeetUp() throws Exception {
        JsonAdaptedMeetUp meetUp = new JsonAdaptedMeetUp(FIRST_MEETUP);
        assertEquals(FIRST_MEETUP, meetUp.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedMeetUp meetUp = new JsonAdaptedMeetUp(INVALID_NAME, VALID_INFO, VALID_FROM, VALID_TO);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meetUp::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMeetUp meetUp = new JsonAdaptedMeetUp(null, VALID_INFO, VALID_FROM, VALID_TO);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meetUp::toModelType);
    }

    @Test
    public void toModelType_invalidInfo_throwsIllegalValueException() {
        JsonAdaptedMeetUp meetUp = new JsonAdaptedMeetUp(VALID_NAME, INVALID_INFO, VALID_FROM, VALID_TO);
        String expectedMessage = Info.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meetUp::toModelType);
    }

    @Test
    public void toModelType_nullInfo_throwsIllegalValueException() {
        JsonAdaptedMeetUp meetUp = new JsonAdaptedMeetUp(VALID_NAME, null, VALID_FROM, VALID_TO);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Info.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meetUp::toModelType);
    }

    @Test
    public void toModelType_invalidFrom_throwsIllegalValueException() {
        JsonAdaptedMeetUp meetUp = new JsonAdaptedMeetUp(VALID_NAME, VALID_INFO, INVALID_FROM, VALID_TO);
        String expectedMessage = From.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meetUp::toModelType);
    }

    @Test
    public void toModelType_nullFrom_throwsIllegalValueException() {
        JsonAdaptedMeetUp meetUp = new JsonAdaptedMeetUp(VALID_NAME, VALID_INFO, null, VALID_TO);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, From.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meetUp::toModelType);
    }

    @Test
    public void toModelType_invalidTo_throwsIllegalValueException() {
        JsonAdaptedMeetUp meetUp = new JsonAdaptedMeetUp(VALID_NAME, VALID_INFO, VALID_FROM, INVALID_TO);
        String expectedMessage = To.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meetUp::toModelType);
    }

    @Test
    public void toModelType_nullTo_throwsIllegalValueException() {
        JsonAdaptedMeetUp meetUp = new JsonAdaptedMeetUp(VALID_NAME, VALID_INFO, VALID_FROM, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, To.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meetUp::toModelType);
    }

}
