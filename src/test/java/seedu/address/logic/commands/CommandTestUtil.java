package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUYER_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.meetup.EditCommand;
import seedu.address.model.BuyerList;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.NameContainsKeywordsPredicate;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.meetup.MeetUpContainsKeywordsPredicate;
import seedu.address.testutil.buyer.EditBuyerDescriptorBuilder;
import seedu.address.testutil.meetup.EditMeetUpDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_BUYER_TYPE_AMY = "seller";
    public static final String VALID_BUYER_TYPE_BOB = "buyer";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_MEETUP_NAME_PITCH = "Sales Pitch";
    public static final String VALID_MEETUP_NAME_NETWORKING = "Networking Session";
    public static final String VALID_MEETUP_INFO_PITCH = "Pitching property at Bukit Timah.";
    public static final String VALID_MEETUP_INFO_NETWORKING = "Networking with real estate agents.";
    public static final String VALID_MEETUP_FROM_PITCH = "2024-09-11 12:00";
    public static final String VALID_MEETUP_FROM_NETWORKING = "2024-10-12 17:30";
    public static final String VALID_MEETUP_TO_PITCH = "2024-09-11 12:59";
    public static final String VALID_MEETUP_TO_NETWORKING = "2024-10-12 19:45";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String BUYER_TYPE_DESC_BOB = " " + PREFIX_BUYER_TYPE + VALID_BUYER_TYPE_BOB;
    public static final String BUYER_TYPE_DESC_AMY = " " + PREFIX_BUYER_TYPE + VALID_BUYER_TYPE_AMY;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_BUYER_TYPE_DESC = " " + PREFIX_BUYER_TYPE
            + "student"; // only 'buyer' or 'seller'
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final seedu.address.logic.commands.buyer.EditCommand.EditBuyerDescriptor DESC_AMY;
    public static final seedu.address.logic.commands.buyer.EditCommand.EditBuyerDescriptor DESC_BOB;
    public static final EditCommand.EditMeetUpDescriptor DESC_PITCH_MEETUP;
    public static final EditCommand.EditMeetUpDescriptor DESC_NETWORKING_MEETUP;

    static {
        DESC_AMY = new EditBuyerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_PITCH_MEETUP = new EditMeetUpDescriptorBuilder().withName(VALID_MEETUP_NAME_PITCH)
                .withInfo(VALID_MEETUP_INFO_PITCH).withFrom(VALID_MEETUP_FROM_PITCH)
                .withTo(VALID_MEETUP_TO_PITCH).build();
        DESC_NETWORKING_MEETUP = new EditMeetUpDescriptorBuilder().withName(VALID_MEETUP_NAME_NETWORKING)
                .withInfo(VALID_MEETUP_INFO_NETWORKING).withFrom(VALID_MEETUP_FROM_NETWORKING)
                .withTo(VALID_MEETUP_TO_NETWORKING).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the buyer list, filtered buyer list and selected buyer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        BuyerList expectedBuyerList = new BuyerList(actualModel.getBuyerList());
        List<Buyer> expectedFilteredList = new ArrayList<>(actualModel.getFilteredBuyerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedBuyerList, actualModel.getBuyerList());
        assertEquals(expectedFilteredList, actualModel.getFilteredBuyerList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the buyer at the given {@code targetIndex} in the
     * {@code model}'s buyer list.
     */
    public static void showBuyerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBuyerList().size());

        Buyer buyer = model.getFilteredBuyerList().get(targetIndex.getZeroBased());
        final String[] splitName = buyer.getName().fullName.split("\\s+");
        model.updateFilteredBuyerList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredBuyerList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the meetup at the given {@code targetIndex} in the
     * {@code model}'s meetup list.
     */
    public static void showMeetUpAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMeetUpList().size());

        MeetUp meetUp = model.getFilteredMeetUpList().get(targetIndex.getZeroBased());
        final String[] splitName = meetUp.getName().meetUpFullName.split("\\s+");
        model.updateFilteredMeetUpList(new MeetUpContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredMeetUpList().size());
    }

}
