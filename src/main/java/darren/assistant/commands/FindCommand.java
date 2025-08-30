package darren.assistant.commands;

import darren.assistant.storage.Storage;
import darren.assistant.tasks.Task;
import darren.assistant.tasks.TaskList;
import darren.assistant.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks by keyword.
 * <p>
 * A find command searches the {@link TaskList} for tasks
 * whose description contains the given keyword.
 * </p>
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand}.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks containing the keyword.
     * <p>
     * This method searches all tasks in {@link TaskList}, filters those
     * containing the keyword in their description, and displays the results
     * using {@link Ui}.
     * </p>
     *
     * @param tasks   The {@link TaskList} to search.
     * @param ui      The {@link Ui} object responsible for displaying results.
     * @param storage The {@link Storage} object (unused here).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        List<Task> matches = new ArrayList<>();
        for (Task t : tasks.asList()) {
            if (t.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(t);
            }
        }

        Ui.printFindResult(matches);
    }
}
