import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlinesTask extends Task{
    private final LocalDateTime by;

    public DeadlinesTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d yyy, h:mm a");
        return getTypeIcon() + getStatusIcon() + " " + description + " (by: " + by.format(fmt) + ")";
    }

    @Override
    public String toStorageString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d yyy, h:mm a");
        return "D" + " | " + (isDone ? "1" : "0") + " | " + this.description + " | " + by.format(fmt);
    }
}
