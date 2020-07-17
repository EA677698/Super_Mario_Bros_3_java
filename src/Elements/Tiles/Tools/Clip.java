package Elements.Tiles.Tools;

import Elements.Layer;

import java.awt.*;

public class Clip extends Tool {

    public Clip(Layer layer, Point location, int middleBlocks, int layers) {
        super(layer, location, true, middleBlocks, layers);
        this.setTileName("Clip");
    }
}
