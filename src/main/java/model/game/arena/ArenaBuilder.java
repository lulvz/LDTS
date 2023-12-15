package model.game.arena;

import game.Game;
import model.game.elements.Boomberman;
import model.game.elements.Coin;
import model.game.elements.Enemy;
import model.game.elements.PowerUps.PowerUp;
import model.game.elements.Wall;
import model.game.elements.bombs.Bomb;
import model.game.elements.bombs.Explosion;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public abstract class ArenaBuilder {
    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight(), getLevel(), Game.getHighscore(getLevel()));

        arena.setBoomberman(createBoomberman());
        arena.setEnemies(createEnemies());
        arena.setWalls(createWalls());
        arena.setCoins(createCoins());
        arena.setPowerups(createPowerups());
        arena.setBombs(createBombs());
        arena.setExplosions(createExplosions());

        return arena;
    }

    public static int getFilesAmount(String path) {
        File folder = new File(Objects.requireNonNull(ArenaBuilder.class.getClassLoader().getResource(path)).getFile());
        if (folder.listFiles() == null) return 0;
        else return folder.listFiles().length;
    }

    public abstract int getLevel();

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract ArrayList<Wall> createWalls();
    protected abstract ArrayList<Coin> createCoins();
    protected abstract ArrayList<Enemy> createEnemies();
    protected abstract Boomberman createBoomberman();
    protected abstract ArrayList<PowerUp> createPowerups();
    protected abstract ArrayList<Bomb> createBombs();
    protected abstract ArrayList<Explosion> createExplosions();
}
