package Elements.Entities.Enemies;

import Elements.Entities.Entity;
import Elements.Layer;
import Elements.Manager;

import java.awt.*;

public abstract class Enemy extends Entity {

    public Enemy(Layer layer, Point coordinates, int width, int height, int direction, boolean hasCollision) {
        super(layer, coordinates, width, height, hasCollision);
        this.setDirection(direction);
    }

    public Enemy(Layer layer, Point coordinates, int width, int height, int life, int damage,int direction, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, hasGravity, hasCollision);
        this.setDirection(direction);
    }

    public Enemy(Layer layer, Point coordinates, int width, int height, int life, int damage, double velocity, double gravity, int direction, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, velocity, gravity, hasGravity, hasCollision);
        this.setDirection(direction);
    }

    @Override
    public void death() {
        setDead(true);
        removeFromLayer();
        Manager.ents.remove(this);
    }

    @Override
    public void initializeImages() {

    }

    @Override
    public Image getSprite() {
        if(getBaseSprite()!=null){
            return getBaseSprite();
        }
        return null;
    }

        @Override
    public String toString() {
        return getEntityName()+","+getLayer()+","+getLocation().x+","+getLocation().y+","+getWidth()+","+getHeight()+","+
                getLife()+","+getDamage()+","+getVelocity()+","+getGravity()+","+getDirection()+","+isHasGravity()+","+isCollision()+",";
    }
}
