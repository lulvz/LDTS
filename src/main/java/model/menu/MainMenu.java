package model.menu;

import java.util.Arrays;

public class MainMenu extends Menu {

    public MainMenu() {
        this.entries = Arrays.asList("Play", "Explanation", "Shop", "Quit");
    }

    public boolean isSelectedStart() {
        return isSelected(0);
    }

    public boolean isSelectedExplanation() {return isSelected(1); }
    public boolean isSelectedShop() { return isSelected(2); }

    public boolean isSelectedExit() {
        return isSelected(3);
    }

}
