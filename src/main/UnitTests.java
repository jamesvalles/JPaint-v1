package main;

import controller.Point;
import model.*;
import model.dialogs.ColorSingleton;
import org.junit.Assert;
import org.junit.Test;
import view.interfaces.IDrawShape;

import java.awt.*;

public class UnitTests {

    @Test
    public void TestShapeConfiguration() {

        ShapeType rectangle = ShapeType.RECTANGLE;
        ShapeShadingType outline = ShapeShadingType.OUTLINE;
        Point startPoint = new Point(300, 200);
        Point endPoint = new Point(300, 200);


        ShapeConfiguration newConfiguration = new ShapeConfiguration();
        newConfiguration.setShapeType(rectangle);
        newConfiguration.setShadingType(outline);
        newConfiguration.setStartPoint(startPoint);
        newConfiguration.setEndPoint(endPoint);

        Assert.assertEquals(rectangle, newConfiguration.getShapeType());
        Assert.assertEquals(outline, newConfiguration.getShadingType());
        Assert.assertEquals(startPoint, newConfiguration.getStartPoint());
        Assert.assertEquals(endPoint, newConfiguration.getEndPoint());
    }

    @Test
    public void TestColorAdapter() {

        ShapeColor red = ShapeColor.RED;
        ShapeColor blue = ShapeColor.BLUE;
        Color primaryColor = Color.RED;
        Color secondaryColor = Color.BLUE;


        ShapeConfiguration newConfiguration = new ShapeConfiguration();
        newConfiguration.setPrimaryColor(red);
        newConfiguration.setSecondaryColor(blue);


        Assert.assertEquals(primaryColor, ColorSingleton.getColor(newConfiguration.getPrimaryColor()));
        Assert.assertEquals(secondaryColor, ColorSingleton.getColor(newConfiguration.getSecondaryColor()));
    }

    @Test
    public void TestShapeFactory() {
        ShapeType rectangle = ShapeType.RECTANGLE;
        ShapeShadingType outline = ShapeShadingType.OUTLINE;
        Point startPoint = new Point(300, 200);
        Point endPoint = new Point(300, 200);
        ShapeColor red = ShapeColor.RED;
        ShapeColor blue = ShapeColor.BLUE;
        Color primaryColor = Color.RED;
        Color secondaryColor = Color.BLUE;

        ShapeConfiguration shapeConfiguration = new ShapeConfiguration();
        ShapeFactory shape = new ShapeFactory();

        shapeConfiguration.setShapeType(rectangle);
        shapeConfiguration.setShadingType(outline);
        shapeConfiguration.setStartPoint(startPoint);
        shapeConfiguration.setEndPoint(endPoint);
        shapeConfiguration.setPrimaryColor(red);
        shapeConfiguration.setSecondaryColor(blue);


        IDrawShape testshape = shape.createShape(shapeConfiguration);


        Assert.assertEquals(rectangle, testshape.getShapeConfiguration().getShapeType());
        Assert.assertEquals(outline, testshape.getShapeConfiguration().getShadingType());
        Assert.assertEquals(startPoint, testshape.getShapeConfiguration().getStartPoint());
        Assert.assertEquals(endPoint, testshape.getShapeConfiguration().getEndPoint());
        Assert.assertEquals(primaryColor, ColorSingleton.getColor(testshape.getShapeConfiguration().getPrimaryColor()));
        Assert.assertEquals(secondaryColor, ColorSingleton.getColor(testshape.getShapeConfiguration().getSecondaryColor()));

    }

    @Test
    public void TestShapeList() {
        ShapeType rectangle = ShapeType.RECTANGLE;
        ShapeShadingType outline = ShapeShadingType.OUTLINE;
        Point startPoint = new Point(300, 200);
        Point endPoint = new Point(300, 200);
        ShapeColor red = ShapeColor.RED;
        ShapeColor blue = ShapeColor.BLUE;
        Color primaryColor = Color.RED;
        Color secondaryColor = Color.BLUE;

        ShapeConfiguration shapeConfiguration = new ShapeConfiguration();
        ShapeFactory shape = new ShapeFactory();

        shapeConfiguration.setShapeType(rectangle);
        shapeConfiguration.setShadingType(outline);
        shapeConfiguration.setStartPoint(startPoint);
        shapeConfiguration.setEndPoint(endPoint);
        shapeConfiguration.setPrimaryColor(red);
        shapeConfiguration.setSecondaryColor(blue);

        ShapeList shapeList = new ShapeList();
        IDrawShape testshape = shape.createShape(shapeConfiguration);
        shapeList.add(testshape);

        Assert.assertEquals(1, shapeList.getShapeList().size());


    }
}
