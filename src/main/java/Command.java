import java.io.IOException;

public abstract class Command {
    protected boolean isExit = false;
    public boolean isExit() {
        return isExit;
    }

    /*
    * Centralised doing work here
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DarrenAssistantException, IOException;
}
