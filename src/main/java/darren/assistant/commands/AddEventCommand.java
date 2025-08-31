package darren.assistant.commands;
import darren.assistant.tasks.Task;
import darren.assistant.ui.Ui;
import darren.assistant.storage.Storage;
import java.io.IOException;
import darren.assistant.tasks.TaskList;
import darren.assistant.tasks.EventsTask;
import darren.assistant.Parser;
import java.time.LocalDateTime;

/**
 * Represents a command to add an event task to the task list.
 * <p>
 * An event task has a description, a start date/time, and an end date/time.
 * </p>
 */
public class AddEventCommand extends Command{
    private final String desc;
    private final LocalDateTime from, to;

    /**
     * Constructs an {@code AddEventCommand}.
     *
     * @param desc Description of the event task.
     * @param from String representation of the start date and time.
     *             This will be parsed into a {@link LocalDateTime} using {@link Parser#parseDateTime(String)}.
     * @param to   String representation of the end date and time.
     *             This will be parsed into a {@link LocalDateTime} using {@link Parser#parseDateTime(String)}.
     */
    public AddEventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = Parser.parseDateTime(from);
        this.to = Parser.parseDateTime(to);
    }

    /**
     * Executes the command to add an event task.
     * <p>
     * This method creates an {@link EventsTask} with the given description, start date/time,
     * and end date/time, adds it to the provided {@link TaskList}, displays a confirmation
     * message using {@link Ui}, and saves the updated task list using {@link Storage}.
     * </p>
     *
     * @param tasks   The {@link TaskList} to which the new event task will be added.
     * @param ui      The {@link Ui} object responsible for user interaction messages.
     * @param storage The {@link Storage} object that handles saving tasks persistently.
     * @throws IOException If an error occurs while saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = new EventsTask(desc, from, to);
        tasks.add(t);
        Ui.printAdded(t);
        storage.save(tasks.asList());
    }
}
