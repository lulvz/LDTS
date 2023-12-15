package model.game.elements.bombs;

public class HugeBomb extends Bomb {
    public HugeBomb(int x, int y) {
        super(x, y, 3);
    }

    public String getColor() {
        return "#FF001E";
    }
}
