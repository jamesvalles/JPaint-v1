package model;

import javafx.scene.shape.Ellipse;
import view.gui.DrawEllipseShape;
import view.gui.DrawRectangleShape;
import view.gui.DrawTriangleShape;
import view.interfaces.IDrawShape;

public class ShapeFactory {

    public IDrawShape createShape(ShapeConfiguration shapeConfiguration) {
        ShapeType shapeType = shapeConfiguration.getShapeType();
        IDrawShape shape = null;

        if (shapeType.equals(ShapeType.RECTANGLE)) {
            System.out.println("ShapeCreateCommand executed. Rectangle drawn.");
            shape = new DrawRectangleShape(shapeConfiguration);
        } else if (shapeType.equals(ShapeType.ELLIPSE)) {
            System.out.println("ShapeCreateCommand executed. Ellipse drawn.");
            shape = new DrawEllipseShape(shapeConfiguration);
        } else if (shapeType.equals(ShapeType.TRIANGLE)) {
            System.out.println("ShapeCreateCommand executed. Triangle drawn.");
            shape = new DrawTriangleShape(shapeConfiguration);
        } else {
            // System.out.println("this is NULLLLLLLL");
        }
        return shape;
    }
}
