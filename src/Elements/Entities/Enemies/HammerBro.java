package Elements.Entities.Enemies;

import Elements.Entities.NotLiving.Fireball;
import Elements.Manager;
import Elements.Layer;
import Main.Main;
import java.awt.*;

public class HammerBro extends Enemy {

    private int type;
    private double timer = System.nanoTime();
    private double attackTimer = System.nanoTime();

    public HammerBro(Layer layer, Point coordinates, int width, int height, int direction, boolean hasCollision, int type) {
        super(layer, coordinates, width, height, direction, hasCollision);
        this.type = type;
        setEntityName("HammerBro");
    }

    public HammerBro(Layer layer, Point coordinates, int width, int height, int life, int damage, int direction, boolean hasGravity, boolean hasCollision, int type) {
        super(layer, coordinates, width, height, life, damage, direction, hasGravity, hasCollision);
        this.type = type;
        setEntityName("HammerBro");
    }

    public HammerBro(Layer layer, Point coordinates, int width, int height, int life, int damage, double velocity, double gravity, int direction, boolean hasGravity, boolean hasCollision, int type) {
        super(layer, coordinates, width, height, life, damage, velocity, gravity, direction, hasGravity, hasCollision);
        this.type = type;
        setEntityName("HammerBro");
    }


    @Override
    public void tick() {
        super.tick();
        if(System.nanoTime()-timer>200000000){
            if(getCurrentSprite()>0){
                setSprite(-1);
            }
            if(System.nanoTime()-attackTimer>2000000000){
                if(isFacingEntity(Manager.player)){
                    attack();
                } else {
                    setDirection(getDirection()*-1);
                }
                attackTimer = System.nanoTime();
            }
            setSprite(getCurrentSprite()+1);
            timer = System.nanoTime();
        }
    }

    public void attack(){
        if(type==3){
            setSprite(1);
            Manager.ents.add(new Fireball(Layer.MIDDLE_LAYER,getLocation(),40,40,getDirection(),false));
        }
    }

    @Override
    public Image[] getImages() {
        switch (type){
            case 2: return Main.game.getSpritesLoader().getHammerBoomerangBro();
            case 3: return Main.game.getSpritesLoader().getHammerFireBro();
            default: return Main.game.getSpritesLoader().getHammerBro();
        }
    }

    @Override
    public void death() {
        super.death();
    }

    @Override
    public String toString() {
        return super.toString()+type+",";
    }

    @Override
    public Image getSprite() {
        return getImages()[getCurrentSprite()];
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
