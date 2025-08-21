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

            if (input.equalsIgnoreCase("bye")) {
                Ui.printGoodbye();
                break;

            } else if (input.equalsIgnoreCase("list")) {
                Ui.printList(tasks);

            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1; // 1-based input
                Task t = tasks.get(index);
                t.markAsDone();
                Ui.printMarked(t);

            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Task t = tasks.get(index);
                t.markAsNotDone();
                Ui.printUnmarked(t);

            } else if (input.startsWith("todo ")){
                String description = input.substring(5).trim();
                Task t = new ToDoTask(description);
                tasks.add(t);
                Ui.printAdded(t);
            } else if (input.startsWith("deadline ")) {
                String s = input.substring(9).trim(); //removes deadline
                int byIdx = s.toLowerCase().lastIndexOf(" /by "); //index where by starts
                String description = s.substring(0, byIdx).trim();
                String by = s.substring(byIdx + 5).trim();
                Task t = new DeadlinesTask(description, by);
                tasks.add(t);
                Ui.printAdded(t);
            } else if (input.startsWith("event ")) {
                String s = input.substring(6).trim();
                int fromIdx = s.toLowerCase().lastIndexOf(" /from ");
                int toIdx   = s.toLowerCase().lastIndexOf(" /to ");

                String description = s.substring(0, fromIdx).trim();
                String from = s.substring(fromIdx + 7, toIdx).trim();
                String to   = s.substring(toIdx + 5).trim();

                Task t = new EventsTask(description, from, to);
                tasks.add(t);
                Ui.printAdded(t);
            } else {
                //unknown command
                System.out.println("Unknown command. Try: todo, deadline, event, list, mark, unmark, bye");
            }
        }
    }

}