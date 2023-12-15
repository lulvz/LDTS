package states;

import controller.Controller;
import controller.menu.GameOverMenuController;
import model.menu.GameOverMenu;
import view.Viewer;
import view.menu.GameOverMenuViewer;

public class GameOverMenuState extends State<GameOverMenu> {
    public GameOverMenuState(GameOverMenu model) { super(model); }
    @Override
    protected Viewer<GameOverMenu> getViewer() {
        return new GameOverMenuViewer(getModel());
    }

    @Override
    protected Controller<GameOverMenu> getController() {
        return new GameOverMenuController(getModel());
    }
}
