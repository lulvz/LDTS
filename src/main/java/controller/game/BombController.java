package controller.game;

import game.Game;
import gui.GUI;
import model.Position;
import model.game.arena.Arena;
import model.game.elements.PowerUps.PowerUp;
import model.game.elements.bombs.Bomb;
import model.game.elements.bombs.Explosion;

import java.io.IOException;

public class BombController extends GameController {
    private long lastTime;
    private long timeDiff;

    public BombController(Arena model) {
        super(model);
        this.lastTime = 0;
        this.timeDiff = 0;
    }

    @Override
    public Arena getModel() {
        return super.getModel();
    }

    // Method to calculate the positions of the explosion
    public void explodeBomb(Bomb bomb, long startTime) {
        // Define the directions to move in
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        // add explosion in the center and check if the player stays on top of the bomb
        explodeBoombermanAtBomb(bomb);
        getModel().getExplosions().add(new Explosion(bomb.getPosition().getX(), bomb.getPosition().getY(), startTime));

        // Loop through the directions
        for (int i = 0; i < dx.length; i++) {
            // Calculate the next position in this direction
            Position nextPosition = bomb.getPosition();
            for(int j = 0; j<bomb.getRange(); j++) {
                // Calculate the next position in this direction
                nextPosition = nextPosition.getNextPosition(dx[i], dy[i]);

                // Check if there is a wall at the next position
                if (getModel().isWall(nextPosition)) {
                    explodeWall(nextPosition, startTime);
                    break;
                }
                if(getModel().isEnemy(nextPosition)) {
                    getModel().removeEnemy(nextPosition);
                    getModel().getBoomberman().increaseScore(1);
                }
                if(getModel().getBoomberman().getPosition().equals(nextPosition)) {
                    getModel().getBoomberman().decreaseHealth();
                }
                // add explosion to the explosions array in the arena
                getModel().getExplosions().add(new Explosion(nextPosition.getX(), nextPosition.getY(), startTime));
            }
        }
    }

    private void explodeBoombermanAtBomb(Bomb bomb) {
        if(getModel().getBoomberman().getPosition().equals(bomb.getPosition())) {
            getModel().getBoomberman().decreaseHealth();
        }
    }

    private void explodeWall(Position position, long startTime) {
        if(getModel().isDestroyableWall(position)) {
            getModel().removeWall(position);
            getModel().getExplosions().add(new Explosion(position.getX(), position.getY(), startTime));
            dropPowerUp(position, 10);
        }
    }

    private void dropPowerUp(Position position, int chance) {
        if(Math.random()*100<(chance+1)){
            getModel().getPowerups().add(new PowerUp(position.getX(), position.getY()));
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (!getModel().getBombs().isEmpty()) {
            timeDiff = time-lastTime;
            System.out.println(timeDiff);
            for(Bomb bomb : getModel().getBombs()) {
                bomb.subtractTimer(timeDiff);
                if(bomb.getTimer() <= 0) {
                    // remove the bomb from the arena
                    getModel().getBombs().remove(bomb);

                    // explode the bomb
                    explodeBomb(bomb, time);
                    break;
                }
            }
        }
        lastTime = time;
    }
}
