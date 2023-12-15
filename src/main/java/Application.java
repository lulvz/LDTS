import game.Game;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        System.out.println("Starting application...");
        Game game = new Game();
        game.run();
    }
}
