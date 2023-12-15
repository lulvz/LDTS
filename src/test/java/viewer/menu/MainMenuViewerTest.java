package viewer.menu;

import gui.GUI;
import model.menu.MainMenu;
import model.menu.ShopMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.menu.MainMenuViewer;
import view.menu.ShopViewer;

import java.io.IOException;

public class MainMenuViewerTest {
    private GUI gui;
    private MainMenu menu;
    private MainMenuViewer menuviewer;

    @BeforeEach
    void TestSetup() {
        gui = Mockito.mock(GUI.class);
        menu = new MainMenu();
        menuviewer = new MainMenuViewer(menu);
    }

    @Test
    void drawTest() throws IOException {
        menuviewer.draw(gui);
        Mockito.verify(gui, Mockito.times(5)).drawText(Mockito.any(), Mockito.any(), Mockito.any());
    }
}
