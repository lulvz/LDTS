package controller.game;

import game.Game;
import gui.GUI;
import model.game.arena.Arena;
import model.game.elements.bombs.Explosion;

import java.io.IOException;
import java.util.ArrayList;

public class ExplosionController extends GameController {
    public ExplosionController(Arena model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        ArrayList<Explosion> explosions = getModel().getExplosions();
        explosions.removeIf(explosion -> time - explosion.getStartTime() > 300);
    }
}
