package darren.assistant.commands;

import java.io.IOException;

import darren.assistant.storage.Storage;
import darren.assistant.tasks.TaskList;
import darren.assistant.ui.DarrenAssistantException;
import darren.assistant.ui.Ui;

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
