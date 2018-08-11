package controller;

import interfaces.IUndoable;
import model.interfaces.IShapeListSubject;
import model.ShapeConfiguration;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;

import java.util.ArrayList;

public class PasteCommand implements ICommand, IUndoable {
    private IShapeListSubject shapeList;
    private IApplicationState applicationState;
    private ShapeConfiguration shapeConfiguration;
    private IDrawShape duplicatedShape;
    private IDrawShape oldshape;
    private IDrawShape newShape;
    private final ArrayList<IDrawShape> tempShapeList = new ArrayList<IDrawShape>();


    public PasteCommand(IApplicationState applicationState, IShapeListSubject shapeList, ShapeConfiguration shapeConfiguration) {
        this.applicationState = applicationState;
        this.shapeConfiguration = shapeConfiguration;
        this.shapeList = shapeList;
    }

    public void run() {

        for (IDrawShape selectedShape : shapeList.getSelectedShapesList()) {
            newShape = selectedShape;
            selectedShape.addX(50);
            selectedShape.addY(50);

            ShapeCreateCommand shape = new ShapeCreateCommand(applicationState, shapeList, selectedShape.getShapeConfiguration());
            tempShapeList.add(shape.shapeFactory.createShape(selectedShape.getShapeConfiguration()));
        }

        for (IDrawShape selectedShape : tempShapeList) {
            shapeList.add(selectedShape);
           // shapeList.notifyObserver();
        }

        CommandHistory.add(this);
        //shapeList.getSelectedShapesList().removeAll(shapeList.getSelectedShapesList());
    }

    @Override
    public void undo() {

        shapeList.remove(newShape);
    //    shapeList.notifyObserver();
    }

    @Override
    public void redo() {
        shapeList.add(newShape);

    }
}