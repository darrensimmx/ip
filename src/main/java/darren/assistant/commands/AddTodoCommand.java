package darren.assistant.commands;

import darren.assistant.storage.Storage;
import darren.assistant.tasks.Task;
import darren.assistant.tasks.TaskList;
import darren.assistant.tasks.ToDoTask;
import darren.assistant.ui.DarrenAssistantException;
import darren.assistant.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a todo task to the task list.
 * <p>
 * A todo task contains only a description without any associated date or time.
 * </p>
 */
public class AddTodoCommand extends Command{
    private final String desc;

    /**
     * Constructs an {@code AddTodoCommand}.
     *
     * @param desc Description of the todo task.
     */
    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the command to add a todo task.
     * <p>
     * This method creates a {@link ToDoTask} with the provided description,
     * validates that the description is not blank, adds it to the provided {@link TaskList},
     * displays a confirmation message using {@link Ui}, and saves the updated task list using {@link Storage}.
     * </p>
     *
     * @param tasks   The {@link TaskList} to which the new todo task will be added.
     * @param ui      The {@link Ui} object responsible for user interaction messages.
     * @param storage The {@link Storage} object that handles saving tasks persistently.
     * @throws DarrenAssistantException If the todo description is blank.
     * @throws IOException If an error occurs while saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DarrenAssistantException, IOException {
        if (desc.isBlank()) {
            throw new DarrenAssistantException("Todo needs a description!");
        }
        Task t = new ToDoTask(desc);
        tasks.add(t);
        Ui.printAdded(t);
        storage.save(tasks.asList());
    }
}
