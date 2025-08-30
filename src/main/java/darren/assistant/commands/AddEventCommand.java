package darren.assistant.commands;
import darren.assistant.tasks.Task;
import darren.assistant.ui.Ui;
import darren.assistant.storage.Storage;
import java.io.IOException;
import darren.assistant.tasks.TaskList;
import darren.assistant.tasks.EventsTask;
import darren.assistant.Parser;
import java.time.LocalDateTime;

public class AddEventCommand extends Command{
    private final String desc;
    private final LocalDateTime from, to;

    public AddEventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = Parser.parseDateTime(from);
        this.to = Parser.parseDateTime(to);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = new EventsTask(desc, from, to);
        tasks.add(t);
        Ui.printAdded(t);
        storage.save(tasks.asList());
    }
}
