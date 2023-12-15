package gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LanternaGUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics textGraphics;

    @BeforeEach
    void TestSetup() {
        screen = Mockito.mock(Screen.class);
        textGraphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);
        gui = new LanternaGUI(screen);
    }

    @Test
    void drawText() {
        gui.drawText(new Position(5, 5), "Testing LanternaGUI", "#ffffff");
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(new TextColor.RGB(255, 255, 255));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(5,5, "Testing LanternaGUI");
    }

    @Test
    void drawElement() {
        gui.drawElement(new Position(5, 4), 'T', "#ffffff");
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(new TextColor.RGB(255, 255, 255));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(5, 5, "T");
    }
}
