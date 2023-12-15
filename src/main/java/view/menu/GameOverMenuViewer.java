package view.menu;

import gui.GUI;
import model.Position;
import model.menu.GameOverMenu;
import view.Viewer;

public class GameOverMenuViewer extends Viewer<GameOverMenu> {
    public GameOverMenuViewer(GameOverMenu model) { super(model); }

    @Override
    protected void drawElements(GUI gui) {
        gui.drawText(new Position(10, 10), "Game Over!", "#FF00FF");
        if (getModel().getEnemiesRemaining() == 0) {
            gui.drawText(new Position(10, 12), "You killed all the enemies!", "#00C04B");
        }
        else gui.drawText(new Position(10, 12), String.format("Enemies remaining: %d", getModel().getEnemiesRemaining()), "#FFFFFF");

        gui.drawText(new Position(10, 13), String.format("Score achieved: %d", getModel().getTotalPoints()), "#FFFFFF");

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            gui.drawText(
                    new Position(10, 15 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FF0000");
        }
    }
}
