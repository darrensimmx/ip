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
            String input = sc.nextLine();

            if (input.toLowerCase().equals("bye")) {
                Ui.printGoodbye();
                break;
            } else if (input.toLowerCase().equals("list")) {
                Ui.printList(tasks);
            } else {
                Task t = new Task(input);
                tasks.add(t);
                ui.printAdded(t);
            }

            Ui.printEcho(input);
        }
    }
}