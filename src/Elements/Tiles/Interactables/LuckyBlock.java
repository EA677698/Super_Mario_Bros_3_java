package Elements.Tiles.Interactables;

import Elements.Entities.Entity;
import Elements.Manager;
import Elements.Layer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LuckyBlock extends Interactable {

    Image[] blocks;
    @SuppressWarnings("CanBeFinal")
    Entity containedEntity;
    private double timer = System.nanoTime();
    private double executeTimer = System.nanoTime();

    public LuckyBlock(Layer layer, Point location, boolean collision, Entity containedEntity) {
        super(layer, location, collision);
        this.containedEntity = containedEntity;
        setTileName("LuckyBlock");
    }

    @Override
    public void tick() {
        super.tick();
        if(!isActivated()){
            if(Manager.player!=null) {
                if (Manager.player.getHitBox().intersects(getHitBox())) {
                    if(getHitBox().outcode(Manager.player.getHitBox().getCenterX(),Manager.player.getHitBox().getCenterY())==8){
                        executeOnTouch();
                    }
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
        if(containedEntity != null){
            containedEntity.setLocation(new Point(getLocation().x,getLocation().y));
            Manager.ents.add(containedEntity);
            if(System.nanoTime()-executeTimer>100000000){
                containedEntity.changeLayer(Layer.MIDDLE_LAYER);
                containedEntity.addY(-1);
                executeTimer = System.nanoTime();
            }
        }
    }

    @Override
    public void executeOnAction() {

    }

    @Override
    public void initializeImages() {
        blocks = new Image[5];
        for(int i = 0; i<blocks.length; i++){
            try {
                blocks[i] = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\blocks\\lb"+(i+1)+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return super.toString()+containedEntity;
    }

    @Override
    public Image[] getSprites() {
        return blocks;
    }

}
