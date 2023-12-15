package model.menu;

import java.util.Arrays;

public class ShopMenu extends Menu {
    public ShopMenu (int amountOfSkins) {
        String[] entries = new String[amountOfSkins + 1];
        entries[0] = "Back";
        for (int i = 1; i <= amountOfSkins; i++) {
            entries[i] = "Buy skin " + (i);
        }
        this.entries = Arrays.asList(entries);
    }

    // the skins are loaded into index 1, 2, 3, etc...
    // the exit button is the index 0 of the array
    public int getSelectedSkin() {
        return this.currentEntry;
    }

    public boolean isSelectedExit() {
        return isSelected(0);
    }

    public boolean isSelectedSkin() {
        if (this.currentEntry == 0) return false;
        else return this.entries.size() != 1;
    }
}