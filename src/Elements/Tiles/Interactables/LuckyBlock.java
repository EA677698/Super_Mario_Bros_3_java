package Elements.Tiles.Interactables;

import Elements.Entities.Entity;
import Elements.Manager;
import Elements.Layer;
import Main.Main;
import java.awt.*;

public class LuckyBlock extends Interactable {

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
            if(Main.game.getManager().getPlayer()!=null) {
                if (Main.game.getManager().getPlayer().getHitBox().intersects(getHitBox())) {
                    if(getHitBox().outcode(Main.game.getManager().getPlayer().getHitBox().getCenterX(),Main.game.getManager().getPlayer().getHitBox().getCenterY())==8){
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
            Main.game.getManager().getEnts().add(containedEntity);
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
    public String toString() {
        return super.toString()+containedEntity;
    }

    @Override
    public Image[] getSprites() {
        return Main.game.getSpritesLoader().getLuckyBlocks();
    }

}
