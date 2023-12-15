package model.game.elements;

public class Coin extends Element {
    public Coin(int x, int y) {
        super(x, y);
    }

    public char getSymbol() {
        return 'c';
    }

    public String getColor() {
        return "#FFFF00";
    }
}
