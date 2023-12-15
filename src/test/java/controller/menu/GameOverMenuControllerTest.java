package controller.menu;

import game.Game;
import gui.GUI;
import model.menu.GameOverMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import states.MainMenuState;

import java.io.IOException;

public class GameOverMenuControllerTest {
    private Game game;
    private GameOverMenu menu;
    private GameOverMenuController menuController;

    @BeforeEach
    void TestSetup() {
        game = Mockito.mock(Game.class);
        menu = Mockito.mock(GameOverMenu.class);
        menuController = new GameOverMenuController(menu);
    }

    @Test
    void MenuTest() throws IOException {
        menuController.step(game, GUI.ACTION.DOWN, 0);
        Mockito.verify(menu, Mockito.times(1)).nextEntry();
        menuController.step(game, GUI.ACTION.UP, 0);
        Mockito.verify(menu, Mockito.times(1)).previousEntry();
        menuController.step(game, GUI.ACTION.QUIT, 0);
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(MainMenuState.class));
    }
}
