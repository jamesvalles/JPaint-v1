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


public class MouseDrawAdapter extends MouseAdapter {
    private Point startPoint;
    private Point endPoint;
    private IApplicationState applicationState;
    private IShapeListSubject shapeList;
    private ShapeConfiguration shapeConfiguration;
    ArrayList<ShapeColor> shapecolor = new ArrayList();


    public MouseDrawAdapter(IApplicationState applicationState, IShapeListSubject shapeList, ShapeConfiguration shapeConfiguration) {
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.shapeConfiguration = shapeConfiguration;


    }

    @Override
    public void mouseClicked(MouseEvent e) {

        ShapeColor primaryColor = applicationState.getActivePrimaryColor();
        shapecolor.add(primaryColor);
        ShapeColor secondaryColor = applicationState.getActiveSecondaryColor();
        shapecolor.add(secondaryColor);

        if (SwingUtilities.isLeftMouseButton(e)) {
            System.out.println("Left click detected. Color Flip undo executed.");
            applicationState.setActivePrimaryColor(shapecolor.get(0));
            applicationState.setActiveSecondaryColor(shapecolor.get(1));

        } else if (SwingUtilities.isRightMouseButton(e)) {
            System.out.println("Right click detected. Color Flip executed.");
            applicationState.setActivePrimaryColor(shapecolor.get(1));
            applicationState.setActiveSecondaryColor(shapecolor.get(0));
        } else {

        }

    }


    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
        applicationState.setStartPoint(startPoint);
        System.out.println("Point (Start): " + "(" + startPoint.getX() + "," + startPoint.getY() + ")");

    }


    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("DRAW IS RUNNNNING");
        endPoint = new Point(e.getX(), e.getY());
        applicationState.setEndPoint(endPoint);
        StartAndEndPointMode mouseMode = applicationState.getActiveStartAndEndPointMode();
        System.out.println("Point(End): " + "(" + endPoint.getX() + "," + endPoint.getY() + ")");
        ShapeCreateCommand newShape = new ShapeCreateCommand(applicationState, shapeList, shapeConfiguration);
        newShape.run();
    }
}
