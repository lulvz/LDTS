package model.game.arena;

import model.Position;

import java.util.ArrayList;

import model.game.elements.Boomberman;
import model.game.elements.Enemy;
import model.game.elements.PowerUps.pUps;
import model.game.elements.Wall;
import model.game.elements.bombs.*;
import model.game.elements.Coin;
import model.game.elements.PowerUps.PowerUp;

public class Arena {
    private final int width;
    private final int height;
    private ArrayList<Coin> coins;
    private ArrayList<Wall> walls;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bomb> bombs;
    private ArrayList<PowerUp> powerups;
    private ArrayList<Explosion> explosions;
    private Boomberman player;


    public final int level;
    public final int highScore;

    public Arena(int width, int height, int level, int highScore) {
        this.width = width;
        this.height = height;
        this.level = level;
        this.highScore = highScore;
    }

    public int getLevel() {
        return level;
    }

    public int getHighScore() {
        return highScore;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }
    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }
    public ArrayList<Wall> getWalls() {
        return walls;
    }
    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
    public ArrayList<Bomb> getBombs() {
        return bombs;
    }
    public void setBombs(ArrayList<Bomb> bombs) {
        this.bombs = bombs;
    }
    public ArrayList<PowerUp> getPowerups() {
        return powerups;
    }
    public void setPowerups(ArrayList<PowerUp> powerups) {
        this.powerups = powerups;
    }
    public ArrayList<Explosion> getExplosions() {
        return explosions;
    }
    public void setExplosions(ArrayList<Explosion> explosions) {
        this.explosions = explosions;
    }
    public Boomberman getBoomberman() {
        return player;
    }
    public void setBoomberman(Boomberman player) {
        this.player = player;
    }

    public boolean isWall(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return true;
        return false;
    }

    public boolean isDestroyableWall (Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position) && wall.isDestroyable())
                return true;
        return false;
    }

    public boolean isEnemy(Position position) {
        for (Enemy enemy : enemies)
            if (enemy.getPosition().equals(position))
                return true;
        return false;
    }

    public boolean isCoin(Position position) {
        for(Coin coin: coins) {
            if(coin.getPosition().equals(position))
                return true;
        }
        return false;
    }
    public boolean isPowerUp(Position position) {
        for (PowerUp powerUp : powerups) {
            if (powerUp.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean isBomb(Position position) {
        for (Bomb bomb : bombs) {
            if (bomb.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public void removeCoin(Position position) {
        for(int i = 0; i<coins.size(); i++) {
            if(coins.get(i).getPosition().equals(position)) {
                coins.remove(i);
                player.increaseScore(1);
                break;
            }
        }
    }

    public void removePowerUp(Position position) {
        for (int i = 0; i < powerups.size(); i++) {
            if (powerups.get(i).getPosition().equals(position)) {
                System.out.println("Picked up powerup" + getPowerups().get(i).getType());
                // extra hp
                if (getPowerups().get(i).getType() == pUps.HP_UP) {
                    getBoomberman().increasehealth();
                }
                // extra bombs
                else if (getPowerups().get(i).getType() == pUps.EXTRA_BOMBS) {
                    // make the max bombs 5
                    getBoomberman().setMaxBombs(5);
                    // give 5 huge bombs to the player
                    ArrayList<Bomb> bombs = new ArrayList<>();
                    for (int j = 0; j < 5; j++) {
                        bombs.add(new HugeBomb(0,0));
                    }
                    getBoomberman().setBombs(bombs);
                }
                // bigger bombs
                else if (getPowerups().get(i).getType() == pUps.BIGGER_BOMBS) {
                    // go through each bomb the player has and give it 10 range
                    for (int j = 0; j < getBoomberman().getBombs().size(); j++) {
                        getBoomberman().getBombs().get(j).setRange(10);
                    }
                }
                powerups.remove(i);
                break;
            }
        }
    }

    public void removeEnemy(Position position) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getPosition().equals(position)) {
                enemies.remove(i);
                break;
            }
        }
    }

    public void removeWall(Position position) {
        for (int i = 0; i < walls.size(); i++) {
            if (walls.get(i).getPosition().equals(position)) {
                walls.remove(i);
                break;
            }
        }
    }

    public Bomb getRandomBomb() {
        int n = (int) (Math.random() * 3);
        switch (n) {
            case 0:
                return new BigBomb(0, 0);
            case 1:
                return new HugeBomb(0, 0);
            default:
                return new SmallBomb(0, 0);
        }
    }
}
