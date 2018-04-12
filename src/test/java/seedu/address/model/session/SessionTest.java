package seedu.address.model.session;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

import seedu.address.commons.core.EventsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.food.Phone;
import seedu.address.testutil.TypicalFoods;

//@@author {jaxony}
public class SessionTest {
    private static final String BACON_NAME = TypicalFoods.BACON.getName().toString();
    private static final String BACON_WRONG_PHONE = "asdadn";
    private static final String BACON_PHONE = TypicalFoods.BACON.getPhone().toString();
    private static final String BACON_EMAIL = TypicalFoods.BACON.getEmail().toString();
    private static final String BACON_ADDRESS = TypicalFoods.BACON.getAddress().toString();
    private static final String BACON_RATING = TypicalFoods.BACON.getRating().toString();
    private static final String BACON_PRICE = TypicalFoods.BACON.getPrice().toString();

    private static final int INDEX_AFTER_NAME = 1;
    private static final String SUCCESS_MESSAGE_AFTER_NAME =
            AddCommand.PROMPTS.get(INDEX_AFTER_NAME).getMessage();
    private static final int INDEX_AFTER_PHONE = 2;
    private static final String FAILURE_MESSAGE_AFTER_WRONG_PHONE =
            Session.TRY_AGAIN_MESSAGE + Phone.MESSAGE_PHONE_CONSTRAINTS;
    private static final String SUCCESS_MESSAGE_AFTER_PHONE =
            AddCommand.PROMPTS.get(INDEX_AFTER_PHONE).getMessage();
    private static final int INDEX_AFTER_EMAIL = 3;
    private static final String SUCCESS_MESSAGE_AFTER_EMAIL =
            AddCommand.PROMPTS.get(INDEX_AFTER_EMAIL).getMessage();
    private static final int INDEX_AFTER_ADDRESS = 4;
    private static final String SUCCESS_MESSAGE_AFTER_ADDRESS =
            AddCommand.PROMPTS.get(INDEX_AFTER_ADDRESS).getMessage();
    private static final int INDEX_AFTER_PRICE = 5;
    private static final String SUCCESS_MESSAGE_AFTER_PRICE =
            AddCommand.PROMPTS.get(INDEX_AFTER_PRICE).getMessage();
    private static final int INDEX_AFTER_RATING = 6;
    private static final String SUCCESS_MESSAGE_AFTER_RATING =
            AddCommand.PROMPTS.get(INDEX_AFTER_RATING).getMessage() + " " + Session.OPTIONAL_MESSAGE;

    private static final String SUCCESS_MESSAGE_AFTER_FIRST_TAG =
            Session.ANYTHING_ELSE_MESSAGE;
    private static final String SUCCESS_MESSAGE_AFTER_SECOND_TAG =
            Session.ANYTHING_ELSE_MESSAGE;

    private static final int INDEX_AFTER_TAGS = 7;
    private static final String SUCCESS_MESSAGE_AFTER_TAGS =
            AddCommand.PROMPTS.get(INDEX_AFTER_TAGS).getMessage() + " " + Session.OPTIONAL_MESSAGE;

    private static final String SUCCESS_MESSAGE_AFTER_FIRST_ALLERGY =
            Session.ANYTHING_ELSE_MESSAGE;


    @Test
    public void interpretUserInput_success() throws CommandException {
        // Session class is abstract so need to use a subclass
        // to test non-abstract methods in Session
        Session session = new SessionAddCommandStub(new AddCommand(null), EventsCenter.getInstance());
        assertEquals(SUCCESS_MESSAGE_AFTER_NAME, session.interpretUserInput(BACON_NAME).feedbackToUser);
        assertEquals(FAILURE_MESSAGE_AFTER_WRONG_PHONE, session.interpretUserInput(BACON_WRONG_PHONE).feedbackToUser);
        assertEquals(SUCCESS_MESSAGE_AFTER_PHONE, session.interpretUserInput(BACON_PHONE).feedbackToUser);
        assertEquals(SUCCESS_MESSAGE_AFTER_EMAIL, session.interpretUserInput(BACON_EMAIL).feedbackToUser);
        assertEquals(SUCCESS_MESSAGE_AFTER_ADDRESS, session.interpretUserInput(BACON_ADDRESS).feedbackToUser);
        assertEquals(SUCCESS_MESSAGE_AFTER_PRICE, session.interpretUserInput(BACON_PRICE).feedbackToUser);
        assertEquals(SUCCESS_MESSAGE_AFTER_RATING, session.interpretUserInput(BACON_RATING).feedbackToUser);

        // adding multi value fields
        assertEquals(SUCCESS_MESSAGE_AFTER_FIRST_TAG, session.interpretUserInput("meat").feedbackToUser);
        assertEquals(SUCCESS_MESSAGE_AFTER_SECOND_TAG, session.interpretUserInput("other").feedbackToUser);
        assertEquals(SUCCESS_MESSAGE_AFTER_TAGS, session.interpretUserInput(
                Session.END_MULTI_VALUE_FIELD).feedbackToUser);

        // adding multi value fields
        assertEquals(SUCCESS_MESSAGE_AFTER_FIRST_ALLERGY, session.interpretUserInput("animals").feedbackToUser);
        assertEquals(Session.SUCCESS_MESSAGE, session.interpretUserInput(Session.END_MULTI_VALUE_FIELD).feedbackToUser);
    }

    /**
     * A stub used to test SessionAddCommand with hardcoded values
     */
    private class SessionAddCommandStub extends SessionAddCommand {

        public SessionAddCommandStub(Command command, EventsCenter eventsCenter) {
            super(command, eventsCenter);
            command.setData(new ModelManager(), null, null);
        }
    }
}
