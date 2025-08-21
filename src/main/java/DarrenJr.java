import java.util.Scanner;

class DarrenJr {
    private final Scanner sc;
    private final Ui ui = new Ui();
    private final TaskList tasks = new TaskList();

    //Constructor
    public DarrenJr() {
        sc = new Scanner(System.in);
    }

    public void run() {
        Ui.printWelcome();

        while (true) {
            String input = sc.nextLine().trim();

            try {
                if (input.equalsIgnoreCase("bye")) {
                    Ui.printGoodbye();
                    break;
                }

                if (input.equalsIgnoreCase("list")) {
                    Ui.printList(tasks);
                    continue;
                }

                if (input.startsWith("mark ")) {
                    String[] p = input.split("\\s+");
                    if (p.length < 2) throw new DarrenAssistantException("Usage: mark <number>");
                    int idx = Integer.parseInt(p[1]) - 1; // 0-based
                    if (idx < 0 || idx >= tasks.size()) throw new DarrenAssistantException("No such task: " + (idx + 1));
                    Task t = tasks.get(idx);
                    t.markAsDone();
                    Ui.printMarked(t);
                    continue;
                }

                if (input.startsWith("unmark ")) {
                    String[] p = input.split("\\s+");
                    if (p.length < 2) throw new DarrenAssistantException("Usage: unmark <number>");
                    int idx = Integer.parseInt(p[1]) - 1;
                    if (idx < 0 || idx >= tasks.size()) throw new DarrenAssistantException("No such task: " + (idx + 1));
                    Task t = tasks.get(idx);
                    t.markAsNotDone();
                    Ui.printUnmarked(t);
                    continue;
                }

                if (input.equals("todo")) {
                    throw new DarrenAssistantException("Todo needs a description.");
                }
                if (input.startsWith("todo ")) {
                    String desc = input.substring(5).trim();
                    if (desc.isEmpty()) throw new DarrenAssistantException("Todo needs a description.");
                    Task t = new ToDoTask(desc);
                    tasks.add(t);
                    Ui.printAdded(t);
                    continue;
                }

                if (input.equals("deadline")) {
                    throw new DarrenAssistantException("Use: deadline <desc> /by <when>");
                }
                if (input.startsWith("deadline ")) {
                    String s = input.substring(9).trim();
                    int by = s.toLowerCase().lastIndexOf(" /by ");
                    if (by < 0) throw new DarrenAssistantException("Use: deadline <desc> /by <when>");
                    String desc = s.substring(0, by).trim();
                    String when = s.substring(by + 5).trim();
                    if (desc.isEmpty() || when.isEmpty()) throw new DarrenAssistantException("Use: deadline <desc> /by <when>");
                    Task t = new DeadlinesTask(desc, when);
                    tasks.add(t);
                    Ui.printAdded(t);
                    continue;
                }

                if (input.equals("event")) {
                    throw new DarrenAssistantException("Use: event <desc> /from <start> /to <end>");
                }
                if (input.startsWith("event ")) {
                    String s = input.substring(6).trim();
                    int f = s.toLowerCase().lastIndexOf(" /from ");
                    int to = s.toLowerCase().lastIndexOf(" /to ");
                    if (f < 0 || to < 0 || to < f) throw new DarrenAssistantException("Use: event <desc> /from <start> /to <end>");
                    String desc = s.substring(0, f).trim();
                    String from = s.substring(f + 7, to).trim();
                    String end  = s.substring(to + 5).trim();
                    if (desc.isEmpty() || from.isEmpty() || end.isEmpty()) throw new DarrenAssistantException("Use: event <desc> /from <start> /to <end>");
                    Task t = new EventsTask(desc, from, end);
                    tasks.add(t);
                    Ui.printAdded(t);
                    continue;
                }

                // everything else
                throw new DarrenAssistantException("Sorry, I don't understand that.");

            } catch (NumberFormatException e) {
                Ui.printError("That’s not a number.");
            } catch (DarrenAssistantException e) {
                Ui.printError(e.getMessage());
            }
        }
    }


}

