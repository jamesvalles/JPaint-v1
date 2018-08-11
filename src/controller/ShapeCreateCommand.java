package controller;

import interfaces.IUndoable;
import model.interfaces.IShapeListSubject;
import model.ShapeConfiguration;
import model.ShapeFactory;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;

public class ShapeCreateCommand implements ICommand, IUndoable {

    ShapeFactory shapeFactory = new ShapeFactory();
    private final IApplicationState applicationState;
    private ShapeConfiguration shapeConfiguration;
    private IShapeListSubject shapeList;


    private IDrawShape shape;

    public ShapeCreateCommand(IApplicationState applicationState, IShapeListSubject shapeList, ShapeConfiguration shapeConfiguration) {
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.shapeConfiguration = shapeConfiguration;
    }

    @Override
    public void run() {
        //  System.out.println("ShapeCreateCommand running... ");
        shapeConfiguration = applicationState.getCurrentShapeConfiguration();
        shape = shapeFactory.createShape(shapeConfiguration);
        this.shapeList.add(shape);
        CommandHistory.add(this);

    }

    public IDrawShape getShape() {
        return shape;
    }

    @Override
    public void undo() {
        shapeList.remove(shape);
    }

    @Override
    public void redo() {
        shapeList.add(shape);
    }


}
