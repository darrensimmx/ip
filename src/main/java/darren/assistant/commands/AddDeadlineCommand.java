package darren.assistant.commands;

import darren.assistant.Parser;
import darren.assistant.storage.Storage;
import darren.assistant.tasks.DeadlinesTask;
import darren.assistant.tasks.Task;
import darren.assistant.tasks.TaskList;
import darren.assistant.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents a command to add a deadline task to the task list.
 * A deadline task has a description and a specific due date/time.
 */
public class AddDeadlineCommand extends Command{
    private final String desc;
    private final LocalDateTime by;

    /**
     * Constructs an {@code AddDeadlineCommand}.
     *
     * @param desc Description of the deadline task.
     * @param when String representation of the date and time by which the task is due.
     *             This will be parsed into a {@link LocalDateTime} using {@link Parser#parseDateTime(String)}.
     */
    public AddDeadlineCommand(String desc, String when) {
        this.desc = desc;
        this.by = Parser.parseDateTime(when);
    }

    /**
     * Executes the command to add a deadline task.
     * <p>
     * This method creates a {@link DeadlinesTask} with the given description and due date,
     * adds it to the provided {@link TaskList}, displays a confirmation message using {@link Ui},
     * and saves the updated task list using {@link Storage}.
     * </p>
     *
     * @param tasks   The {@link TaskList} to which the new deadline task will be added.
     * @param ui      The {@link Ui} object responsible for user interaction messages.
     * @param storage The {@link Storage} object that handles saving tasks persistently.
     * @throws IOException If an error occurs while saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = new DeadlinesTask(desc, by);
        tasks.add(t);
        Ui.printAdded(t);
        storage.save(tasks.asList());
    }
}
