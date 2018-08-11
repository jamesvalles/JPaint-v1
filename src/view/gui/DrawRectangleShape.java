package view.gui;

import controller.Point;
import model.ShapeConfiguration;
import model.ShapeShadingType;
import model.ShapeType;
import model.dialogs.ColorSingleton;
import view.interfaces.IDrawShape;

import java.awt.*;

public class DrawRectangleShape implements IDrawShape {

    private ShapeConfiguration shapeConfiguration;
    private ShapeShadingType shapeShadingType;
    private Color primaryColor;
    private Color secondaryColor;
    private int width;
    private int height;
    private Point adjustedStart;
    private Point adjustedEnd;
    private Point startPoint;
    private Point endPoint;
    private ShapeType shapeType;

    public DrawRectangleShape(ShapeConfiguration shapeConfiguration) {
        this.shapeConfiguration = shapeConfiguration;
        this.shapeShadingType = shapeConfiguration.getShadingType();
        this.primaryColor = ColorSingleton.getColor(shapeConfiguration.getPrimaryColor());
        this.secondaryColor = ColorSingleton.getColor(shapeConfiguration.getSecondaryColor());
        this.width = shapeConfiguration.getWidth();
        this.height = shapeConfiguration.getHeight();
        this.adjustedStart = shapeConfiguration.getAdjustedStart();
        this.adjustedEnd = shapeConfiguration.getAdjustedEnd();
        this.startPoint = shapeConfiguration.getStartPoint();
        this.shapeType = shapeConfiguration.getShapeType();
        this.endPoint = shapeConfiguration.getEndPoint();
    }


    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (shapeShadingType.equals(ShapeShadingType.OUTLINE)) {
            g.setColor(primaryColor);
            g2.setStroke(new BasicStroke(8));
            g.drawRect(adjustedStart.getX(), adjustedStart.getY(), width, height);
        } else if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)) {
            g.setColor(secondaryColor);
            g.fillRect(adjustedStart.getX(), adjustedStart.getY(), width, height);
        } else if (shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            g.setColor(primaryColor);
            g2.setStroke(new BasicStroke(8));
            g.drawRect(adjustedStart.getX(), adjustedStart.getY(), width, height);
            g.setColor(secondaryColor);
            g.fillRect(adjustedStart.getX(), adjustedStart.getY(), width, height);
        }
    }

    public boolean contains(Point startPoint) {
        return (adjustedStart.getX() < startPoint.getX() && adjustedStart.getY() < startPoint.getY()
                && adjustedStart.getX() + width > startPoint.getX() && adjustedStart.getY() + height > startPoint.getY());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return adjustedEnd;
    }


    @Override
    public void setAdjustedStart(Point adjustedStart) {
        this.adjustedStart = adjustedStart;
    }

    @Override
    public void setAdjustedEnd(Point adjustedEnd) {
        this.adjustedEnd = adjustedEnd;
    }

    public Point getAdjustedStart() {
        return adjustedStart;
    }

    @Override
    public Point getAdjustedEnd() {
        return adjustedEnd;
    }

    @Override
    public void addX(int dx) {
        adjustedStart.setX(adjustedStart.getX() + dx);
        adjustedEnd.setX(adjustedEnd.getX() + dx);

    }

    @Override
    public void addY(int dy) {
        adjustedStart.setY(adjustedStart.getY() + dy);
        adjustedEnd.setY(adjustedEnd.getY() + dy);

    }


    public ShapeConfiguration getShapeConfiguration() {
        return shapeConfiguration;
    }
}

