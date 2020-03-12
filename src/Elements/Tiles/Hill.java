package Elements.Tiles;

import Elements.Layer;
import Elements.Manager;
import Settings.Controls;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Hill extends Tile implements LayeredTile,Adjustables {

    private final int type;
    private int blocks;
    private Image top1,top2,bottom1,bottom2;
    private double timer = System.nanoTime();

    public Hill(Layer layer, Point location, boolean collision, int type, int blocks) {
        super(layer ,location, collision);
        if(blocks%2==1){
            blocks++;
        }
        this.type = type;
        this.blocks = blocks;
        initializeImages();
        setTileName("Hill");
        this.setWidth(120);
        this.setHeight(((blocks/2)+1)*60);
    }

    @Override
    public void tick() {
        super.tick();
        if(Manager.selectedTile==this){
            changeHeight();
            changeLength();
        }
    }

    public void changeLength(){
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
    public void initializeImages() {
        if(type==1){
            try {
                top1 = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\hills\\1-3.png"));
                top2 = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\hills\\1-4.png"));
                bottom1 = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\hills\\1-1.png"));
                bottom2 = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\hills\\1-2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type==2){
            try {
                top1 = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\hills\\2-3.png"));
                top2 = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\hills\\2-4.png"));
                bottom1 = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\hills\\2-1.png"));
                bottom2 = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\hills\\2-2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getType(){
        return type;
    }

    public int getBlocks(){
        return blocks;
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

    public Image[][] get2DSprites(){
        Image[][] ret = new Image[(2+blocks)/2][2];
        ret[0][0] = top1;
        ret[0][1] = top2;
        for(int i = 1; i<ret.length; i++){
            ret[i][0] = bottom1;
            ret[i][1] = bottom2;
        }
        return ret;
    }

    public String toString() {
        return super.toString()+type+","+blocks+",";
    }

}
