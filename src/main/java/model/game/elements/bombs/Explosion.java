package model.game.elements.bombs;

import model.game.elements.Element;

public class Explosion extends Element {
    private final long startTime;

    public Explosion(int x, int y, long startTime) {
        super(x, y);
        this.startTime = startTime;
    }

    public char getSymbol() {
        return '*';
    }

    public String getColor() {
        return "#FF0000";
    }

    public long getStartTime() {
        return startTime;
    }
}
