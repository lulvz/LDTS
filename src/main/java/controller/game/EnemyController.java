package controller.game;

import game.Game;
import gui.GUI;
import model.Position;
import model.game.arena.Arena;
import model.game.elements.Enemy;
import strategy.StrategyFollow;
import strategy.StrategyRandom;

import java.io.IOException;

public class EnemyController extends GameController {
    private long lastposition;
    public EnemyController(Arena model) {
        super(model);
        this.lastposition = 0;
    }
    private void moveEnemy(Enemy enemy, Position position) {
        if (!getModel().isWall(position) && !getModel().isBomb(position) && !getModel().isEnemy(position) && !getModel().isPowerUp(position) && !getModel().isCoin(position)) {
            enemy.setPosition(position);
            
            // check if the enemy is on top of the player
            Position boombermanPosition = getModel().getBoomberman().getPosition();
            if (boombermanPosition.equals(position))
                getModel().getBoomberman().decreaseHealth();
        }
    }

    private void checkChase(Enemy enemy, boolean chase) {
        if (chase && (enemy.getStrategy() instanceof StrategyRandom)) {
            enemy.setStrategy(new StrategyFollow());
        } else if (!chase && (enemy.getStrategy() instanceof StrategyFollow)){
            enemy.setStrategy(new StrategyRandom());
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        // check if enemy is within 5 squares of boomberman, if he is, switch strategy to StrategyFollow
        // if he is not, switch strategy to StrategyRandom
        if (time - lastposition > 500) {
            for (Enemy enemy : getModel().getEnemies()) {

                boolean chase = enemy.getPosition().distance(getModel().getBoomberman().getPosition()) < 7;

                System.out.println(chase);

                checkChase(enemy, chase);
                System.out.println(enemy.getStrategy());
                moveEnemy(enemy, enemy.getNextPosition(getModel().getBoomberman().getPosition()));
            }
            this.lastposition = time;
        }
    }
}
