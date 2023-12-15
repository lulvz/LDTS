package view.game;

import gui.GUI;
import model.game.elements.Coin;

public class CoinViewer implements ElementViewer<Coin> {
    @Override
    public void draw(Coin coin, GUI gui) {
        gui.drawElement(coin.getPosition(), coin.getSymbol(), coin.getColor());
    }
}
