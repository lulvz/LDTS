package viewer.menu;

import gui.GUI;
import model.menu.GameOverMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.menu.GameOverMenuViewer;

import java.io.IOException;

public class GameOverMenuViewerTest {
    private GUI gui;
    private GameOverMenu menu;
    private GameOverMenuViewer menuViewer;

    @BeforeEach
    void TestSetup() {
        gui = Mockito.mock(GUI.class);
        menu = new GameOverMenu(1, 1, 0);
        menuViewer = new GameOverMenuViewer(menu);
    }

    @Test
    void drawTest() throws IOException {
        menuViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(5)).drawText(Mockito.any(), Mockito.any(), Mockito.any());
    }
}
