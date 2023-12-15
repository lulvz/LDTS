package viewer.menu;

import gui.GUI;
import model.menu.ShopMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.menu.ShopViewer;

import java.io.IOException;

public class ShopMenuViewerTest {
    private GUI gui;
    private ShopMenu menu;
    private ShopViewer menuviewer;

    @BeforeEach
    void TestSetup() {
        gui = Mockito.mock(GUI.class);
        menu = new ShopMenu(3);
        menuviewer = new ShopViewer(menu);
    }

    @Test
    void drawTest() throws IOException {
        menuviewer.draw(gui);
        Mockito.verify(gui, Mockito.times(5)).drawText(Mockito.any(), Mockito.any(), Mockito.any());
    }
}
