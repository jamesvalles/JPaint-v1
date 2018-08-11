package model.interfaces;

import controller.Point;
import model.*;
import view.interfaces.IMouseAdapterObserver;

public interface IApplicationState {
    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    StartAndEndPointMode getActiveStartAndEndPointMode();

    ShapeConfiguration getCurrentShapeConfiguration();

    void setStartPoint(Point startPoint);

    void setEndPoint(Point endPoint);

    Point getStartPoint();

    Point getEndPoint();

    Point getAdjustedStart();

    Point getAdjustedEnd();

    void setActivePrimaryColor(ShapeColor activePrimaryColor);

    void setActiveSecondaryColor(ShapeColor activeSecondaryColor);

    void registerObserver(IMouseAdapterObserver o);

    void notifyObservers();

}
