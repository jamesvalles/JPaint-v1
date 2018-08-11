package view.gui;

import controller.Point;
import model.ShapeConfiguration;
import model.ShapeShadingType;
import model.dialogs.ColorSingleton;
import view.interfaces.IDrawShape;

import java.awt.*;

public class DrawTriangleShape implements IDrawShape {

    private ShapeConfiguration shapeConfiguration;
    private ShapeShadingType shapeShadingType;
    private Color primaryColor;
    private Color secondaryColor;
    private int width;
    private int height;
    private Point adjustedStart;
    private Point adjustedEnd;
    private Point startPoint;
    private int[] x = new int[3];
    private int[] y = new int[3];


    public DrawTriangleShape(ShapeConfiguration shapeConfiguration) {
        this.shapeConfiguration = shapeConfiguration;
        this.shapeShadingType = shapeConfiguration.getShadingType();
        this.primaryColor = ColorSingleton.getColor(shapeConfiguration.getPrimaryColor());
        this.secondaryColor = ColorSingleton.getColor(shapeConfiguration.getSecondaryColor());
        this.adjustedStart = shapeConfiguration.getAdjustedStart();
        this.adjustedEnd = shapeConfiguration.getAdjustedEnd();
        this.startPoint = shapeConfiguration.getStartPoint();
        this.x[0] = shapeConfiguration.getAdjustedStart().getX();
        this.x[1] = shapeConfiguration.getAdjustedEnd().getX();
        this.x[2] = shapeConfiguration.getAdjustedStart().getX();

        this.y[0] = shapeConfiguration.getAdjustedStart().getY();
        this.y[1] = shapeConfiguration.getAdjustedEnd().getY();
        this.y[2] = shapeConfiguration.getAdjustedEnd().getY();
    }


    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (shapeShadingType.equals(ShapeShadingType.OUTLINE)) {
            g.setColor(primaryColor);
            g2.setStroke(new BasicStroke(8));
            g.drawPolygon(x, y, 3);

        } else if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)) {
           // System.out.println("Draw Triangle Shape filled in is running.");
            g.setColor(secondaryColor);
            g.fillPolygon(x, y, 3);

        } else if (shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            g.setColor(primaryColor);
            g2.setStroke(new BasicStroke(8));
            g.drawPolygon(x, y, 3);
            g.setColor(secondaryColor);
            g.fillPolygon(x, y, 3);
        }
    }

    double area(int x1, int y1, int x2, int y2, int x3, int y3) {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }

    /* A function to check whether point P(x, y) lies inside the triangle  */
    boolean isInside(int x1, int y1, int x2, int y2, int x3, int y3, int x, int y) {

        double A = area(x1, y1, x2, y2, x3, y3);
        double A1 = area(x, y, x2, y2, x3, y3);
        double A2 = area(x1, y1, x, y, x3, y3);
        double A3 = area(x1, y1, x2, y2, x, y);

   /* Check if sum of A1, A2 and A3 is same as A */
        return (A == A1 + A2 + A3);
    }


    public boolean contains(Point startPoint) {
        if (isInside(x[0], y[0], x[1], y[1], x[2], y[2], startPoint.getX(), startPoint.getY())) {
            return true;
        } else {
            return false;
        }
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
        this.x[0] = adjustedStart.getX() + dx;
        this.x[1] = adjustedEnd.getX() + dx;
        this.x[2] = adjustedStart.getX() + dx;
    }

    @Override
    public void addY(int dy) {
        this.y[0] = adjustedStart.getY() + dy;
        this.y[1] = adjustedEnd.getY() + dy;
        this.y[2] = adjustedEnd.getY() + dy;
    }

    public ShapeConfiguration getShapeConfiguration() {
        return shapeConfiguration;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


