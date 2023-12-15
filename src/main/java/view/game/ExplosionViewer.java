package view.game;

import gui.GUI;
import model.game.elements.bombs.Explosion;

public class ExplosionViewer implements ElementViewer<Explosion> {
    @Override
    public void draw(Explosion explosion, GUI gui) {
        gui.drawElement(explosion.getPosition(), explosion.getSymbol(), explosion.getColor());
    }
}
