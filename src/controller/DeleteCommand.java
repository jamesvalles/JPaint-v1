package controller;

import interfaces.IUndoable;
import model.interfaces.IShapeListSubject;
import model.ShapeConfiguration;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;

import java.util.ArrayList;


public class DeleteCommand implements ICommand, IUndoable {
    private IShapeListSubject shapeList;
    private IApplicationState applicationState;
    private ShapeConfiguration shapeConfiguration;
    ArrayList<IDrawShape> selectedShapes;

    public DeleteCommand(IApplicationState applicationState, IShapeListSubject shapeList, ShapeConfiguration shapeConfiguration) {
        this.applicationState = applicationState;
        this.shapeConfiguration = shapeConfiguration;
        this.shapeList = shapeList;
    }

    public void run() {

        System.out.println("DeleteCommand executed.");
        selectedShapes = shapeList.getSelectedShapesList();
        for (IDrawShape shape : selectedShapes) {
            shapeList.remove(shape);
            shapeList.notifyObserver();
        }
        System.out.println("Canvas # of shapes: " + shapeList.getShapeList().size());
        CommandHistory.add(this);

    }

    @Override
    public void undo() {
        for (IDrawShape shape : selectedShapes) {
            shapeList.add(shape);
        }
    }

    @Override
    public void redo() {
        for (IDrawShape shape : selectedShapes) {
            shapeList.remove(shape);
        }
    }
}