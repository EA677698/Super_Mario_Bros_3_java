package Elements.Tiles.Interactables;

import Elements.Layer;
import Elements.Manager;
import Elements.Tiles.Adjustables;
import Elements.Tiles.LayeredTile;
import Settings.Controls;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Pipes extends Interactable implements LayeredTile, Adjustables {

    private int blocks;
    private double timer = System.nanoTime();
    private Pipes connectionPipe;
    private Image top1,top2,bottom1,bottom2;

    public Pipes(Layer layer, Point location, boolean collision, int blocks) {
        super(layer, location, collision);
        this.blocks = blocks;
        setTileName("Pipe");
        initializeImages();
        this.setWidth(120);
        this.setHeight(((blocks/2)+1)*60);
    }

    @Override
    public void tick() {
        super.tick();
        executeOnAction();
        if(Manager.selectedTile==this){
            changeHeight();
            changeLength();
        }
    }

    @Override
    public void executeOnTouch() {

    }

    public void connectToPipe(Pipes pipes){
        connectionPipe = pipes;
    }

    @Override
    public void executeOnAction() {
        if(connectionPipe!=null){
            if(Manager.player.getHitBox().intersects(this.getHitBox())){
                if(Manager.player.getLocation().y<this.getLocation().y){
                    if(Controls.down){
                        Manager.player.setLocation(new Point(connectionPipe.getLocation().x,connectionPipe.getLocation().y));
                    }
                }
            }
        }
    }

    @Override
    public void initializeImages() {
        try {
            top1 = ImageIO.read(new File(getLocalPath() + "\\assets\\Tiles\\pipes\\1.png"));
            top2 = ImageIO.read(new File(getLocalPath() + "\\assets\\Tiles\\pipes\\2.png"));
            bottom1 = ImageIO.read(new File(getLocalPath() + "\\assets\\Tiles\\pipes\\3.png"));
            bottom2 = ImageIO.read(new File(getLocalPath() + "\\assets\\Tiles\\pipes\\4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image[] getSprites() {
        Image[] ret = new Image[2+blocks];
        ret[0] = top1;
        ret[1] = top2;
        for(int i = 2; i+1<ret.length; i++){
            ret[i] = bottom1;
            ret[i+1] = bottom2;
        }

        return ret;
    }

    @Override
    public Image[][] get2DSprites() {
        Image[][] ret = new Image[(2+blocks)/2][2];
        ret[0][0] = top1;
        ret[0][1] = top2;
        for(int i = 1; i<ret.length; i++){
            ret[i][0] = bottom1;
            ret[i][1] = bottom2;
        }
        return ret;
    }

    @Override
    public void changeLength() {

    }

    @Override
    public void changeHeight() {
        if(Controls.shift&&System.nanoTime()-timer>100000000){
            if(Controls.down){
                blocks+=2;
            } else if(blocks>=2&&Controls.up){
                blocks-=2;
            }
            timer = System.nanoTime();
            this.setHeight(((blocks/2)+1)*60);
        }
    }

    @Override
    public String toString() {
        return super.toString()+blocks+",";
    }
}
