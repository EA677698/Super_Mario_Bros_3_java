package Elements.Tiles;

import Elements.Layer;

import java.awt.*;

public class Shrub extends Tile {

    Image[] sprite = new Image[1];

    public Shrub(Layer layer, Point location, boolean collision) {
        super(layer, location, collision);
        setTileName("Shrub");
    }

    @Override
    public void initializeImages() {

    }

    @Override
    public Image[] getSprites() {
        sprite[0] = createSprite(getLocalPath()+"\\assets\\Tiles\\other\\shrub.png");
        return sprite;
    }
}
