package Elements.Entities.Enemies;
import Elements.Layer;
import Main.Main;
import java.awt.*;

public class KoopaTroopa extends Enemy {

    private double timer = System.nanoTime();
    private double timer2 = System.nanoTime();

    public KoopaTroopa(Layer layer, Point coordinates, int width, int height, int direction, boolean hasCollision) {
        super(layer, coordinates, width, height,direction, hasCollision);
        setEntityName("KoopaTroopa");
        setDirection(direction);
        setXVelocity(3);
    }

    public KoopaTroopa(Layer layer, Point coordinates, int width, int height, int life, int damage, int direction, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage,direction,hasGravity, hasCollision);
        setEntityName("KoopaTroopa");
        setDirection(direction);
        setXVelocity(3);
    }

    public KoopaTroopa(Layer layer, Point coordinates, int width, int height, int life, int damage, double velocity, double gravity, int direction, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, velocity, gravity,direction,hasGravity, hasCollision);
        setEntityName("KoopaTroopa");
        setDirection(direction);
        setXVelocity(3);
    }

    @Override
    public void tick() {
        super.tick();
        if(isAllowedMoving()){
            if(System.nanoTime()-timer>10000000){
                if(getDirection()==1){
                    addX(3);
                } else if(getDirection()==-1) {
                    addX(-3);
                }
                timer = System.nanoTime();
            }
        } else{
            setDirection(getDirection()*-1);
        }
    }

    @Override
    public void death() {

    }

    @Override
    public Image[] getImages() {
        return Main.game.getSpritesLoader().getKoopaTroopa();
    }

    @Override
    public Image getSprite() {
        if(System.nanoTime()-timer2 >100000000){
            if(getCurrentSprite()==1){
                setSprite(0);
            } else{
                setSprite(1);
            }
            timer2 = System.nanoTime();
        }
        return Main.game.getSpritesLoader().getKoopaTroopa()[getCurrentSprite()];
    }
}
