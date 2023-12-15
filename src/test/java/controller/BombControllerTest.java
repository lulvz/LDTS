package controller;

import controller.game.BombController;
import game.Game;
import gui.GUI;
import model.Position;
import model.game.arena.Arena;
import model.game.elements.Boomberman;
import model.game.elements.Coin;
import model.game.elements.Enemy;
import model.game.elements.PowerUps.PowerUp;
import model.game.elements.Wall;
import model.game.elements.bombs.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.StrategyRandom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BombControllerTest {
    private Game game;
    private BombController bombController;
    private Arena arena;
    private Boomberman boomberman;
    @BeforeEach
    void TestSetup() {
        game = Mockito.mock(Game.class);
        boomberman = new Boomberman(10, 10, 5, 3);
        arena = new Arena(25,25, 1, 0);
        arena.setBoomberman(boomberman);
        arena.setBombs(new ArrayList<Bomb>());
        arena.setWalls(new ArrayList<Wall>());
        arena.setEnemies(new ArrayList<Enemy>());
        arena.setCoins(new ArrayList<Coin>());
        arena.setExplosions(new ArrayList<Explosion>());
        arena.setPowerups(new ArrayList<PowerUp>());

        bombController = new BombController(arena);
    }

    @Test
    void removeBeforeExplode() throws IOException {
        Bomb b_test = new BigBomb(5,5);
        b_test.subtractTimer(b_test.getTimer());
        arena.setBombs(new ArrayList<Bomb>(Arrays.asList(b_test)));
        bombController.step(game, GUI.ACTION.NONE, 0);
        assertEquals(0, arena.getBombs().size());
    }

    @Test
    void explodeDestructibleWall() {
        Wall w_test = new Wall(0, 1, 1, true);
        Bomb b_test = new SmallBomb(0, 0);
        b_test.subtractTimer(b_test.getTimer());
        arena.setWalls(new ArrayList<Wall>(Arrays.asList(w_test)));
        arena.setBombs(new ArrayList<Bomb>(Arrays.asList(b_test)));
        bombController.explodeBomb(b_test, 0);
        assertEquals(0, arena.getWalls().size());
        assertFalse(arena.isWall(new Position(0, 1)));
    }

    @Test
    void explodeIndestructibleWall() {
        Wall w_test = new Wall(0, 1, 1, false);
        Bomb b_test = new SmallBomb(0, 0);
        b_test.subtractTimer(b_test.getTimer());
        arena.setWalls(new ArrayList<Wall>(Arrays.asList(w_test)));
        arena.setBombs(new ArrayList<Bomb>(Arrays.asList(b_test)));
        bombController.explodeBomb(b_test, 0);
        assertEquals(1, arena.getWalls().size());
        assertTrue(arena.isWall(new Position(0, 1)));
    }

    @Test
    void explodeEnemy() {
        Enemy e_test = new Enemy(3, 3, new StrategyRandom());
        Bomb b_test = new SmallBomb(2, 3);
        b_test.subtractTimer(b_test.getTimer());
        arena.setEnemies(new ArrayList<Enemy>(Arrays.asList(e_test)));
        arena.setBombs(new ArrayList<Bomb>(Arrays.asList(b_test)));
        bombController.explodeBomb(b_test, 0);
        assertEquals(0, arena.getEnemies().size());
        assertFalse(arena.isEnemy(new Position(3, 3)));
    }
    @Test
    void damageBoomberman() {
        Bomb b_test = new HugeBomb(9, 10);
        b_test.subtractTimer(b_test.getTimer());
        arena.setBombs(new ArrayList<Bomb>(Arrays.asList(b_test)));
        bombController.explodeBomb(b_test, 0);
        assertEquals(4, arena.getBoomberman().getHealth());
    }
}

