package view;

import gui.GUI;

import java.io.IOException;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    // each viewer is going to have their own way of drawing the model
    // this should be done in the drawElements method
    public void draw(GUI gui) throws IOException {
        gui.clear();
        drawElements(gui);
        gui.refresh();
    }

    // method that should be implemented by the subclasses to draw the model
    protected abstract void drawElements(GUI gui);
}
