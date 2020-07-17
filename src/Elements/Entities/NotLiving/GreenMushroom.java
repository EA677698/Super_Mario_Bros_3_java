package Elements.Entities.NotLiving;

import Elements.Manager;
import Elements.Entities.Mario.Player;
import Elements.Layer;
import Main.Global;
import Main.Main;
import Sound.SFX;
import java.awt.*;

public class GreenMushroom extends NotLiving {

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
    public Image getSprite() {
        return Main.game.getSpritesLoader().getMushrooms()[0];
    }
}
