package controller;

import controller.game.EnemyController;
import game.Game;
import gui.GUI;
import model.Position;
import model.game.arena.Arena;
import model.game.elements.Boomberman;
import model.game.elements.Coin;
import model.game.elements.Enemy;
import model.game.elements.PowerUps.PowerUp;
import model.game.elements.Wall;
import model.game.elements.bombs.Bomb;
import model.game.elements.bombs.Explosion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.StrategyFollow;
import strategy.StrategyRandom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EnemyControllerTest {
    private Game game;
    private Arena arena;
    private EnemyController enemyController;
    private Boomberman boomberman;

    @BeforeEach
    void TestSetup() {
        this.boomberman = new Boomberman(5, 5, 5, 2);
        this.game = Mockito.mock(Game.class);
        this.arena = new Arena(25, 25, 1, 0);
        this.enemyController = new EnemyController(arena);
        arena.setWalls(new ArrayList<Wall>());
        arena.setEnemies(new ArrayList<Enemy>());
        arena.setBombs(new ArrayList<Bomb>());
        arena.setPowerups(new ArrayList<PowerUp>());
        arena.setCoins(new ArrayList<Coin>());
        arena.setExplosions(new ArrayList<Explosion>());
        arena.setBoomberman(boomberman);
    }

    @Test
    void moveEnemies() throws IOException {
        Enemy enemy = new Enemy(10, 10, new StrategyRandom());
        arena.setEnemies(new ArrayList<Enemy>(Arrays.asList(enemy)));
        enemyController.step(game, GUI.ACTION.NONE, 1000);
        assertNotEquals(new Position(10, 10), enemy.getPosition());
    }

    @Test
    void getHitByEnemy() throws IOException {
        Enemy enemy = new Enemy(6, 5, new StrategyFollow());
        arena.setEnemies(new ArrayList<>(Arrays.asList(enemy)));
        enemyController.step(game, GUI.ACTION.NONE, 1000);
        assertEquals(4, boomberman.getHealth());
    }
}
