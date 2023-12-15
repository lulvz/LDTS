package states;

import controller.Controller;
import controller.menu.ExplanationMenuController;
import model.menu.ExplanationMenu;
import view.Viewer;
import view.menu.ExplanationMenuViewer;


public class ExplanationMenuState extends State<ExplanationMenu>{

    public ExplanationMenuState(ExplanationMenu model){
        super(model);
    }

    @Override
    protected Viewer<ExplanationMenu> getViewer() {
        return new ExplanationMenuViewer(getModel());
    }

    @Override
    protected Controller<ExplanationMenu> getController() {
        return new ExplanationMenuController(getModel());
    }
}
