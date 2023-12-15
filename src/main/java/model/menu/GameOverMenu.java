package model.menu;

import java.util.Arrays;

public class GameOverMenu extends Menu {
    private final int levelentry;
    private final int enemiesRemaining;
    private final int totalPoints;
    public GameOverMenu(int level, int enemiesRemaining, int totalPoints) {
        this.entries = Arrays.asList("Retry", "Quit");
        this.levelentry = level;
        this.enemiesRemaining = enemiesRemaining;
        this.totalPoints = totalPoints;
    }
    public boolean isSelectedExit() { return isSelected(1); }

    public boolean isSelectedRetry() {return isSelected(0); }

    public int getLevel() { return this.levelentry; }

    public int getEnemiesRemaining() { return enemiesRemaining; }

    public int getTotalPoints() { return totalPoints; }
}
