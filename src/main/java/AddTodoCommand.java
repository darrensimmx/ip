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
        Ui.printAdded(t);
        storage.save(tasks.asList());
    }
}
