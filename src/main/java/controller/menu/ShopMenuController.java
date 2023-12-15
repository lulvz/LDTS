package controller.menu;

import controller.Controller;
import game.Game;
import gui.GUI;
import model.game.elements.Boomberman;
import model.menu.MainMenu;
import model.menu.ShopMenu;
import states.MainMenuState;

import java.io.IOException;

public class ShopMenuController extends Controller<ShopMenu> {
    public ShopMenuController(ShopMenu model) {
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
                if (getModel().isSelectedSkin()) {
                    int skinNumber = getModel().getSelectedSkin();
                    // skin is in resources/skins/skinNumber.sym file
                    char skin = Game.getSkin(skinNumber);
                    Boomberman.setSymbol(skin);
                    game.setState(new MainMenuState(new MainMenu()));
                }
                break;
            case QUIT:
                game.setState(new MainMenuState(new MainMenu()));
                break;
        }
    }
}