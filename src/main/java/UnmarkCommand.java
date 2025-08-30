import java.io.IOException;

public class UnmarkCommand extends Command{
    private final int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DarrenAssistantException, IOException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new DarrenAssistantException("No such tasks: " + (idx + 1));
        }
        Task t = tasks.get(idx);
        t.markAsNotDone();
        Ui.printUnmarked(t);
        storage.save(tasks.asList());
    }
}
