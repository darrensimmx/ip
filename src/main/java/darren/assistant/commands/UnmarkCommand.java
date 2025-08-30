package darren.assistant.commands;

import darren.assistant.storage.Storage;
import darren.assistant.tasks.Task;
import darren.assistant.tasks.TaskList;
import darren.assistant.ui.DarrenAssistantException;
import darren.assistant.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a task in the task list as not done.
 * <p>
 * An unmark command updates the completion status of a specified task,
 * setting it to "not done," and saves the updated task list to storage.
 * </p>
 */
public class UnmarkCommand extends Command {
    private final int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the command to unmark a task as not done.
     * <p>
     * This method validates the index, retrieves the task at that index,
     * marks it as not done, displays a confirmation message using {@link Ui},
     * and saves the updated task list using {@link Storage}.
     * </p>
     *
     * @param tasks   The {@link TaskList} containing the tasks.
     * @param ui      The {@link Ui} object responsible for user interaction messages.
     * @param storage The {@link Storage} object that handles saving tasks persistently.
     * @throws DarrenAssistantException If the index is invalid (negative or greater than/equal to the number of tasks).
     * @throws IOException If an error occurs while saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DarrenAssistantException, IOException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new DarrenAssistantException("No such tasks: " + (idx + 1));
        }
        Task t = tasks.get(idx);
        t.markAsNotDone();
        Ui.printUnmarked(t);
        storage.save(tasks.asList());
    }
}
