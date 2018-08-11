package view.gui;

import model.ShapeConfiguration;
import model.StartAndEndPointMode;
import model.interfaces.IApplicationState;
import model.interfaces.IShapeListSubject;
import view.interfaces.IMouseAdapterObserver;
import javax.swing.*;
import java.awt.event.MouseListener;

public class MouseObserver extends JFrame implements IMouseAdapterObserver {
    private IApplicationState applicationState;
    private PaintCanvas paintCanvas;
    private IShapeListSubject shapeList;
    private ShapeConfiguration shapeConfiguration;

    public MouseObserver(IApplicationState applicationState, PaintCanvas paintCanvas, IShapeListSubject shapeList, ShapeConfiguration shapeConfiguration) {
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
        this.shapeConfiguration = shapeConfiguration;
        applicationState.registerObserver(this);
    }


    public void run() {

        MouseListener[] listeners = paintCanvas.getMouseListeners();
        for (MouseListener mouseListener : listeners) {
            paintCanvas.removeMouseListener(mouseListener);

        }
        StartAndEndPointMode startAndEndPointMode = applicationState.getActiveStartAndEndPointMode();

        if (startAndEndPointMode.equals(StartAndEndPointMode.DRAW)) {
            //  System.out.println("Draw");
            paintCanvas.addMouseListener(new MouseDrawAdapter(applicationState, shapeList, shapeConfiguration));

        } else if (startAndEndPointMode.equals(StartAndEndPointMode.SELECT)) {
            // System.out.println("Select");
            paintCanvas.addMouseListener(new MouseSelectAdapter(applicationState, shapeList, shapeConfiguration));
        } else if (startAndEndPointMode.equals(StartAndEndPointMode.MOVE)) {
            //  System.out.println("Move");
            paintCanvas.addMouseListener(new MouseMoveAdapter(applicationState, shapeList, shapeConfiguration));
        } else {
        }
    }
}
