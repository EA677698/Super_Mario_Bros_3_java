package Elements.Tiles;

import Elements.Layer;
import Elements.Manager;
import Main.Global;
import Settings.Controls;

import java.awt.*;

public class Floor extends Tile implements Adjustables, LayeredTile{

    private double timerX = System.nanoTime();
    private double timerY = System.nanoTime();
    private int middleBlocks, layers;
    private Image startBlock1, middleBlock1, endBlocks1, startBlock2, middleBlock2, endBlocks2;

    public Floor(Layer layer, Point location, boolean collision, int middleBlocks, int layers) {
        super(layer, location, collision);
        this.middleBlocks = middleBlocks;
        this.layers = layers;
        this.setTileName("Platform");
        this.setWidth(120+(middleBlocks*60));
        setHitBox(new Rectangle(getWidth(),getHeight()));
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
        if(Controls.shift&&System.nanoTime()- timerX >100000000){
            if(Controls.right){
                middleBlocks++;
            } else if(middleBlocks>=1&&Controls.left){
                middleBlocks--;
            }
            timerX = System.nanoTime();
            this.setWidth(120+(middleBlocks*60));
        }
    }

    @Override
    public void changeHeight() {
        if(Controls.shift&&System.nanoTime()- timerY >100000000){
            if(Controls.down){
                layers++;
            } else if(layers>=1&&Controls.up){
                layers--;
            }
            timerY = System.nanoTime();
            this.setHeight(60+(layers*60));
        }
    }

    @Override
    public void initializeImages() {
        startBlock1 = createSprite(Global.localPath+"\\assets\\Tiles\\platforms\\1-1.png");
        middleBlock1 = createSprite(Global.localPath+"\\assets\\Tiles\\platforms\\1-2.png");
        endBlocks1 = createSprite(Global.localPath+"\\assets\\Tiles\\platforms\\1-3.png");
        startBlock2 = createSprite(Global.localPath+"\\assets\\Tiles\\platforms\\1-4.png");
        middleBlock2 = createSprite(Global.localPath+"\\assets\\Tiles\\platforms\\1-5.png");
        endBlocks2 = createSprite(Global.localPath+"\\assets\\Tiles\\platforms\\1-6.png");
    }

    @Override
    public Image[] getSprites() {
        Image[] blocks = new Image[middleBlocks+2];
        blocks[0] = startBlock1;
        for(int i = 1; i<blocks.length-1; i++){
            blocks[i] = middleBlock1;
        }
        blocks[blocks.length-1] = endBlocks1;
        return blocks;
    }


    @Override
    public Image[][] get2DSprites() {
        Image[][] ret = new Image[1+layers][2+middleBlocks];
        ret[0][0] = startBlock1;
        for(int i = 1; i<ret[0].length-1; i++){
            ret[0][i] = middleBlock1;
        }
        ret[0][ret[0].length-1] = endBlocks1;
        for(int i = 1; i<ret.length; i++){
            for(int e = 0; e<ret[0].length; e++){
                if(e==0){
                    ret[i][e] = startBlock2;
                } else if(e==ret[0].length-1){
                    ret[i][e] = endBlocks2;
                } else {
                    ret[i][e] = middleBlock2;
                }
            }
        }
        return ret;
    }

    public String toString() {
        return super.toString()+middleBlocks+","+layers+",";
    }
}
