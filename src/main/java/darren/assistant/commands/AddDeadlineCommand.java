package darren.assistant.commands;

import darren.assistant.Parser;
import darren.assistant.storage.Storage;
import darren.assistant.tasks.DeadlinesTask;
import darren.assistant.tasks.Task;
import darren.assistant.tasks.TaskList;
import darren.assistant.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command{
    private final String desc;
    private final LocalDateTime by;

    public AddDeadlineCommand(String desc, String when) {
        this.desc = desc;
        this.by = Parser.parseDateTime(when);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = new DeadlinesTask(desc, by);
        tasks.add(t);
        Ui.printAdded(t);
        storage.save(tasks.asList());
    }
}
