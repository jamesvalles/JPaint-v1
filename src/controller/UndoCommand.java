package controller;

import controller.ICommand;

public class UndoCommand implements ICommand {

    @Override
    public void run() {
        System.out.println("UndoCommand executed.");
        CommandHistory.undo();
        // CommandHistory.printCommandHistory();
    }
}
