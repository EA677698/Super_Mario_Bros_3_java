package Elements.Tiles;

import Elements.Layer;
import Main.Main;
import java.awt.*;

public class Clouds extends Tile implements LayeredTile {

    private final int type;

    public Clouds(Layer layer, Point location, boolean collision, int type) {
        super(layer, location, collision);
        this.type = type;
        setTileName("Cloud");
        calculateTileLayers();
    }

    @Override
    public Image[] getSprites() {
        return Main.game.getSpritesLoader().getClouds();
    }

    @Override
    public void calculateTileLayers() {
        Image[][] tile = new Image[2][3];
        int index = 0;
        for(int i = 0; i<tile.length; i++){
            for(int e = 0; e<tile[0].length; e++){
                tile[i][e] = Main.game.getSpritesLoader().getClouds()[index];
                index++;
            }
        }
        Main.game.getManager().getTileLayouts().put(getUUID(),tile);
    }

    @Override
    public Image[][] get2DSprites() {
        if (!Main.game.getManager().getTileLayouts().containsKey(getUUID())) {
            calculateTileLayers();
        }
        return Main.game.getManager().getTileLayouts().get(getUUID());
    }

    @Override
    public String toString() {
        return super.toString()+type+",";
    }
}
