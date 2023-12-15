package viewer;

import gui.GUI;
import model.game.elements.Boomberman;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.game.BoombermanViewer;

public class BoombermanViewerTest {
    private Boomberman boomberman;
    private BoombermanViewer boombermanViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        boomberman = new Boomberman(12, 12, 5, 3);
        boombermanViewer = new BoombermanViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawBoomberman() {
        boombermanViewer.draw(boomberman, gui);
        Mockito.verify(gui, Mockito.times(1)).drawElement(boomberman.getPosition(), boomberman.getSymbol(), boomberman.getColor());
    }
}
