import java.io.IOException;

public class MarkCommand extends Command{
    private final int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DarrenAssistantException, IOException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new DarrenAssistantException("No such tasks: " + (idx + 1));
        }
        Task t = tasks.get(idx);
        t.markAsDone();
        Ui.printMarked(t);
        storage.save(tasks.asList());
    }
}
