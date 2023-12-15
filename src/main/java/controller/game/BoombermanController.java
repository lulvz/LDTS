package controller.game;

import game.Game;
import gui.GUI;
import model.Position;
import model.game.arena.Arena;
import model.game.elements.bombs.Bomb;

import java.io.IOException;

public class BoombermanController extends GameController {
    private long lastposition;
    public BoombermanController(Arena model) {
        super(model);
        this.lastposition = 0;
    }

    public void movePlayerLeft() {
        movePlayer(getModel().getBoomberman().getPosition().getLeft());
    }

    public void movePlayerRight() {
        movePlayer(getModel().getBoomberman().getPosition().getRight());
    }

    public void movePlayerUp() {
        movePlayer(getModel().getBoomberman().getPosition().getUp());
    }

    public void movePlayerDown() {
        movePlayer(getModel().getBoomberman().getPosition().getDown());
    }

    private void checkBombMax() {
        if (getModel().getBoomberman().getMaxBombs() > 3) {
            getModel().getBoomberman().setMaxBombs(getModel().getBoomberman().getMaxBombs() - 1);
        }
    }

    public void placeBomb() {
        // if the player has bombs left and isn't on top of another bomb
        if (getModel().getBoomberman().getBombs().size() > 0 && !(getModel().isBomb(getModel().getBoomberman().getPosition()))) {
            // get random next bomb and set its position to the player's position
            Bomb nextBomb = getModel().getBoomberman().getNextBomb();
            nextBomb.setPosition(getModel().getBoomberman().getPosition());
            // add the bomb to the arena
            getModel().getBombs().add(nextBomb);
            getModel().getBoomberman().popBomb();
            checkBombMax();
        }
    }

    private void movePlayer(Position position) {
        if (!getModel().isWall(position) && !getModel().isBomb(position)) {
            getModel().getBoomberman().setPosition(position);
            // ENEMIES
            checkEnemies(position);
            // COINS
            checkCoins(position);
            // POWER UPS
            checkPowerUps(position);
        }
    }

    private void checkEnemies(Position position) {
        if (getModel().isEnemy(position)) {
            getModel().getBoomberman().decreaseHealth();
        }
    }

    private void checkCoins(Position position) {
        if (getModel().isCoin(position)) {
            // go through each coin and remove the one that was picked up
            getModel().removeCoin(position);
        }
    }

    private void checkPowerUps(Position position) {
        if (getModel().isPowerUp(position)) {
            // go through each powerup and remove the one that was picked up
            getModel().removePowerUp(position);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.UP) movePlayerUp();
        if (action == GUI.ACTION.RIGHT) movePlayerRight();
        if (action == GUI.ACTION.DOWN) movePlayerDown();
        if (action == GUI.ACTION.LEFT) movePlayerLeft();
        if (action == GUI.ACTION.SELECT) placeBomb();

        if (!getModel().getBoomberman().canAddBomb()) {
            this.lastposition = time;
        }
        // only starts counting time after the player has placed a bomb and freed a slot
        checkBombAdd(time);
    }

    private void checkBombAdd(long time) {
        if (time - lastposition > 3000 && getModel().getBoomberman().canAddBomb()) {
            getModel().getBoomberman().insertBomb(getModel().getRandomBomb());
            System.out.println("added bomb");
            this.lastposition = time;
        }
    }
}
