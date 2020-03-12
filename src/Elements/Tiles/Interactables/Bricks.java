package Elements.Tiles.Interactables;

import Elements.Entities.Entity;
import Elements.Manager;
import Elements.Entities.Mario.Powers;
import Elements.Layer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Bricks extends Interactable {

    private Image[] blocks;
    private Entity containedEntity;
    private boolean breakable;
    private double executeTimer = System.nanoTime();
    private double timer = System.nanoTime();

    public Bricks(Layer layer, Point location, boolean collision, Entity containedEntity) {
        super(layer, location, collision);
        this.containedEntity = containedEntity;
        setTileName("Brick");
        this.breakable = false;
    }

    public Bricks(Layer layer, Point location, boolean collision, boolean breakable) {
        super(layer, location, collision);
        setTileName("Brick");
        this.breakable = breakable;
    }

    @Override
    public void tick() {
        super.tick();
        if(!isActivated()){
            if(Manager.player!=null) {
                if (Manager.player.getHitBox().intersects(getHitBox())&&getHitBox().outcode(Manager.player.getHitBox().getCenterX(),Manager.player.getHitBox().getCenterY())==8) {
                    executeOnTouch();
                }
            }
            if(System.nanoTime()-timer>100000000){
                setCurrentSprite(getCurrentSprite()+1);
                if(getCurrentSprite()>3){
                    setCurrentSprite(0);
                }
                timer = System.nanoTime();
            }
        } else {
            setCurrentSprite(4);
        }
    }

    @Override
    public void executeOnTouch() {
        setActivated(true);
        if(breakable&&Manager.player.getPower()== Powers.BIG){
            removeFromLayer();
            Manager.tiles.remove(this);
        }
        if(containedEntity != null){
            containedEntity.setLocation(new Point(getLocation().x,getLocation().y));
            Manager.ents.add(containedEntity);
            if(System.nanoTime()-executeTimer>100000000){
                containedEntity.addY(-1);
                executeTimer = System.nanoTime();
            }
        }
        setCurrentSprite(4);
    }

    @Override
    public void executeOnAction() {

    }

    @Override
    public void initializeImages() {
        blocks = new Image[5];
        try{
            for(int i = 0; i<4; i++){
                blocks[i] = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\blocks\\brick"+(i+1)+".png"));
            }
            blocks[4] = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\blocks\\lb5.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Image[] getSprites() {
        return blocks;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public Entity getContainedEntity() {
        return containedEntity;
    }

    public void setContainedEntity(Entity containedEntity) {
        this.containedEntity = containedEntity;
    }

    @Override
    public String toString() {
        return super.toString()+breakable+",";
    }
}
