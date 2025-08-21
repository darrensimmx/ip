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

            } else {
                Task t = new Task(input);
                tasks.add(t);
                Ui.printAdded(t);
            }
        }
    }

}