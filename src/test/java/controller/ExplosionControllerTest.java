package controller;

import controller.game.ExplosionController;
import game.Game;
import gui.GUI;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplosionControllerTest {
    private Game game;
    private ExplosionController explosionController;
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

        explosionController = new ExplosionController(arena);
    }

    @Test
    void checkIfRemoved() throws IOException {
        Explosion e_test = new Explosion(7, 7, 0);
        arena.setExplosions(new ArrayList<Explosion>(Arrays.asList(e_test)));
        explosionController.step(game, GUI.ACTION.NONE, 500);
        assertEquals(0, arena.getExplosions().size());
    }
}
