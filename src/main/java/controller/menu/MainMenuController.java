package controller.menu;

import controller.Controller;
import game.Game;
import gui.GUI;
import model.game.arena.ArenaBuilder;
import model.menu.ExplanationMenu;
import model.menu.LevelMenu;
import model.menu.MainMenu;
import model.menu.ShopMenu;
import states.ExplanationMenuState;
import states.LevelMenuState;
import states.ShopState;

import java.io.IOException;

public class MainMenuController extends Controller<MainMenu> {
    public MainMenuController(MainMenu model) {
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
                if (getModel().isSelectedExit()) game.setState(null);
                if (getModel().isSelectedStart()) {
                    // get amount of files in levels folder from resources
                    int amountOfLevels = ArenaBuilder.getFilesAmount("levels");
                    game.setState(new LevelMenuState(new LevelMenu(amountOfLevels)));
                }
                if (getModel().isSelectedShop()) {
                    int amountOfSkins = ArenaBuilder.getFilesAmount("skins");
                    game.setState(new ShopState(new ShopMenu(amountOfSkins)));
                }
                if(getModel().isSelectedExplanation()){
                    game.setState(new ExplanationMenuState(new ExplanationMenu()));
                }
                break;
            case QUIT:
                game.setState(null);
                break;
        }
    }
}
