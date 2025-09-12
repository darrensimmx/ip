package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;

public class Ui {
    public static void printWelcome() {
        printLine();
        System.out.println("Hello! Im darren.assisant.DarrenAssistant, the finest chatbot you will find out there!\n What can I do for you?");
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

    public static void printError(String msg) {
        printLine();
        System.out.println(msg);
        printLine();
    }

    public static void printDeleted(Task t, int count) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + t);
        System.out.println("Now you have " +  count + " tasks in the list.");
        printLine();
    }

    public static void printFindResult(List<Task> matches) {
        printLine();
        if (matches.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int i = 1;
            for (Task t : matches) {
                System.out.println(i + "." + t);
                i++;
            }
        }
        printLine();
    }

    public String formatAdded(Task t, int total) {
        return "Got it. I've added this task:\n  " + t
                + "\nNow you have " + total + " tasks in the list.";
    }

    public String formatList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "You have no tasks in your list.";
        }
        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String formatDeleted(Task t, int total) {
        return "Noted. I've removed this task:\n  " + t
                + "\nNow you have " + total + " tasks in the list.";
    }

    public String formatGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public String formatFindResult(List<Task> matches, String keyword) {
        if (matches.isEmpty()) {
            return "No tasks found matching: " + keyword;
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < matches.size(); i++) {
            sb.append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String formatMarked(Task t) {
        return "Nice! I've marked this task as done:\n  " + t;
    }

    public String formatUnmarked(Task t) {
        return "OK, I've marked this task as not done yet:\n  " + t;
    }


    private static void printLine() {
        System.out.println("_____________________________________________________");
    }

}
