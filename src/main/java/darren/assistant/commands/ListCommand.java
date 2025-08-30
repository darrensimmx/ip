package darren.assistant.commands;

import darren.assistant.storage.Storage;
import darren.assistant.tasks.TaskList;
import darren.assistant.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.printList(tasks);
    }
}
