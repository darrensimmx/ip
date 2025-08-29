public class Parser {
    public static Task parseTask(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) throw new IllegalArgumentException("bad line");

        String type = parts[0].trim();
        boolean done = "1".equals(parts[1].trim());
        String desc = parts[2].trim();
        String from  = parts[3].trim();
        String to    = parts[4].trim();

        switch (type) {
            case "T":
                return new ToDoTask(desc);
            case "D":
                return new DeadlinesTask(desc, parts[3].trim());
            case "E":
                return new EventsTask(desc, from, to);
            default:
                throw new IllegalArgumentException("unknown type: " + type);
        }
    }
}
