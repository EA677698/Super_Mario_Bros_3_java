package Elements.Entities.NotLiving;

import Elements.Manager;
import Elements.Entities.Mario.Player;
import Elements.Layer;
import Main.Global;
import Sound.SFX;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Coin extends NotLiving {

    private Image[] coins;
    private double timer = System.nanoTime();

    public Coin(Layer layer, Point coordinates, int width, int height, int direction, boolean hasCollision) {
        super(layer, coordinates, width, height, direction, hasCollision);
        setEntityName("Coin");
    }

    public Coin(Layer layer, Point coordinates, int width, int height, int life, int damage, int direction, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, direction, hasGravity, hasCollision);
        setEntityName("Coin");
    }

    public Coin(Layer layer, Point coordinates, int width, int height, int life, int damage, double velocity, double gravity, int direction, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, velocity, gravity, direction, hasGravity, hasCollision);
        setEntityName("Coin");
    }

    @Override
    public void executeUponTouch(Player player) {
        Global.money++;
        SFX.coin.setFramePosition(0);
        SFX.coin.start();
        removeFromLayer();
        Manager.ents.remove(this);
    }

    @Override
    public void death() {

    }

    @Override
    public void initializeImages() {
        coins = new Image[3];
        for(int i = 0; i<coins.length; i++){
            try {
                coins[i] = ImageIO.read(new File(getLocalPath()+"\\assets\\Entities\\Coin\\"+(i+1)+".png"));
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    @Override
    public Image[] getImages() {
        return coins;
    }

    @Override
    public Image getSprite() {
        if(System.nanoTime()-timer>100000000){
            setSprite(getCurrentSprite()+1);
            if(getCurrentSprite()>2){
                setSprite(0);
            }
            timer = System.nanoTime();
        }
        return coins[getCurrentSprite()];
    }
}
