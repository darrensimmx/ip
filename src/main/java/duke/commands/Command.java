package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.DarrenAssistantException;
import duke.ui.Ui;

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
