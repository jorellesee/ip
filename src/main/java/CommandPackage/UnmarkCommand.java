package CommandPackage;

import CommandPackage.Command;
import ExceptionsPackage.DukeException;
import TaskPackage.TaskList;
import TaskPackage.Task;
import UiPackage.Ui;
import StoragePackage.Storage;

public class UnmarkCommand extends Command {
    protected int index;
    public UnmarkCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.getTasks().get(index - 1).markAsNotDone();
            System.out.println("____________________________________________________________\n" +
                    "Nice! I've marked this task as undone:\n" +
                    tasks.getTasks().get(index - 1).toString() +
                    "\n____________________________________________________________");
            storage.saveTasks(tasks);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Task index is out of bounds");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}