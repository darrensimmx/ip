package darren.assistant.commands;

import darren.assistant.storage.Storage;
import darren.assistant.tasks.Task;
import darren.assistant.tasks.TaskList;
import darren.assistant.tasks.ToDoTask;
import darren.assistant.ui.DarrenAssistantException;
import darren.assistant.ui.Ui;

import java.io.IOException;

public class AddTodoCommand extends Command{
    private final String desc;

    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

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
