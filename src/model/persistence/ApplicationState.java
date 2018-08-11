package model.persistence;

import controller.Point;
import model.*;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import model.interfaces.IShapeListSubject;
import view.interfaces.IUiModule;
import view.interfaces.IMouseAdapterObserver;

import java.util.ArrayList;

public class ApplicationState implements IApplicationState {
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;
    private IShapeListSubject shapeList;
    private ArrayList<IMouseAdapterObserver> mouseObservers = new ArrayList<IMouseAdapterObserver>();
    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;
    private Point startPoint;
    private Point endPoint;
    private Point adjustedStart;
    private Point adjustedEnd;
    private int width;
    private int height;


    public ShapeConfiguration getCurrentShapeConfiguration() {
        ShapeConfiguration shapeConfig = new ShapeConfiguration();
        shapeConfig.setPrimaryColor(activePrimaryColor);
        shapeConfig.setSecondaryColor(activeSecondaryColor);
        shapeConfig.setShadingType(activeShapeShadingType);
        shapeConfig.setShapeType(activeShapeType);
        shapeConfig.setEndPoint(endPoint);
        shapeConfig.setStartPoint(startPoint);
        shapeConfig.setAdjustedStart(adjustedStart);
        shapeConfig.setAdjustedEnd(adjustedEnd);
        shapeConfig.setWidth(width);
        shapeConfig.setHeight(height);

        return shapeConfig;
    }



    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.shapeList = shapeList;
        this.dialogProvider = new DialogProvider(this);
        setDefaults();
    }

    private void setDefaults() {
        activeShapeType = ShapeType.ELLIPSE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeStartAndEndPointMode = StartAndEndPointMode.DRAW;
    }

    public void setActivePrimaryColor(ShapeColor activePrimaryColor) {
        this.activePrimaryColor = activePrimaryColor;
    }

    public void setActiveSecondaryColor(ShapeColor activeSecondaryColor) {
        this.activeSecondaryColor = activeSecondaryColor;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public void setWidth(int width) {
        Point adjustedStart = getAdjustedStart();
        Point adjustedEnd = getAdjustedEnd();
        this.width = adjustedEnd.getX() - adjustedStart.getX();
    }

    public void setHeight(int height) {
        Point adjustedStart = getAdjustedStart();
        Point adjustedEnd = getAdjustedEnd();
        this.height = adjustedEnd.getY() - adjustedStart.getY();
    }

    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
        System.out.println("IApplicationState - changed ShapeConfiguration: " + activeShapeType);
    }

    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
        System.out.println("IApplicationState - changed Primary Color " + activePrimaryColor);
    }

    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
        System.out.println("IApplicationState - changed Secondary Color: " + activeSecondaryColor);
    }

    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
        System.out.println("IApplicationState - changed Shading Type: " + activeShapeShadingType);
    }

    @Override
    public void setActiveStartAndEndPointMode() {
        activeStartAndEndPointMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
        System.out.println("IApplicationState - changed Mouse Mode: " + activeStartAndEndPointMode);
        notifyObservers();

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }

    @Override
    public StartAndEndPointMode getActiveStartAndEndPointMode() {
        return activeStartAndEndPointMode;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getAdjustedStart() {
        int startX = Math.min(startPoint.getX(), endPoint.getX());
        int startY = Math.min(startPoint.getY(), endPoint.getY());
        adjustedStart = new Point(startX, startY);
        return adjustedStart;
    }

    public Point getAdjustedEnd() {
        int endX = Math.max(startPoint.getX(), endPoint.getX());
        int endY = Math.max(startPoint.getY(), endPoint.getY());
        adjustedEnd = new Point(endX, endY);
        return adjustedEnd;
    }

    public void setAdjustedStart(Point adjustedStart) {
        this.adjustedStart = adjustedStart;
    }

    public void setAdjustedEnd(Point adjustedEnd) {
        this.adjustedEnd = adjustedEnd;
    }


    @Override
    public void registerObserver(IMouseAdapterObserver o) {
        mouseObservers.add(o);
    }

    @Override
    public void notifyObservers() {
        // System.out.println("mouse observer size " + mouseObservers.size());
        for (IMouseAdapterObserver observer : mouseObservers) {
            observer.run();
            // System.out.println("NOOOOOOOOOTIFIED OBSERVER");

        }
    }
}


