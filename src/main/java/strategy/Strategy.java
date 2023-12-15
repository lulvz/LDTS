package strategy;

import model.Position;
import model.game.elements.Element;

public interface Strategy {
    Position getNextPosition(Position me, Position target);
}
