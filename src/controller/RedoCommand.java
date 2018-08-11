package controller;

public class RedoCommand implements ICommand {
    @Override
    public void run() {
        System.out.println("RedoCommand executed.");
        CommandHistory.redo();
        //  CommandHistory.printCommandHistory();
    }
}
