package controller.menu;

import controller.Controller;
import game.Game;
import gui.GUI;
import model.game.arena.ArenaLoader;
import model.menu.ExplanationMenu;
import model.menu.GameOverMenu;
import model.menu.MainMenu;
import states.GameState;
import states.MainMenuState;

import java.io.IOException;

public class ExplanationMenuController extends Controller<ExplanationMenu> {

    public ExplanationMenuController(ExplanationMenu model) { super(model); }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case SELECT:
            case QUIT:
                game.setState(new MainMenuState(new MainMenu()));
                break;
        }
    }
}
