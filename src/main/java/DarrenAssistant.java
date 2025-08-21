import java.util.Scanner;

public class DarrenAssistant {
    public static void main(String[] args) {
        new Duke().run();
    }
}

class Duke {
    private final Scanner sc;

    //Constructor
    public Duke() {
        sc = new Scanner(System.in);
    }

    public void run() {
        Ui.printWelcome();

        while (true) {
            String input = sc.nextLine();

            if (input.toLowerCase().equals("bye")) {
                Ui.printGoodbye();
                break;
            }

            Ui.printEcho(input);
        }
    }
}

class Ui {
    public static void printWelcome() {
        printLine();
        System.out.println("Hello! Im DarrenAssistant, the finest chatbot you will find out there!\n What can I do for you?");
        printLine();
    }

    public static void printEcho(String input) {
        printLine();
        System.out.println(input);
        printLine();
    }

    public static void printGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void printLine() {
        System.out.println("_____________________________________________________");
    }
}