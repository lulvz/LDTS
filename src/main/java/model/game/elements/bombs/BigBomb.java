package model.game.elements.bombs;

public class BigBomb extends Bomb {
    public BigBomb(int x, int y) {
        super(x, y, 2);
    }

    public String getColor() {
        return "#FFFF00";
    }
}
