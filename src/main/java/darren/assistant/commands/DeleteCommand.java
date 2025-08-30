package darren.assistant.commands;

import darren.assistant.storage.Storage;
import darren.assistant.tasks.Task;
import darren.assistant.tasks.TaskList;
import darren.assistant.ui.DarrenAssistantException;
import darren.assistant.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 * <p>
 * A delete command removes the task at the specified index from the {@link TaskList}.
 * </p>
 */
public class DeleteCommand extends Command {
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the command to delete a task.
     * <p>
     * This method removes the task at the specified index from the {@link TaskList},
     * validates that the index is within bounds, displays a confirmation message using {@link Ui},
     * and saves the updated task list using {@link Storage}.
     * </p>
     *
     * @param tasks   The {@link TaskList} from which the task will be deleted.
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
        Task t = tasks.remove(idx);
        Ui.printDeleted(t, tasks.size());
        storage.save(tasks.asList());
    }
}
