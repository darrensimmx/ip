public class Ui {
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

    public static void printAdded(Task t) {
        printLine();
        System.out.println("added: " + t);
        printLine();
    }

    public static void printGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printMarked(Task t) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t);
        printLine();
    }

    public static void printUnmarked(Task t) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
        printLine();
    }

    public static void printList(TaskList tasks) {
        printLine();
        System.out.println(tasks.toNumberedString());
        printLine();
    }


    private static void printLine() {
        System.out.println("_____________________________________________________");
    }
}
