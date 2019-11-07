package seedu.billboard.logic.commands;

import seedu.billboard.logic.commands.exceptions.CommandException;
import seedu.billboard.model.Model;

/**
 *  Represents an Archive command with hidden internal logic and the ability to be executed.
 */
public abstract class RecurrenceCommand extends Command {
    public static final String COMMAND_WORD = "recur";
    public static final String MESSAGE_USAGE = "Recurrence commands: list, add, edit, delete.";

    public abstract CommandResult execute(Model model) throws CommandException;
}
