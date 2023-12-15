package view.menu;

import gui.GUI;
import model.Position;
import model.menu.LevelMenu;
import view.Viewer;

public class LevelMenuViewer extends Viewer<LevelMenu> {
    public LevelMenuViewer(LevelMenu model) {
        super(model);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(5, 5), "Level Menu", "#FF00FF");

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            gui.drawText(
                    new Position(5, 7 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FF0000");
        }
    }
}
