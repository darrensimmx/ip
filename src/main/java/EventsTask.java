public class EventsTask extends Task{
    private String from;
    private String to;
    public EventsTask(String description, String from, String to) {
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
}
