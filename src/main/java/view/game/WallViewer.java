package view.game;

import gui.GUI;
import model.game.elements.Wall;

public class WallViewer implements ElementViewer<Wall> {
    @Override
    public void draw(Wall wall, GUI gui) {
        gui.drawElement(wall.getPosition(), wall.getSymbol(), wall.getColor());
    }
}
