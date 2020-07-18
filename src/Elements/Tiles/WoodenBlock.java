package Elements.Tiles;

import Elements.Layer;
import Main.Main;
import java.awt.*;

public class WoodenBlock extends Tile {

    private Image blocked;

    public WoodenBlock(Layer layer, Point location, boolean collision) {
        super(layer, location, collision);
        setTileName("WoodenBlock");
    }

    @Override
    public Image[] getSprites() {
        return Main.game.getSpritesLoader().getWoodenBlock();
    }
}
