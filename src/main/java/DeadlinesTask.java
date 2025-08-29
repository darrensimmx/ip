public class DeadlinesTask extends Task{
    private final String by;

    public DeadlinesTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + " (by: " + by + ")";
    }

    @Override
    public String toStorageString() {
        return "D" + " | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.by;
    }
}
