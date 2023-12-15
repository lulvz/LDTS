package view.menu;

import gui.GUI;
import model.Position;
import model.menu.ExplanationMenu;
import view.Viewer;

public class ExplanationMenuViewer extends Viewer<ExplanationMenu> {
    public ExplanationMenuViewer(ExplanationMenu model) {
        super(model);
    }
    @Override
    protected void drawElements(GUI gui) {
        String s1 = "      ___                    ___      ";
        String s2 = "     | ^ |                  | W |     ";
        String s3 = "     |_|_|                  |___|     ";
        String s4 = " ___  ___  ___    OR    ___  ___  ___ ";
        String s5 = "| <-|| | ||-> |        | A || S || D |";
        String s6 = "|___||_v_||___|        |___||___||___|";

        gui.drawText(new Position(10, 1), "Movements:", "#FFFFFF");
        gui.drawText(new Position(10, 2), s1, "#FFFFFF");
        gui.drawText(new Position(10, 3), s2, "#FFFFFF");
        gui.drawText(new Position(10, 4), s3, "#FFFFFF");
        gui.drawText(new Position(10, 5), s4, "#FFFFFF");
        gui.drawText(new Position(10, 6), s5, "#FFFFFF");
        gui.drawText(new Position(10, 7), s6, "#FFFFFF");

        gui.drawText(new Position(10, 9), "Bomb drop:  Space-bar", "#FFFFFF");

        gui.drawText(new Position(10, 11), "Bombs:", "#FFFFFF");
        gui.drawText(new Position(10, 12), "Green - Bomb with range 1", "#FFFFFF");
        gui.drawText(new Position(10, 13), "Yellow - Bomb with range 2:", "#FFFFFF");
        gui.drawText(new Position(10, 14), "Red - Bomb with range 3", "#FFFFFF");

        gui.drawText(new Position(10, 16), "Walls:", "#FFFFFF");
        gui.drawText(new Position(10, 17), "# - Indestructible wall", "#FFFFFF");
        gui.drawText(new Position(10, 18), "= - destructible wall", "#FFFFFF");

        gui.drawText(new Position(10, 20), "PowerUps Explained:", "#FFFFFF");
        gui.drawText(new Position(10, 21), "Red - Increase Player HP", "#FFFFFF");
        gui.drawText(new Position(10, 22), "Green - Increase number of Bombs", "#FFFFFF");
        gui.drawText(new Position(10, 23), "Blue - Bomb with bigger Range", "#FFFFFF");

    }
}
