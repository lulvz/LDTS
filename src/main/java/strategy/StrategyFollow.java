package strategy;

import model.Position;

public class StrategyFollow implements Strategy {

    @Override
    public Position getNextPosition(Position me, Position target) {
        int dx = target.getX() - me.getX();
        int dy = target.getY() - me.getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) return new Position(me.getX() + 1, me.getY());
            else return new Position(me.getX() - 1, me.getY());
        }
        else {
            if (dy > 0) return new Position(me.getX(), me.getY() + 1);
            else return new Position(me.getX(), me.getY() - 1);
        }
    }
}
