package controller.game;

import controller.Controller;
import model.game.arena.Arena;

public abstract class GameController extends Controller<Arena> {
    GameController(Arena model) { super(model); }
}
