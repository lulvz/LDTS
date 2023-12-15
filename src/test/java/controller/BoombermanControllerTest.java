package controller;

import controller.game.BoombermanController;
import game.Game;
import gui.GUI;
import model.Position;
import model.game.arena.Arena;
import model.game.elements.Boomberman;
import model.game.elements.Coin;
import model.game.elements.PowerUps.PowerUp;
import model.game.elements.PowerUps.pUps;
import model.game.elements.Wall;
import model.game.elements.bombs.BigBomb;
import model.game.elements.bombs.Bomb;
import model.game.elements.bombs.HugeBomb;
import model.game.elements.bombs.SmallBomb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoombermanControllerTest {
    private Game game;
    private BoombermanController boombermanController;
    private Boomberman boomberman;
    private Arena arena;

    @BeforeEach
    void TestSetup() {
        boomberman = new Boomberman(10, 10, 5, 3);
        arena = new Arena(25, 25, 1, 0);
        this.game = Mockito.mock(Game.class);
        arena.setBoomberman(boomberman);
        arena.setBombs(new ArrayList<>());
        arena.setCoins(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.setEnemies(new ArrayList<>());
        arena.setPowerups(new ArrayList<>());

        boombermanController = new BoombermanController(arena);
    }

    @Test
    void moveBoombermanRightEmpty() {
        boombermanController.movePlayerRight();
        assertEquals(new Position(11, 10), boomberman.getPosition());
    }

    @Test
    void moveBoombermanUpEmpty() {
        boombermanController.movePlayerUp();
        assertEquals(new Position(10, 9), boomberman.getPosition());
    }

    @Test
    void moveBoombermanDownEmpty() {
        boombermanController.movePlayerDown();
        assertEquals(new Position(10, 11), boomberman.getPosition());
    }

    @Test
    void moveBoombermanLeftNotEmpty() {
        arena.setWalls(new ArrayList<>(List.of(new Wall(9, 10, 1, true))));
        boombermanController.movePlayerLeft();
        assertEquals(new Position(10, 10), boomberman.getPosition());
    }

    @Test
    void getRandomBombAuto() throws IOException {
        boombermanController.step(game, GUI.ACTION.NONE, 3001);
        assertEquals(1, boomberman.getBombs().size());
        assertNotNull(boomberman.getBombs().get(0));
    }

    @Test
    void placeSmallBombBoomberman() {
        Bomb smb_test = new SmallBomb(0, 0);
        boomberman.insertBomb(smb_test);
        boombermanController.placeBomb();
        assertTrue((arena.isBomb(new Position(10, 10)) && arena.getBombs().get(0).getRange() == 1));
    }

    @Test
    void placeBigBombBoomberman() {
        Bomb bgb_test = new BigBomb(0, 0);
        boomberman.insertBomb(bgb_test);
        boombermanController.placeBomb();
        assertTrue((arena.isBomb(new Position(10, 10)) && arena.getBombs().get(0).getRange() == 2));
    }

    @Test
    void placeHugeBombBoomberman() {
        Bomb hgb_test = new HugeBomb(0, 0);
        boomberman.insertBomb(hgb_test);
        boombermanController.placeBomb();
        assertTrue((arena.isBomb(new Position(10, 10)) && arena.getBombs().get(0).getRange() == 3));
    }

    @Test
    void collectCoins() {
        Coin c_test = new Coin(11,10);
        arena.setCoins(new ArrayList<>(List.of(c_test)));
        boombermanController.movePlayerRight();
        arena.removeCoin(boomberman.getPosition());
        assertEquals(0, arena.getCoins().size());
        assertEquals(1, boomberman.getScore());
    }

    @Test
    void collectHP_UPPowerup() {
        PowerUp p_test = new PowerUp(11, 10, pUps.HP_UP);
        arena.setPowerups(new ArrayList<>(List.of(p_test)));
        boombermanController.movePlayerRight();
        arena.removePowerUp(boomberman.getPosition());
        assertEquals(0, arena.getPowerups().size());
        assertEquals(6, boomberman.getHealth());
    }

    @Test
    void collectBIGGER_BOMBSPowerup() {
        PowerUp p_test = new PowerUp(11, 10, pUps.BIGGER_BOMBS);
        arena.setPowerups(new ArrayList<>(List.of(p_test)));
        boombermanController.movePlayerRight();
        arena.removePowerUp(boomberman.getPosition());
        assertEquals(0, arena.getPowerups().size());
        for (int i = 0; i < boomberman.getBombs().size(); i++) {
            assertEquals(10, boomberman.getBombs().get(i).getRange());
        }
    }

    @Test
    void collectEXTRA_BOMBSPowerup() {
        PowerUp p_test = new PowerUp(11, 10, pUps.EXTRA_BOMBS);
        arena.setPowerups(new ArrayList<>(List.of(p_test)));
        boombermanController.movePlayerRight();
        arena.removePowerUp(boomberman.getPosition());
        assertEquals(0, arena.getPowerups().size());
        assertEquals(5, boomberman.getMaxBombs());
        for (int i = 0; i < boomberman.getBombs().size(); i++) {
            assertTrue(boomberman.getBombs().get(i) instanceof HugeBomb);
        }
    }
}