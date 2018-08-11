package view.interfaces;

import model.ShapeShadingType;
import model.ShapeType;

public interface IShadedShape {
    void drawShadedShape();

    ShapeShadingType shapeShadingType();

    ShapeType getShapeType();

}
