package controller;

import model.interfaces.IShapeListSubject;
import model.ShapeConfiguration;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;

public class CopyCommand implements ICommand {
    IApplicationState applicationState;
    IShapeListSubject shapeList;
    ShapeConfiguration shapeConfiguration;

    public CopyCommand(IApplicationState applicationState, IShapeListSubject shapeList, ShapeConfiguration shapeConfiguration) {
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.shapeConfiguration = shapeConfiguration;
    }

    @Override
    public void run() {
        for (IDrawShape shape : shapeList.getSelectedShapesList()) {
            shapeList.addClipBoardShapes(shape);
        }
        System.out.println(shapeList.getClipBoard().size() + " Shapes Copied.");

        //ShapeCreateCommand newSelector = new ShapeCreateCommand(applicationState, shapeList, shapeConfiguration);
        //newSelector.run();
    }
}
