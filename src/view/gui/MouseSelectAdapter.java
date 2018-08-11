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

import view.interfaces.IUiModule;

import javax.swing.*;


public class MouseSelectAdapter extends MouseAdapter {
    private Point startPoint;
    private Point endPoint;
    private IApplicationState applicationState;
    private IShapeListSubject shapeList;
    private ShapeConfiguration shapeConfiguration;
    ArrayList<ShapeColor> shapecolor = new ArrayList();


    public MouseSelectAdapter(IApplicationState applicationState, IShapeListSubject shapeList, ShapeConfiguration shapeConfiguration) {
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.shapeConfiguration = shapeConfiguration;


    }

    @Override
    public void mousePressed(MouseEvent e) {
        // System.out.println("SSSSSELLLLLECT IS RUNNING");
        startPoint = new Point(e.getX(), e.getY());
        applicationState.setStartPoint(startPoint);
        //  System.out.println("DrawClickHandler reporting Mouse was pressed. Point (Start): " + "(" + startPoint.getX() + "," + startPoint.getY() + ")");

    }


    @Override
    public void mouseReleased(MouseEvent e) {

        endPoint = new Point(e.getX(), e.getY());
        applicationState.setEndPoint(endPoint);
        StartAndEndPointMode mouseMode = applicationState.getActiveStartAndEndPointMode();
        //  System.out.println("DrawClickHandler reporting Mouse was released. Point(End): " + "(" + endPoint.getX() + "," + endPoint.getY() + ")");
        ShapeSelectCommand newSelect = new ShapeSelectCommand(applicationState, shapeList, shapeConfiguration);
        newSelect.run();
    }


}
