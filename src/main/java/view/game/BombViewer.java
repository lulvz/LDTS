package view.game;

import gui.GUI;
import model.game.elements.bombs.Bomb;

public class BombViewer implements ElementViewer<Bomb> {
    @Override
    public void draw(Bomb bomb, GUI gui) {
        gui.drawElement(bomb.getPosition(), bomb.getSymbol(), bomb.getColor());
    }
}
