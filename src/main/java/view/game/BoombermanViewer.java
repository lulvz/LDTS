package view.game;

import gui.GUI;
import model.game.elements.Boomberman;

public class BoombermanViewer implements ElementViewer<Boomberman> {
    @Override
    public void draw(Boomberman bomberman, GUI gui) {
        gui.drawElement(bomberman.getPosition(), bomberman.getSymbol(), bomberman.getColor());
    }
}
