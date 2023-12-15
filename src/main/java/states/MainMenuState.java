package states;

import controller.Controller;
import controller.menu.MainMenuController;
import model.menu.MainMenu;
import view.Viewer;
import view.menu.MainMenuViewer;

public class MainMenuState extends State<MainMenu> {
    public MainMenuState(MainMenu model) {
        super(model);
    }

    @Override
    protected Viewer<MainMenu> getViewer() {
        return new MainMenuViewer(getModel());
    }

    @Override
    protected Controller<MainMenu> getController() {
        return new MainMenuController(getModel());
    }
}
