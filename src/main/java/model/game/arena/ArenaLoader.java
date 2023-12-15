package model.game.arena;

import model.game.elements.Boomberman;
import model.game.elements.Coin;
import model.game.elements.Enemy;
import model.game.elements.PowerUps.PowerUp;
import model.game.elements.Wall;
import model.game.elements.bombs.Bomb;
import model.game.elements.bombs.Explosion;
import model.game.elements.bombs.HugeBomb;
import strategy.StrategyRandom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ArenaLoader extends ArenaBuilder {
    private final int level;
    private final ArrayList<String> line_elements;

    public ArenaLoader(int i) throws IOException {
        this.level = i;
        URL resource = ArenaBuilder.class.getResource("/levels/level" + level + ".lvl");
        BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));

        line_elements = readLines(reader);
    }

    private ArrayList<String> readLines(BufferedReader br) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    public int getLevel() {
        return level;
    }

    @Override
    protected int getWidth() {
        int width = 0;
        for (String s: line_elements) {
            if (s.length() > width) {
                width = s.length();
            }
        }
        return width;
    }

    @Override
    protected int getHeight() {
        return line_elements.size();
    }

    @Override
    protected ArrayList<Wall> createWalls() {
        ArrayList<Wall> walls = new ArrayList<>();
        for (int y = 0; y < getHeight(); y++) {
            String line = line_elements.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '=')
                    walls.add(new Wall(x, y, 2, true));
                if (line.charAt(x) == '#')
                    walls.add(new Wall(x, y, -1, false));
            }
        }
        return walls;
    }

    @Override
    protected ArrayList<Coin> createCoins() {
        ArrayList<Coin> coins = new ArrayList<>();
        for (int y = 0; y < getHeight(); y++) {
            String line = line_elements.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'c')
                    coins.add(new Coin(x, y));
            }
        }
        return coins;
    }

    @Override
    protected ArrayList<Enemy> createEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (int y = 0; y < getHeight(); y++) {
            String line = line_elements.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'E')
                    enemies.add(new Enemy(x, y, new StrategyRandom()));
            }
        }
        return enemies;
    }

    @Override
    protected ArrayList<PowerUp> createPowerups() {
        ArrayList<PowerUp> powerups = new ArrayList<>();
        for (int y = 0; y < getHeight(); y++) {
            String line = line_elements.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'P')
                    powerups.add(new PowerUp(x, y));
            }
        }
        return powerups;
    }

    @Override
    protected ArrayList<Bomb> createBombs() {
        ArrayList<Bomb> bombs = new ArrayList<>();
        for (int y = 0; y < getHeight(); y++) {
            String line = line_elements.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'b')
                    // add huge bomb
                    bombs.add(new HugeBomb(x, y));
            }
        }
        return bombs;
    }

    @Override
    protected Boomberman createBoomberman() {
        for (int y = 0; y < getHeight(); y++) {
            String line = line_elements.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'B')
                    return new Boomberman(x, y, 1, 3);
            }
        }
        return null;
    }

    @Override
    protected ArrayList<Explosion> createExplosions() {
        ArrayList<Explosion> explosions = new ArrayList<>();
        for (int y = 0; y < getHeight(); y++) {
            String line = line_elements.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '*')
                    explosions.add(new Explosion(x, y, 400));
            }
        }
        return explosions;
    }
}
