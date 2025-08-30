package darren.assistant.commands;

import darren.assistant.storage.Storage;
import darren.assistant.tasks.TaskList;
import darren.assistant.ui.Ui;

/**
 * Represents a command to exit the assistant program.
 * <p>
 * When executed, this command displays a goodbye message and signals the program to terminate.
 * </p>
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     * <p>
     * This method displays a farewell message using {@link Ui#printGoodbye()}
     * and sets {@code isExit} to {@code true}, which signals the application to terminate.
     * </p>
     *
     * @param tasks   The {@link TaskList}, unused in this command.
     * @param ui      The {@link Ui} object responsible for user interaction messages.
     * @param storage The {@link Storage} object, unused in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.printGoodbye();
        isExit = true;
    }
}
