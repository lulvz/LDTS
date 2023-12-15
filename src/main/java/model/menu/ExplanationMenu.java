package model.menu;

import java.util.Arrays;

public class ExplanationMenu extends Menu{
    public ExplanationMenu ( ){
            this.entries = Arrays.asList("Back");
    }
    public boolean isSelectedExit() {
        return isSelected(0);
    }
}
