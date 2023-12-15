package controller.menu;

import game.Game;
import gui.GUI;
import model.menu.ExplanationMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import states.MainMenuState;

import java.io.IOException;

public class ExplanationMenuControllerTest {
    private Game game;
    private ExplanationMenu menu;
    private ExplanationMenuController menuController;

    @BeforeEach
    void TestSetup() {
        game = Mockito.mock(Game.class);
        menu = Mockito.mock(ExplanationMenu.class);
        menuController = new ExplanationMenuController(menu);
    }

    @Test
    void MenuTest() throws IOException {
        menuController.step(game, GUI.ACTION.SELECT, 0);
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(MainMenuState.class));
        menuController.step(game, GUI.ACTION.QUIT, 0);
        Mockito.verify(game, Mockito.times(2)).setState(Mockito.any(MainMenuState.class));
    }
}
