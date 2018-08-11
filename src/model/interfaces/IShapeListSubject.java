package model.interfaces;

import view.interfaces.IDrawShape;

import java.util.ArrayList;

public interface IShapeListSubject {
    void add(IDrawShape shape);

    void remove(IDrawShape shape);

    ArrayList<IDrawShape> getShapeList();

    void register(IShapeListObserver paintCanvas);

    void unsubscribe(IShapeListObserver paintCanvas);

    void notifyObserver();

    void addSelectedList(IDrawShape shapes);

    ArrayList<IDrawShape> getSelectedShapesList();

    void removeSelectedList();

    void clearSelectedShapeList();

    void addClipBoardShapes(IDrawShape shapes);

    void removeClipBoardShapes();

    void clearClipBoard();

    ArrayList<IDrawShape> getClipBoard();
}
