package view.menu;

import gui.GUI;
import model.Position;
import model.menu.ShopMenu;
import view.Viewer;

public class ShopViewer extends Viewer<ShopMenu> {
    public ShopViewer(ShopMenu model) {
        super(model);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(5, 5), "Shop", "#FF00FF");

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            gui.drawText(
                    new Position(5, 7 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FF0000");
        }
    }
}
