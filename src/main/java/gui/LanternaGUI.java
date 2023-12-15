package gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import model.Position;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {
    private final Screen screen;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }
    public LanternaGUI(int width, int height) throws IOException, URISyntaxException, FontFormatException {
        AWTTerminalFontConfiguration fontconfig = FontConfig();
        Terminal terminal = CreateTerminal(width, height, fontconfig);
        this.screen = CreateScreen(terminal);
    }

    private Terminal CreateTerminal (int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        return terminalFactory.createTerminal();
    }

    private Screen CreateScreen (Terminal terminal) throws IOException {
        Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    @Override
    public ACTION getNextAction() throws IOException {
        KeyStroke ks = screen.pollInput();
        if (ks == null) return ACTION.NONE;
        if (ks.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if (ks.getKeyType() == KeyType.Escape) return ACTION.QUIT;

        if (ks.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (ks.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        if (ks.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;
        if (ks.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
        if (ks.getKeyType() == KeyType.Enter) return ACTION.SELECT;
        if (ks.getKeyType() == KeyType.Character && ks.getCharacter() == ' ') return ACTION.SELECT;

        // WASD
        if (ks.getKeyType() == KeyType.Character && ks.getCharacter() == 'w') return ACTION.UP;
        if (ks.getKeyType() == KeyType.Character && ks.getCharacter() == 's') return ACTION.DOWN;
        if (ks.getKeyType() == KeyType.Character && ks.getCharacter() == 'a') return ACTION.LEFT;
        if (ks.getKeyType() == KeyType.Character && ks.getCharacter() == 'd') return ACTION.RIGHT;
        if (ks.getKeyType() == KeyType.Character && ks.getCharacter() == 'q') return ACTION.QUIT;

        return ACTION.NONE;
    }

    private AWTTerminalFontConfiguration FontConfig() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/kongtext.ttf");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    @Override
    public void drawElement(Position position, char element, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(position.getX(), position.getY() + 1, "" + element);
    }

    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(position.getX(), position.getY(), text);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
