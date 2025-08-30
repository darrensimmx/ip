package darren.assistant;

import darren.assistant.commands.Command;
import darren.assistant.storage.Storage;
import darren.assistant.tasks.TaskList;
import darren.assistant.ui.DarrenAssistantException;
import darren.assistant.ui.Ui;

import java.util.Scanner;

class DarrenJr {
    private final Scanner sc;
    private final Ui ui = new Ui();
    private final Storage storage = new Storage(java.nio.file.Paths.get("data", "darren.assisant.DarrenAssistant.txt"));
    private final TaskList tasks = new TaskList();

    //Constructor
    public DarrenJr() {
        sc = new Scanner(System.in);
    }

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




