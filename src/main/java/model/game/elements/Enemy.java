package model.game.elements;

import strategy.Strategy;
import model.Position;

// each enemy has a different strategy, this is implemented using the strategy design pattern
public class Enemy extends Element {
    private Strategy strategy;
    public Enemy(int x, int y, Strategy strategy) {
        super(x, y);
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }

    public char getSymbol() {
        return 'E';
    }

    public String getColor() {
        return "#9700B5";
    }

    public Position getNextPosition(Position target) {
        return strategy.getNextPosition(getPosition(), target);
    }

}
