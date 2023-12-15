package model.game.elements.bombs;

public class SmallBomb extends Bomb {
    public SmallBomb(int x, int y) {
        super(x, y, 1);
    }

    public String getColor() {
        return "#00FF2F";
    }
}
