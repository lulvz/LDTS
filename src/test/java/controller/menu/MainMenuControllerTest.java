package controller.menu;

import controller.menu.MainMenuController;
import game.Game;
import gui.GUI;
import model.menu.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import states.LevelMenuState;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainMenuControllerTest {
    private Game game;
    private MainMenu menu;
    private MainMenuController mainMenuController;

    @BeforeEach
    void TestSetup() {
        game = Mockito.mock(Game.class);
        menu = Mockito.mock(MainMenu.class);
        mainMenuController = new MainMenuController(menu);
    }

    @Test
    void MenuTest() throws IOException {
        mainMenuController.step(game, GUI.ACTION.DOWN, 0);
        Mockito.verify(menu, Mockito.times(1)).nextEntry();
        mainMenuController.step(game, GUI.ACTION.UP, 0);
        Mockito.verify(menu, Mockito.times(1)).previousEntry();
        mainMenuController.step(game, GUI.ACTION.QUIT, 0);
        Mockito.verify(game, Mockito.times(1)).setState(null);
    }
}
