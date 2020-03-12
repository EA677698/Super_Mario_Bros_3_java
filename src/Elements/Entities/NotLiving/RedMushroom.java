package Elements.Entities.NotLiving;

import Elements.Manager;
import Elements.Entities.Mario.Player;
import Elements.Entities.Mario.Powers;
import Elements.Layer;
import Sound.SFX;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RedMushroom extends NotLiving {

    private Image red;

    public RedMushroom(Layer layer, Point coordinates, int width, int height, int direction, boolean hasCollision) {
        super(layer, coordinates, width, height, direction,hasCollision);
        setEntityName("RedMushroom");
    }

    public RedMushroom(Layer layer, Point coordinates, int width, int height, int life, int damage, int direction, boolean hasGravity,boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, direction, hasGravity,hasCollision);
        setEntityName("RedMushroom");
    }

    public RedMushroom(Layer layer, Point coordinates, int width, int height, int life, int damage, double velocity, double gravity, int direction, boolean hasGravity,boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, velocity, gravity, direction, hasGravity,hasCollision);
        setEntityName("RedMushroom");
    }

    @Override
    public void executeUponTouch(Player player) {
        player.setPower(Powers.BIG);
        SFX.powerUp.setFramePosition(0);
        SFX.powerUp.start();
        removeFromLayer();
        Manager.ents.remove(this);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void death() {

    }

    @Override
    public void initializeImages() {
        try {
            red = ImageIO.read(new File(getLocalPath()+"\\assets\\Entities\\PowerUps\\bigMushroom.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image getSprite() {
        return red;
    }
}
