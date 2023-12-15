package model.game.elements.PowerUps;

import model.game.elements.Element;

public class PowerUp extends Element {
    pUps type;

    // Constructor that makes random power up
    public PowerUp(int x, int y) {
        super(x, y);
        int n = (int) (Math.random() * 3);
        switch (n) {
            case 0:
                type = pUps.HP_UP;
                break;
            case 1:
                type = pUps.EXTRA_BOMBS;
                break;
            case 2:
                type = pUps.BIGGER_BOMBS;
                break;
        }
    }

    // constructor that takes in type of power up
    public PowerUp(int x, int y, pUps powerUp) {
        super(x, y);
        this.type = powerUp;
    }

    public pUps getType() {
        return type;
    }

    public char getSymbol() {
        return 'P';
    }

    public String getColor() {
        if (this.type == pUps.HP_UP) {
            return "#FF0000";
        } else if (this.type == pUps.EXTRA_BOMBS) {
            return "#00FF00";
        } else {
            return "#0000FF";
        }
    }
}
