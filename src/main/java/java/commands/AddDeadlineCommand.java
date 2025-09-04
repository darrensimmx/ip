package commands;

import parser.Parser;
import storage.Storage;
import tasks.DeadlinesTask;
import tasks.Task;
import tasks.TaskList;
import ui.DarrenAssistantException;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents a command to add a deadline task to the task list.
 * A deadline task has a description and a specific due date/time.
 */
public class AddDeadlineCommand extends Command {
    private final String desc;
    private final LocalDateTime by;
    private String message; // Message to return to GUI

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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DarrenAssistantException, IOException {
        if (desc == null || desc.isBlank()) {
            throw new DarrenAssistantException("Deadline needs a description!");
        }
        if (by == null) {
            throw new DarrenAssistantException("Please provide a valid deadline date/time.");
        }

        Task t = new DeadlinesTask(desc, by);
        tasks.add(t);

        // Build the GUI message
        message = ui.formatAdded(t, tasks.size());

        // Persist
        storage.save(tasks.asList());
    }

    @Override
    public String getString() {
        return message != null ? message : "Added deadline: " + desc + " (by " + by + ")";
    }
}
