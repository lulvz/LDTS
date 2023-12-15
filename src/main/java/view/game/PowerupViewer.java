package view.game;

import gui.GUI;
import model.game.elements.PowerUps.PowerUp;

public class PowerupViewer implements ElementViewer<PowerUp> {
    @Override
    public void draw(PowerUp powerUp, GUI gui) {
        gui.drawElement(powerUp.getPosition(), powerUp.getSymbol(), powerUp.getColor());
    }
}
