package darren.assistant;

import darren.assistant.commands.Command;
import darren.assistant.storage.Storage;
import darren.assistant.tasks.TaskList;
import darren.assistant.ui.DarrenAssistantException;
import darren.assistant.ui.Ui;

import java.util.Scanner;


/**
 * The core driver class for DarrenAssistant.
 * <p>
 * {@code DarrenJr} is responsible for:
 * <ul>
 *   <li>Reading user input from the console</li>
 *   <li>Parsing input into {@link Command} objects</li>
 *   <li>Executing commands to manipulate the {@link TaskList}</li>
 *   <li>Handling storage persistence via {@link Storage}</li>
 * </ul>
 */
class DarrenJr {
    /** Scanner for reading user input. */
    private final Scanner sc;

    /** Handles UI printing and messages. */
    private final Ui ui = new Ui();

    /** Storage manager for saving and loading tasks. */
    private final Storage storage = new Storage(java.nio.file.Paths.get("data", "darren.assisant.DarrenAssistant.txt"));

    /** The in-memory list of tasks. */
    private final TaskList tasks = new TaskList();

    /**
     * Constructs a {@code DarrenJr} instance and initializes the input scanner.
     */
    public DarrenJr() {
        sc = new Scanner(System.in);
    }

    /**
     * Starts the main program loop.
     * <p>
     * This method:
     * <ul>
     *   <li>Prints the welcome message</li>
     *   <li>Continuously reads user input</li>
     *   <li>Parses input into {@link Command} objects</li>
     *   <li>Executes commands until the exit command is given</li>
     * </ul>
     */
    public void run() {
        Ui.printWelcome();
        boolean isExit = false;

        while(!isExit) {
            try {
                String raw = sc.nextLine();
                Command c = Parser.parseTask(raw);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DarrenAssistantException e) {
                Ui.printError(e.getMessage());
            } catch (Exception e) {
                Ui.printError("Unexpected error: " + e.getMessage());
            }
        }
    }
}




