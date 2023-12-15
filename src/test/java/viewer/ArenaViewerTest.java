package viewer;

import controller.game.ArenaController;
import gui.GUI;
import model.Position;
import model.game.arena.Arena;
import model.game.elements.Boomberman;
import model.game.elements.Coin;
import model.game.elements.Enemy;
import model.game.elements.PowerUps.PowerUp;
import model.game.elements.PowerUps.pUps;
import model.game.elements.Wall;
import model.game.elements.bombs.BigBomb;
import model.game.elements.bombs.Bomb;
import model.game.elements.bombs.Explosion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.StrategyRandom;
import view.game.ArenaViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;

public class ArenaViewerTest {
    private ArenaViewer arenaViewer;
    private Arena arena;
    private GUI gui;

    @BeforeEach
    void TestSetup() {
        arena = new Arena(25, 25, 1, 0);
        arenaViewer = new ArenaViewer(arena);
        gui = Mockito.mock(GUI.class);


        arena.setWalls(new ArrayList<Wall>(Arrays.asList(new Wall(7, 6, 1, true), new Wall(10, 11, 2, true)
                , new Wall(15, 12, 1, false))));
        arena.setEnemies(new ArrayList<Enemy>(Arrays.asList(new Enemy(4, 5, new StrategyRandom()), new Enemy(1, 1, new StrategyRandom()))));
        arena.setBoomberman(new Boomberman(12, 13, 3, 2));
        arena.setBombs(new ArrayList<Bomb>(Arrays.asList(new BigBomb(10, 10))));
        arena.setCoins(new ArrayList<Coin>(Arrays.asList(new Coin(7, 6), new Coin(10, 11), new Coin(15, 12))));
        arena.setPowerups(new ArrayList<PowerUp>(Arrays.asList(new PowerUp(18, 17, pUps.HP_UP), new PowerUp(19,18, pUps.BIGGER_BOMBS), new PowerUp(20, 19, pUps.EXTRA_BOMBS))));
        arena.setExplosions(new ArrayList<Explosion>(Arrays.asList(new Explosion(22, 22, 0))));
    }

    @Test
    void drawWalls() throws IOException {
        arenaViewer.draw(gui);
        Wall tmp = new Wall(0, 0, 0, true);
        Wall tmp_destroyable = new Wall(0, 0, 0, false);
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(7, 6)), eq(tmp.getSymbol()), eq(tmp.getColor()));
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(10, 11)), eq(tmp.getSymbol()), eq(tmp.getColor()));
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(15, 12)), eq(tmp_destroyable.getSymbol()), eq(tmp_destroyable.getColor()));
        Mockito.verify(gui, Mockito.times(2)).drawElement(Mockito.any(Position.class), Mockito.anyChar(), eq(tmp.getColor()));
        Mockito.verify(gui, Mockito.times(1)).drawElement(Mockito.any(Position.class), Mockito.anyChar(), eq(tmp_destroyable.getColor()));
    }

    @Test
    void drawEnemies() throws IOException {
        arenaViewer.draw(gui);
        Enemy tmp = new Enemy(0, 0, new StrategyRandom());
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(4, 5)), eq(tmp.getSymbol()), eq(tmp.getColor()));
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(1, 1)), eq(tmp.getSymbol()), eq(tmp.getColor()));
        Mockito.verify(gui, Mockito.times(2)).drawElement(Mockito.any(Position.class), eq(tmp.getSymbol()), eq(tmp.getColor()));
    }

    @Test
    void drawBoomberman() throws IOException {
        arenaViewer.draw(gui);
        Boomberman tmp = new Boomberman(0, 0, 0, 0);
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(12, 13)), eq(tmp.getSymbol()), eq(tmp.getColor()));
        Mockito.verify(gui, Mockito.times(1)).drawElement(Mockito.any(Position.class), eq(tmp.getSymbol()), eq(tmp.getColor()));
    }

    @Test
    void drawBombs() throws IOException {
        arenaViewer.draw(gui);
        BigBomb tmp = new BigBomb(0, 0);
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(10, 10)), eq(tmp.getSymbol()), eq(tmp.getColor()));
        Mockito.verify(gui, Mockito.times(1)).drawElement(Mockito.any(Position.class), eq(tmp.getSymbol()), eq(tmp.getColor()));
    }

    @Test
    void drawCoins() throws IOException {
        arenaViewer.draw(gui);
        Coin tmp = new Coin(0, 0);
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(7, 6)), eq(tmp.getSymbol()), eq(tmp.getColor()));
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(10, 11)), eq(tmp.getSymbol()), eq(tmp.getColor()));
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(15, 12)), eq(tmp.getSymbol()), eq(tmp.getColor()));
        Mockito.verify(gui, Mockito.times(3)).drawElement(Mockito.any(Position.class), eq(tmp.getSymbol()), eq(tmp.getColor()));
    }

    @Test
    void drawPowerups() throws IOException {
        arenaViewer.draw(gui);
        PowerUp hp_tmp = new PowerUp(0, 0, pUps.HP_UP);
        PowerUp bb_tmp = new PowerUp(0,0, pUps.BIGGER_BOMBS);
        PowerUp eb_tmp = new PowerUp(0, 0, pUps.EXTRA_BOMBS);
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(18, 17)), eq(hp_tmp.getSymbol()), eq(hp_tmp.getColor()));
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(19, 18)), eq(bb_tmp.getSymbol()), eq(bb_tmp.getColor()));
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(20, 19)), eq(eb_tmp.getSymbol()), eq(eb_tmp.getColor()));
    }

    @Test
    void drawExplosions() throws IOException {
        arenaViewer.draw(gui);
        Explosion tmp = new Explosion(0, 0, 0);
        Mockito.verify(gui, Mockito.times(1)).drawElement(eq(new Position(22, 22)), eq(tmp.getSymbol()), eq(tmp.getColor()));
        Mockito.verify(gui, Mockito.times(1)).drawElement(Mockito.any(Position.class), eq(tmp.getSymbol()), eq(tmp.getColor()));
    }

    @Test
    void refreshAndClear() throws IOException {
        arenaViewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }
}