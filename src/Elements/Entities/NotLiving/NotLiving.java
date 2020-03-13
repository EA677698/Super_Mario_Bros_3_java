package Elements.Entities.NotLiving;

import Elements.Entities.Entity;
import Elements.Entities.Mario.Player;
import Elements.Layer;

import java.awt.*;

public abstract class NotLiving extends Entity {

    public NotLiving(Layer layer, Point coordinates, int width, int height, int direction, boolean hasCollision) {
        super(layer, coordinates, width, height, hasCollision);
        this.setDirection(direction);
    }

    public NotLiving(Layer layer, Point coordinates, int width, int height, int life, int damage, int direction, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, hasGravity, hasCollision);
        this.setDirection(direction);
    }

    public NotLiving(Layer layer, Point coordinates, int width, int height, int life, int damage, double velocity, double gravity, int direction, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, velocity, gravity, hasGravity, hasCollision);
        this.setDirection(direction);
    }

    public abstract void executeUponTouch(Player player);

    @Override
    public Image getSprite() {
        if(getBaseSprite()!=null){
            return getBaseSprite();
        }
        return null;
    }

    public String toString(){
        return getEntityName()+","+getLayer()+","+getLocation().x+","+getLocation().y+","+getWidth()+","+getHeight()+","+getLife()+","+getDamage()+
                ","+ getXVelocity() +","+getGravity()+","+getDirection()+","+isHasGravity()+","+isCollision()+",";
    }
}
