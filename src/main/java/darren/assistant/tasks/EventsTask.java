package darren.assistant.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventsTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    public EventsTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    protected String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toStorageString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "E" + " | " + (isDone ? "1" : "0") + " | " + this.description + " | " + " (from: " + from.format(fmt) + " to: " + to.format(fmt) + ")";
    }
}
