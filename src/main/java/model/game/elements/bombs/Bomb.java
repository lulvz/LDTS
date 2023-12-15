package model.game.elements.bombs;

import model.Position;
import model.game.elements.Element;

import java.util.ArrayList;

public abstract class Bomb extends Element {
    private int range;
    private int timer;

    public Bomb(int x, int y, int range) {
        super(x, y);
        this.range = range;
        this.timer = 1500;
    }

    public char getSymbol() {
        return 'O';
    }

    public int getRange() {
        return range;
    }

    public void setRange (int range) {
        this.range = range;
    }

    public int getTimer() {
        return timer;
    }

    public void subtractTimer(long n) {
        this.timer -= n;
    }
}
