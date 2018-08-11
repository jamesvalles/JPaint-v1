package view.interfaces;

import controller.Point;
import model.ShapeConfiguration;
import model.ShapeShadingType;
import model.ShapeType;

import java.awt.*;

public interface IDrawShape {
    void paint(Graphics g);

    boolean contains(Point start_Point);

    Point getStartPoint();

    Point getEndPoint();

    void addX(int dx);

    void addY(int dy);

    void setAdjustedEnd(Point adjustedEnd);

    void setAdjustedStart(Point adjustedStart);

    Point getAdjustedStart();

    Point getAdjustedEnd();

    ShapeConfiguration getShapeConfiguration();

    int getWidth();

    int getHeight();
}