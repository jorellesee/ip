package DukePackage;

import CommandPackage.Command;
import ExceptionsPackage.DukeException;
import StoragePackage.Storage;
import TaskPackage.TaskList;
import UiPackage.Ui;
import ParserPackage.Parser;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.retrieveTasks();
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        String path = home + "/Desktop/tasks.txt";
        new Duke(path).run();
    }
}



