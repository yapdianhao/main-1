package seedu.billboard.logic.commands;

import seedu.billboard.logic.commands.exceptions.CommandException;
import seedu.billboard.model.Model;
import seedu.billboard.model.versionedbillboard.VersionedBillboard;

/**
 * Undo the previous edit action.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String EMPTY_UNDO_LIST = "There is no command to be redone.";
    public static final String MESSAGE_REDO_SUCCESS = COMMAND_WORD + ": %s";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!VersionedBillboard.isRedoable()) {
            throw new CommandException(EMPTY_UNDO_LIST);
        }

        String redoCmd = VersionedBillboard.getRedoCmd();
        CommandResult redoCmdResult = VersionedBillboard.getRedoCmdResult();
        Model undoModel = VersionedBillboard.getRedoModel();

        model.setModel(undoModel.getClone());
        return new CommandResult(String.format(MESSAGE_REDO_SUCCESS, redoCmd),
                false, false,
                redoCmdResult.getListToBeDisplayed().orElse(CommandResult.UNCHANGED_LIST_VIEW));
    }
}
