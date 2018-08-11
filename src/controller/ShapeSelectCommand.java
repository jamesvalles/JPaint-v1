package controller;

import model.interfaces.IShapeListSubject;
import model.ShapeConfiguration;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;

public class ShapeSelectCommand implements ICommand {

    private IShapeListSubject shapeList;
    private IDrawShape selectedShape;
    private IApplicationState applicationState;
    Boolean containsSelectedShape = false;

    public ShapeSelectCommand(IApplicationState applicationState, IShapeListSubject shapeList, ShapeConfiguration shapeConfiguration) {
        this.shapeList = shapeList;
        this.applicationState = applicationState;
    }


    @Override
    public void run() {
        System.out.println("Select mode...");

        for (IDrawShape shape : shapeList.getShapeList()) {
            boolean contain = shape.contains(applicationState.getStartPoint());
            if (contain) {
                containsSelectedShape = true;
                selectedShape = shape;
                shapeList.addSelectedList(selectedShape);
                System.out.println(">> Shape selected. " + shapeList.getSelectedShapesList().size());
                break;
            } else {
                //shapeList.getSelectedShapesList().remove(shape);
                containsSelectedShape = false;
                //System.out.println("Shape not selected.");
            }
        }
        if (containsSelectedShape == false) {
            shapeList.clearSelectedShapeList();
            shapeList.getClipBoard().clear();
            System.out.println("Shape List cleared. Shapes selected: " + shapeList.getSelectedShapesList().size());
        }
    }

    public IDrawShape getSelectedShape() {
        return selectedShape;
    }
}