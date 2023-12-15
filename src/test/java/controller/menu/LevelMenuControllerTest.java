package controller.menu;

import game.Game;
import gui.GUI;
import model.menu.LevelMenu;
import model.menu.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import states.MainMenuState;

import java.io.IOException;

public class LevelMenuControllerTest {
    private Game game;
    private LevelMenu menu;
    private LevelMenuController menuController;

    @BeforeEach
    void TestSetup() {
        game = Mockito.mock(Game.class);
        menu = Mockito.mock(LevelMenu.class);
        menuController = new LevelMenuController(menu);
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
