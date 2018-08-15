package main;

import controller.IJPaintController;
import controller.JPaintController;
import view.gui.MouseObserver;
import model.ShapeConfiguration;
import model.ShapeList;
import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.gui.*;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        ShapeList shapeList = new ShapeList();
        ShapeConfiguration shapeConfiguration = new ShapeConfiguration();
        PaintCanvas paintCanvas = new PaintCanvas(shapeList);
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        IApplicationState applicationState = new ApplicationState(uiModule);

        //  paintCanvas.addMouseListener(new DrawClickHandler(applicationState, shapeList, shapeConfiguration));
        paintCanvas.setCursor((new Cursor(Cursor.CROSSHAIR_CURSOR)));

        IJPaintController controller = new JPaintController(uiModule, applicationState, shapeList, shapeConfiguration);
        MouseObserver mouseObserver = new MouseObserver(applicationState, paintCanvas, shapeList, shapeConfiguration);
        mouseObserver.run();
        controller.setup();
    }
}
