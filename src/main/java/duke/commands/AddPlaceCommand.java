package duke.commands;

import duke.storage.Storage;
import duke.tasks.PlaceTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.DarrenAssistantException;
import duke.ui.Ui;

import java.io.IOException;

public class AddPlaceCommand extends Command {
    private final String desc;
    private final String place;
    private String message;

    public AddPlaceCommand(String desc, String place) {
        this.desc = desc;
        this.place = place;
        assert place != null && !place.isBlank();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DarrenAssistantException, IOException {
        if (desc == null || desc.isBlank()) {
            throw new DarrenAssistantException("Place-task needs a description!");
        }
        if (place == null || place.isBlank()) {
            throw new DarrenAssistantException("Please provide a valid location with /at.");
        }

        Task t = new PlaceTask(desc, place);
        tasks.add(t);

        message = ui.formatAdded(t, tasks.size());
        storage.save(tasks.asList());
    }

    @Override
    public String getString() {
        return message != null ? message : "Added place-task: " + desc + " at " + place;
    }
}
