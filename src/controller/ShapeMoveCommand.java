package controller;

import interfaces.IUndoable;
import model.interfaces.IShapeListSubject;
import model.ShapeConfiguration;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;
import java.util.ArrayList;


public class ShapeMoveCommand implements ICommand, IUndoable {
    private IApplicationState applicationState;
    private IShapeListSubject shapeList;
    private ShapeConfiguration shapeConfiguration;
    private IDrawShape old_shape;
    private IDrawShape new_shape;
    private ArrayList<IDrawShape> tempShapeList;


    public ShapeMoveCommand(IApplicationState applicationState, IShapeListSubject shapeList, ShapeConfiguration shapeConfiguration) {
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.shapeConfiguration = shapeConfiguration;
    }

    @Override
    public void run() {


        tempShapeList = new ArrayList<IDrawShape>();

        int dx = applicationState.getEndPoint().getX() - applicationState.getStartPoint().getX();
        int dy = applicationState.getEndPoint().getY() - applicationState.getStartPoint().getY();

        for (IDrawShape selectedShape : shapeList.getSelectedShapesList()) {
            old_shape = selectedShape;
            tempShapeList.add(old_shape);
            shapeList.remove(old_shape);

            for (IDrawShape tempShape : tempShapeList) {
                tempShape.addX(dx);
                tempShape.addY(dy);
                new_shape = tempShape;
                shapeList.add(new_shape);
            }
            tempShapeList.clear();
        }
        CommandHistory.add(this);
    }


    @Override
    public void undo() {
       // System.out.println("Move undo executed.");
        shapeList.remove(new_shape);
        shapeList.add(old_shape);

    }

    @Override
    public void redo() {
        //System.out.println("Move redo executed.");
        shapeList.add(new_shape);
        shapeList.remove(old_shape);

    }
}
