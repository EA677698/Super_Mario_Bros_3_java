package Elements.Tiles;

import Elements.Layer;
import Main.Main;
import java.awt.*;

public class Shrub extends Tile {

    public Shrub(Layer layer, Point location, boolean collision) {
        super(layer, location, collision);
        setTileName("Shrub");
    }

    @Override
    public Image[] getSprites() {
        return Main.game.getSpritesLoader().getShrub();
    }
}
