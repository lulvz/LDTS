package states;

import controller.Controller;
import controller.menu.LevelMenuController;
import model.menu.LevelMenu;
import view.Viewer;
import view.menu.LevelMenuViewer;

public class LevelMenuState extends State<LevelMenu> {
    public LevelMenuState(LevelMenu model) {
        super(model);
    }

    @Override
    protected Viewer<LevelMenu> getViewer() {
        return new LevelMenuViewer(getModel());
    }

    @Override
    protected Controller<LevelMenu> getController() {
        return new LevelMenuController(getModel());
    }
}
