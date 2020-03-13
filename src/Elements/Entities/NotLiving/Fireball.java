package Elements.Entities.NotLiving;

import Elements.Entities.Mario.Player;
import Elements.Entities.Mario.Powers;
import Elements.Layer;
import Sound.BGM;
import Sound.SFX;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fireball extends NotLiving {

    private Image[] fireballs;
    private double timer = System.nanoTime();
    private double moveTimer = System.nanoTime();
    private double hitTimer = System.nanoTime();

    public Fireball(Layer layer, Point coordinates, int width, int height, int direction, boolean hasCollision) {
        super(layer, coordinates, width, height, direction, hasCollision);
        this.setHasGravity(false);
        setEntityName("Fireball");
    }

    @Override
    public void executeUponTouch(Player player) {
        if(player!=null){
            if(System.nanoTime()-hitTimer>2000000000){
                if(player.getHitBox().intersects(getHitBox())){
                    switch (player.getPower()){
                        case SMALL: player.setDead(true);
                            BGM.level1.stop();
                            SFX.down1.setFramePosition(0);
                            SFX.down1.start();
                            break;
                        case BIG: player.setPower(Powers.SMALL);
                            SFX.pipe.setFramePosition(0);
                            SFX.pipe.start();
                            break;
                        default: player.setPower(Powers.BIG);
                    }
                    hitTimer = System.nanoTime();
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(System.nanoTime()-timer>100000000){
            if(getCurrentSprite()>=3){
                setSprite(0);
            }
            setSprite(getCurrentSprite()+1);
            timer = System.nanoTime();
        }
        if(System.nanoTime()-moveTimer>10000000){
            if(getDirection()==1){
                addX(5);
            } else if(getDirection()==-1) {
                addX(-5);
            }
            moveTimer = System.nanoTime();
        }
    }

    @Override
    public void death() {

    }

    @Override
    public Image[] getImages() {
        return fireballs;
    }

    @Override
    public void initializeImages() {
        fireballs = new Image[4];
        for(int i = 0; i<4; i++){
            try {
                fireballs[i] = ImageIO.read(new File(getLocalPath()+"\\assets\\Entities\\Minions\\Fireball\\"+(i+1)+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Image getSprite() {
        return fireballs[getCurrentSprite()];
    }
}
