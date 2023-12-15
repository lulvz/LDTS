package viewer.menu;

import gui.GUI;
import model.menu.ExplanationMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.menu.ExplanationMenuViewer;
import view.menu.ShopViewer;

import java.io.IOException;

public class ExplanationMenuViewerTest {
    private GUI gui;
    private ExplanationMenu menu;
    private ExplanationMenuViewer menuviewer;

    @BeforeEach
    void TestSetup() {
        gui = Mockito.mock(GUI.class);
        menu = new ExplanationMenu();
        menuviewer = new ExplanationMenuViewer(menu);
    }

    @Test
    void drawTest() throws IOException {
        menuviewer.draw(gui);
        Mockito.verify(gui, Mockito.times(19)).drawText(Mockito.any(), Mockito.any(), Mockito.any());
    }
}
