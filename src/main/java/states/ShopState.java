package states;

import controller.Controller;
import controller.menu.ShopMenuController;
import model.menu.ShopMenu;
import view.Viewer;
import view.menu.ShopViewer;

public class ShopState extends State<ShopMenu> {
    public ShopState(ShopMenu model) {
        super(model);
    }

    @Override
    protected Viewer<ShopMenu> getViewer() {
        return new ShopViewer(getModel());
    }

    @Override
    protected Controller<ShopMenu> getController() {
        return new ShopMenuController(getModel());
    }
}