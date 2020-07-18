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
    }

    @Override
    public Image[] getSprites() {
        return Main.game.getSpritesLoader().getClouds();
    }

    @Override
    public Image[][] get2DSprites() {
        Image[][] ret = new Image[2][3];
        int index = 0;
        for(int i = 0; i<ret.length; i++){
            for(int e = 0; e<ret[0].length; e++){
                ret[i][e] = Main.game.getSpritesLoader().getClouds()[index];
                index++;
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        return super.toString()+type+",";
    }
}
