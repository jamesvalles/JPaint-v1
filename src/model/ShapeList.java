package model;

import model.interfaces.IShapeListObserver;
import model.interfaces.IShapeListSubject;
import view.interfaces.IDrawShape;
import view.interfaces.IMouseAdapterObserver;

import java.util.ArrayList;

public class ShapeList implements IShapeListSubject {
    private final ArrayList<IDrawShape> internalShapesList;
    private final ArrayList<IShapeListObserver> observers;
    private final ArrayList<IDrawShape> selectedShapesList;
    private final ArrayList<IDrawShape> clipBoard;


    public ShapeList() {
        internalShapesList = new ArrayList<IDrawShape>();
        observers = new ArrayList<IShapeListObserver>();
        selectedShapesList = new ArrayList<IDrawShape>();
        clipBoard = new ArrayList<IDrawShape>();

    }

    public void add(IDrawShape shapes) {
        //  System.out.println("ShapeList adding");
        internalShapesList.add(shapes);
        notifyObserver();
    }

    public void remove(IDrawShape shape) {
        //  System.out.println("ShapeList adding");
        internalShapesList.remove(shape);
        notifyObserver();
    }

    public ArrayList<IDrawShape> getShapeList() {
        return internalShapesList;
    }

    @Override
    public void register(IShapeListObserver observer) {
        observers.add(observer);
    }


    @Override
    public void unsubscribe(IShapeListObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        // System.out.println("ShapeList notifying " + observers.size() + " observers.");


        for (IShapeListObserver newObserver : observers) {
            newObserver.update();
        }
    }

    //Selected shape list

    public void addSelectedList(IDrawShape shapes) {
        selectedShapesList.add(shapes);

    }

    public void removeSelectedList() {
        selectedShapesList.removeAll(selectedShapesList);
        notifyObserver();

    }

    public void clearSelectedShapeList() {
        selectedShapesList.clear();
    }

    public ArrayList<IDrawShape> getSelectedShapesList() {
        return selectedShapesList;
    }

    //Clipboard Shapelist

    public void addClipBoardShapes(IDrawShape shapes) {
        clipBoard.add(shapes);

    }

    public void removeClipBoardShapes() {
        clipBoard.removeAll(selectedShapesList);
        notifyObserver();

    }

    public void clearClipBoard() {
        clipBoard.clear();
    }

    public ArrayList<IDrawShape> getClipBoard() {
        return clipBoard;
    }

}
