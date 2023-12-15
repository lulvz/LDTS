package viewer.menu;

import gui.GUI;
import model.menu.LevelMenu;
import model.menu.ShopMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.menu.LevelMenuViewer;
import view.menu.ShopViewer;

import java.io.IOException;

public class LevelMenuViewerTest {
    private GUI gui;
    private LevelMenu menu;
    private LevelMenuViewer menuviewer;

    @BeforeEach
    void TestSetup() {
        gui = Mockito.mock(GUI.class);
        menu = new LevelMenu(3);
        menuviewer = new LevelMenuViewer(menu);
    }

    @Test
    void drawTest() throws IOException {
        menuviewer.draw(gui);
        Mockito.verify(gui, Mockito.times(5)).drawText(Mockito.any(), Mockito.any(), Mockito.any());
    }
}
