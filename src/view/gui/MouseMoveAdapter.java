package view.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controller.Point;
import controller.ShapeMoveCommand;
import model.ShapeColor;
import model.interfaces.IShapeListSubject;
import model.ShapeConfiguration;
import model.interfaces.IApplicationState;


public class MouseMoveAdapter extends MouseAdapter {
    private Point startPoint;
    private Point endPoint;
    private IApplicationState applicationState;
    private IShapeListSubject shapeList;
    private ShapeConfiguration shapeConfiguration;
    ArrayList<ShapeColor> shapecolor = new ArrayList();


    public MouseMoveAdapter(IApplicationState applicationState, IShapeListSubject shapeList, ShapeConfiguration shapeConfiguration) {
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.shapeConfiguration = shapeConfiguration;


    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
        applicationState.setStartPoint(startPoint);


    }


    @Override
    public void mouseReleased(MouseEvent e) {

        endPoint = new Point(e.getX(), e.getY());
        applicationState.setEndPoint(endPoint);
        System.out.println("Shape move executed." + "Point (Start): " + "(" + startPoint.getX() + "," + startPoint.getY() + ")" + "Point(End): " + "(" + endPoint.getX() + "," + endPoint.getY() + ")");
        ShapeMoveCommand newMove = new ShapeMoveCommand(applicationState, shapeList, shapeConfiguration);
        newMove.run();
    }
}
