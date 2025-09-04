package commands;

import java.io.IOException;

import storage.Storage;
import tasks.TaskList;
import ui.DarrenAssistantException;
import ui.Ui;

public abstract class Command {
    protected boolean isExit = false;
    public boolean isExit() {
        return isExit;
    }

    /*
    * Centralised doing work here
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DarrenAssistantException, IOException;

    public abstract String getString();
}
