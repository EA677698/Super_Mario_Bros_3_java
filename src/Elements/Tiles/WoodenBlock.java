package Elements.Tiles;

import Elements.Layer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WoodenBlock extends Tile {

    private Image blocked;

    public WoodenBlock(Layer layer, Point location, boolean collision) {
        super(layer, location, collision);
        setTileName("WoodenBlock");
    }

    @Override
    public void initializeImages() {
        try {
            blocked = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\blocks\\block1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image[] getSprites() {
        Image[] block = new Image[1];
        block[0] = blocked;
        return block;
    }
}
