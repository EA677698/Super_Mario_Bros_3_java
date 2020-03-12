package Elements.Tiles.Interactables;

import Elements.Layer;
import Elements.Tiles.Tile;

import java.awt.*;

public abstract class Interactable extends Tile {

    private boolean isActivated;
    private int currentSprite;

    public Interactable(Layer layer, Point location, boolean collision) {
        super(layer, location, collision);
    }

    @Override
    public void tick() {
        super.tick();
    }

    public abstract void executeOnTouch();

    public abstract void executeOnAction();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public int getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(int currentSprite) {
        this.currentSprite = currentSprite;
    }
}
