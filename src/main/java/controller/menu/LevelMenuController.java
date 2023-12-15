package controller.menu;

import controller.Controller;
import game.Game;
import gui.GUI;
import model.game.arena.ArenaLoader;
import model.menu.LevelMenu;
import model.menu.MainMenu;
import states.GameState;
import states.MainMenuState;

import java.io.IOException;

public class LevelMenuController extends Controller<LevelMenu> {
    public LevelMenuController(LevelMenu model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) game.setState(new MainMenuState(new MainMenu()));
                if (getModel().isSelectedLevel()) {
                    // re-read files to make sure the highscore is updated
                    Game.readSavefile();
                    game.setState(new GameState(new ArenaLoader(getModel().getSelectedLevel()).createArena()));
                }
                break;
            case QUIT:
                game.setState(new MainMenuState(new MainMenu()));
                break;
        }
    }
}
