package Elements.Entities.NotLiving;

import Elements.Manager;
import Elements.Entities.Mario.Player;
import Elements.Layer;
import Main.Global;
import Sound.SFX;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GreenMushroom extends NotLiving {

    private Image green;

    public GreenMushroom(Layer layer, Point coordinates, int width, int height, int direction, boolean hasCollision) {
        super(layer, coordinates, width, height, direction, hasCollision);
        setEntityName("GreenMushroom");
    }

    public GreenMushroom(Layer layer,Point coordinates, int width, int height, int life, int damage, int direction, boolean hasGravity,boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, direction, hasGravity, hasCollision);
        setEntityName("GreenMushroom");
    }

    public GreenMushroom(Layer layer, Point coordinates, int width, int height, int life, int damage, double velocity, double gravity, int direction, boolean hasGravity,boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, velocity, gravity, direction, hasGravity,hasCollision);
        setEntityName("GreenMushroom");
    }

    @Override
    public void executeUponTouch(Player player) {
        Global.lives++;
        SFX.up1.setFramePosition(0);
        SFX.up1.start();
        removeFromLayer();
        Manager.ents.remove(this);
    }

    @Override
    public void death() {}

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void initializeImages() {
        try {
            green = ImageIO.read(new File(getLocalPath()+"\\assets\\Entities\\PowerUps\\1up.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image getSprite() {
        return green;
    }
}
