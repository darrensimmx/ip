import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    /*
    * We have to split between each case, and parse differently due to difference in length and what it represents at each index
     */
    public static Task parseTask(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) throw new IllegalArgumentException("bad line");

        String type = parts[0].trim();
        boolean done = "1".equals(parts[1].trim());
        String desc = parts[2].trim();

        switch (type) {
            case "T": {
                ToDoTask t = new ToDoTask(desc);
                if (done) t.markAsDone();
                return t;
            }
            case "D": {
                String byStr = parts[3].trim();
                LocalDateTime by = parseDateTime(byStr);
                DeadlinesTask t = new DeadlinesTask(desc, by);
                if (done) t.markAsDone();
                return t;
            }
            case "E": {
                String fromStr = parts[3].trim();
                String toStr   = parts[4].trim();
                LocalDateTime from = parseDateTime(fromStr);
                LocalDateTime to   = parseDateTime(toStr);
                EventsTask t = new EventsTask(desc, from, to);
                if (done) t.markAsDone();
                return t;
            }
            default:
                throw new IllegalArgumentException("unknown type: " + type);
        }
    }

    /*
    * Time formatter for user input d/M/yyyy HHmm
     */
    private static final DateTimeFormatter[] DATE_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
    };

    /*
    * Time formatter for user input d/M/yyyy, time set to 00:00
     */
    private static final DateTimeFormatter[] DAY_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ISO_LOCAL_DATE,
    };

    /*
    * checks if user input aligns with any of the format, if not throw error of wrong format
     */
    static LocalDateTime parseDateTime(String s) {
        for (DateTimeFormatter f : DATE_PATTERNS) {
            try {
                return LocalDateTime.parse(s.trim(), f);
            } catch (DateTimeParseException ignored) {}
        }
        for (DateTimeFormatter f : DAY_PATTERNS) {
            try {
                return LocalDate.parse(s.trim(), f).atStartOfDay();
            } catch (DateTimeParseException ignored) {}
        }
        throw new IllegalArgumentException("I couldn't understand the date/time: " + s + " Try formatting in d/MMMM/yyyy or d/MMMM/yyyy HH:mm");
    }
}
