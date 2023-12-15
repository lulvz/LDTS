package view.game;

import gui.GUI;
import model.game.elements.Enemy;

public class EnemyViewer implements ElementViewer<Enemy> {
    @Override
    public void draw(Enemy enemy, GUI gui) {
        gui.drawElement(enemy.getPosition(), enemy.getSymbol(), enemy.getColor());
    }
}
