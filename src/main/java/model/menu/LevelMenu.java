package model.menu;

import java.util.Arrays;
import java.util.List;

public class LevelMenu extends Menu {
    public LevelMenu(int amountOfLevels) {
        String[] entries = new String[amountOfLevels + 1];
        entries[0] = "Back";
        for (int i = 1; i <= amountOfLevels; i++) {
            entries[i] = "Level " + (i);
        }
        this.entries = Arrays.asList(entries);
    }

    // the levels are loaded into index 1, 2, 3, etc...
    // the exit button is the index 0 of the array
    public int getSelectedLevel() {
        return this.currentEntry;
    }

    public boolean isSelectedExit() {
        return isSelected(0);
    }

    public boolean isSelectedLevel() {
        if (this.currentEntry == 0) return false;
        else return this.entries.size() != 1;
    }
}
