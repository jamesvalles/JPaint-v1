package view.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controller.Point;
import controller.ShapeCreateCommand;
import controller.ShapeMoveCommand;
import controller.ShapeSelectCommand;
import model.ShapeColor;
import model.dialogs.DialogProvider;
import model.interfaces.IShapeListSubject;
import model.ShapeConfiguration;
import model.StartAndEndPointMode;
import model.interfaces.IApplicationState;
import org.junit.Test;
import view.interfaces.IUiModule;

import javax.swing.*;


public class DrawClickHandler extends MouseAdapter {
    private Point startPoint;
    private Point endPoint;
    private IApplicationState applicationState;
    private IShapeListSubject shapeList;
    private ShapeConfiguration shapeConfiguration;
    ArrayList<ShapeColor> shapecolor = new ArrayList();


    public DrawClickHandler(IApplicationState applicationState, IShapeListSubject shapeList, ShapeConfiguration shapeConfiguration) {
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.shapeConfiguration = shapeConfiguration;


    }

    //Extra credit - Left click to change color, Right click to revert back to previous color.

    @Override
    public void mouseClicked(MouseEvent e) {
        if (applicationState.getActiveStartAndEndPointMode() == StartAndEndPointMode.DRAW) {
            // shapecolor.clear();

            ShapeColor primaryColor = applicationState.getActivePrimaryColor();
            shapecolor.add(primaryColor);
            ShapeColor secondaryColor = applicationState.getActiveSecondaryColor();
            shapecolor.add(secondaryColor);

            if (SwingUtilities.isLeftMouseButton(e)) {
                System.out.println("LEFT MOUSE>> Color Flip");
                applicationState.setActivePrimaryColor(shapecolor.get(0));
                applicationState.setActiveSecondaryColor(shapecolor.get(1));

            } else if (SwingUtilities.isRightMouseButton(e)) {
                System.out.println("<<RIGHT MOUSE Color Flip");
                applicationState.setActivePrimaryColor(shapecolor.get(1));
                applicationState.setActiveSecondaryColor(shapecolor.get(0));
            } else {
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
        applicationState.setStartPoint(startPoint);
        System.out.println("DrawClickHandler reporting Mouse was pressed. Point (Start): " + "(" + startPoint.getX() + "," + startPoint.getY() + ")");

    }


    @Override
    public void mouseReleased(MouseEvent e) {

        endPoint = new Point(e.getX(), e.getY());
        applicationState.setEndPoint(endPoint);
        StartAndEndPointMode mouseMode = applicationState.getActiveStartAndEndPointMode();
        System.out.println("DrawClickHandler reporting Mouse was released. Point(End): " + "(" + endPoint.getX() + "," + endPoint.getY() + ")");

        switch (mouseMode) {
            case DRAW:
                ShapeCreateCommand newShape = new ShapeCreateCommand(applicationState, shapeList, shapeConfiguration);
                newShape.run();
                break;

            case MOVE:
                ShapeMoveCommand newMove = new ShapeMoveCommand(applicationState, shapeList, shapeConfiguration);
                newMove.run();
                break;

            case SELECT:
                ShapeSelectCommand newSelect = new ShapeSelectCommand(applicationState, shapeList, shapeConfiguration);
                newSelect.run();
                break;
        }
    }


}
