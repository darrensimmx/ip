package darren.assistant.commands;

import darren.assistant.storage.Storage;
import darren.assistant.tasks.Task;
import darren.assistant.tasks.TaskList;
import darren.assistant.ui.DarrenAssistantException;
import darren.assistant.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task in the task list as done.
 * <p>
 * A mark command updates the completion status of a specified task
 * and saves the updated task list to storage.
 * </p>
 */
public class MarkCommand extends Command {
    private final int idx;

    /**
     * Constructs a {@code MarkCommand}.
     *
     * @param idx Index of the task to mark as done (0-based).
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the command to mark a task as done.
     * <p>
     * This method validates the index, retrieves the task at that index,
     * marks it as done, displays a confirmation message using {@link Ui},
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
        t.markAsDone();
        Ui.printMarked(t);
        storage.save(tasks.asList());
    }
}
