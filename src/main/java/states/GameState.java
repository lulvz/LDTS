package states;

import controller.Controller;
import controller.game.ArenaController;
import model.game.arena.Arena;
import view.Viewer;
import view.game.ArenaViewer;

public class GameState extends State<Arena> {
    public GameState(Arena arena) {
        super(arena);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new ArenaViewer(getModel());
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }
}
