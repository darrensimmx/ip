import java.io.IOException;

public class DeleteCommand extends Command{
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

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
