package controller;

import interfaces.IUndoable;
import model.interfaces.IShapeListSubject;
import view.interfaces.IDrawShape;

import java.util.ArrayList;

public class ClearAllCommand implements ICommand, IUndoable{
        private IShapeListSubject shapeList;
        private final ArrayList<IDrawShape> shapes = new ArrayList<IDrawShape>();

    public ClearAllCommand(IShapeListSubject shapeList){
        this.shapeList = shapeList;
    }
    @Override
    public void run() {
        for(IDrawShape shape : shapeList.getShapeList()){
            shapes.add(shape);
        }
        shapeList.clearSelectedShapeList();
        shapeList.getClipBoard().clear();
        shapeList.getShapeList().clear();
        shapeList.notifyObserver();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for(IDrawShape shape : shapeList.getShapeList()){
            shapeList.add(shape);
            shapeList.notifyObserver();
        }
    }

    @Override
    public void redo() {
        shapeList.clearSelectedShapeList();
        shapeList.getClipBoard().clear();
        shapeList.getShapeList().clear();
    }
}
