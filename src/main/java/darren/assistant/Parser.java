package darren.assistant;

import darren.assistant.commands.*;
import darren.assistant.tasks.DeadlinesTask;
import darren.assistant.tasks.EventsTask;
import darren.assistant.tasks.Task;
import darren.assistant.tasks.ToDoTask;
import darren.assistant.ui.DarrenAssistantException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    /*
    * We have to split between each case, and parse differently due to difference in length and what it represents at each index
     */
    public static Command parseTask(String line) throws DarrenAssistantException {
        String input = line.trim();
        if (input.isEmpty()) {
            throw new DarrenAssistantException("Empty command.");
        }

        String low = input.toLowerCase();

        if (low.equals("bye")) {
            return new ExitCommand();
        }

        if (low.equals("list")) {
            return new ListCommand();
        }

        if (low.startsWith("todo ")) {
            return new AddTodoCommand(input.substring(5).trim());
        }

        if (low.startsWith("deadline ")) {
            String s = input.substring(9).trim();
            String[] p = s.split("(?i)\\s*/by\\s+", 2);
            if (p.length < 2) {
                throw new DarrenAssistantException("Use: deadline <desc> /by <when>");
            }
            return new AddDeadlineCommand(p[0].trim(), p[1].trim());
        }

        if (low.startsWith("event ")) {
            String s = input.substring(6).trim();
            String[] f = s.split("(?i)\\s*/from\\s+", 2);
            String[] t = (f.length == 2) ? f[1].split("(?i)\\s*/to\\s+", 2) : new String[0];
            if (f.length < 2 || t.length < 2) {
                throw new DarrenAssistantException("Use: event <desc> /from <start> /to <end>");
            }
            return new AddEventCommand(f[0].trim(), t[0].trim(), t[1].trim());
        }

        if (low.startsWith("mark ")) {
            return new MarkCommand(parseIndex(input));
        }

        if (low.startsWith("unmark ")) {
            return new UnmarkCommand(parseIndex(input));
        }

        if (low.startsWith("delete ")) {
            return new DeleteCommand(parseIndex(input));
        }

        if (low.startsWith("find ")) {
            return new FindCommand(input.substring(5).trim());
        }

        throw new DarrenAssistantException("Sorry, I don't understand that");
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
    public static LocalDateTime parseDateTime(String s) {
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

    /*
    * Helper function to split the input for mark, unmark and delete commands
     */
    private static int parseIndex(String input) throws DarrenAssistantException {
        String[] p = input.trim().split("\\s+");
        if (p.length < 2) throw new DarrenAssistantException("Missing number.");
        try {
            int oneBased = Integer.parseInt(p[1]);
            return oneBased - 1; // convert to 0-based
        } catch (NumberFormatException e) {
            throw new DarrenAssistantException("That’s not a number.");
        }
    }

    /*
    * Parse used for storage
     */
    public static Task parseStoredTask(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) throw new IllegalArgumentException("Bad line");

        String type = parts[0].trim().toUpperCase();
        boolean done = "1".equals(parts[1].trim());
        String desc = parts[2].trim();

        switch (type) {
            case "T": {
                ToDoTask t = new ToDoTask(desc);
                if (done) t.markAsDone();
                return t;
            }
            case "D": {
                if (parts.length < 4) throw new IllegalArgumentException("Deadline missing /by");
                var by = parseDateTime(parts[3].trim());
                DeadlinesTask t = new DeadlinesTask(desc, by);
                if (done) t.markAsDone();
                return t;
            }
            case "E": {
                if (parts.length < 5) throw new IllegalArgumentException("Event missing /from or /to");
                var from = parseDateTime(parts[3].trim());
                var to   = parseDateTime(parts[4].trim());
                EventsTask t = new EventsTask(desc, from, to);
                if (done) t.markAsDone();
                return t;
            }
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}
