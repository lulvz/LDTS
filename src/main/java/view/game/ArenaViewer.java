package view.game;

import gui.GUI;
import model.Position;
import model.game.arena.Arena;
import model.game.elements.Element;
import view.Viewer;

import java.util.List;

public class ArenaViewer extends Viewer<Arena> {
    public ArenaViewer(Arena arena) {
        super(arena);
    }

    @Override
    public void drawElements(GUI gui) {
        new BoombermanViewer().draw(getModel().getBoomberman(), gui);
        if (getModel().getEnemies() != null)
            drawListElements(gui, getModel().getEnemies(), new EnemyViewer());
        if (getModel().getBombs() != null)
            drawListElements(gui, getModel().getBombs(), new BombViewer());
        if (getModel().getWalls() != null)
            drawListElements(gui, getModel().getWalls(), new WallViewer());
        if (getModel().getPowerups() != null)
            drawListElements(gui, getModel().getPowerups(), new PowerupViewer());
        if (getModel().getCoins() != null)
            drawListElements(gui, getModel().getCoins(), new CoinViewer());
        if (getModel().getExplosions() != null)
            drawListElements(gui, getModel().getExplosions(), new ExplosionViewer());

        gui.drawText(new Position(0, 0), "Health: " + getModel().getBoomberman().getHealth(), "#FFD700");
        // size of the health bar
        int healthBarSize = "Health: ".length() + String.valueOf(getModel().getBoomberman().getHealth()).length();
        gui.drawText(new Position(healthBarSize+1, 0), "Score: " + getModel().getBoomberman().getScore(), "#FFD700");
        // size of the score bar
        int scoreBarSize = "Score: ".length() + String.valueOf(getModel().getBoomberman().getScore()).length();
        // draw the bombs the boomberman has
        for (int i = 0; i < getModel().getBoomberman().getBombs().size(); i++) {
            gui.drawElement(new Position(healthBarSize + scoreBarSize + 2 + i, -1), getModel().getBoomberman().getBombs().get(i).getSymbol(), getModel().getBoomberman().getBombs().get(i).getColor());
        }
        int bombsBarSize = getModel().getBoomberman().getBombs().size();
        // draw the highscore of the level
        gui.drawText(new Position(healthBarSize + scoreBarSize + bombsBarSize + 3, 0), "Highscore: " + getModel().getHighScore(), "#FFD700");
    }

    private <T extends Element> void drawListElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements){
            viewer.draw(element, gui);
        }
    }
}
