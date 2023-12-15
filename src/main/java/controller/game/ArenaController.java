package controller.game;

import game.Game;
import gui.GUI;
import model.game.arena.Arena;
import model.game.arena.ArenaBuilder;
import model.menu.GameOverMenu;
import model.menu.LevelMenu;
import states.GameOverMenuState;
import states.LevelMenuState;

import java.io.IOException;

public class ArenaController extends GameController {
    private final BoombermanController boombermanController;
    private final EnemyController enemyController;
    private final BombController bombController;
    private final ExplosionController explosionController;
    public ArenaController(Arena model) {
        super(model);
        this.boombermanController = new BoombermanController(model);
        this.enemyController = new EnemyController(model);
        this.bombController = new BombController(model);
        this.explosionController = new ExplosionController(model);
    }

    private void checkHighscore(int current_level_highscore) {
        if(getModel().getBoomberman().getScore() > current_level_highscore) {
            Game.setHighscore(getModel().getLevel(), getModel().getBoomberman().getScore());
            Game.writeSavefile();
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT || getModel().getBoomberman().getHealth() <= 0 || getModel().getEnemies().isEmpty()) {
            // check player's highscore and compare it to the current level's highscore
            // if hte player's highscore is bigger than the current highscore then save it to the file
            int current_level_highscore = Game.getHighscore((getModel().getLevel()));
            checkHighscore(current_level_highscore);
            // sleep for 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int score = getModel().getBoomberman().getScore();
            int enemiesLeft = getModel().getEnemies().size();
            game.setState(new GameOverMenuState(new GameOverMenu(getModel().getLevel(), enemiesLeft, score)));
        }
        boombermanController.step(game, action, time);
        enemyController.step(game, action, time);
        bombController.step(game, action, time);
        explosionController.step(game, action, time);
    }
}
