package view.game;

import gui.GUI;
import model.game.elements.Element;

// Extends the Viewer class to draw the game elements
public interface ElementViewer<T extends Element> {
    void draw(T element, GUI gui);
}
