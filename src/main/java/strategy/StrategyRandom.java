package strategy;

import model.Position;

public class StrategyRandom implements Strategy {
    @Override
    public Position getNextPosition(Position me, Position target) {
        return me.getRandomNeighbour();
    }
}
