package Elements.Tiles.Tools;

import Elements.Layer;
import Elements.Tiles.Adjustables;
import Elements.Tiles.LayeredTile;
import Elements.Tiles.Tile;
import Settings.Controls;
import Elements.Manager;
import java.awt.*;

public class Tool extends Tile implements Adjustables, LayeredTile {

    private int middleBlocks, layers;
    private double timerX = System.nanoTime();
    private double timerY = System.nanoTime();

    public Tool(Layer layer, Point location, boolean collision, int middleBlocks, int layers) {
        super(layer, location, collision);
        this.layers = layers;
        this.middleBlocks = middleBlocks;
        this.setWidth(60+(middleBlocks*60));
        setHitBox(new Rectangle(getWidth(),getHeight()));
    }

    public void tick(){
        super.tick();
        if(Manager.selectedTile==this){
            changeHeight();
            changeLength();
        }
    }


    @Override
    public Image[] getSprites() {
        return new Image[0];
    }

    public void changeLength(){
        if(Controls.shift&&System.nanoTime()- timerX >100000000){
            if(Controls.right){
                middleBlocks++;
            } else if(middleBlocks>=1&&Controls.left){
                middleBlocks--;
            }
            timerX = System.nanoTime();
            setWidth(60+(middleBlocks*60));
        }
    }

    public void changeHeight() {
        if(Controls.shift&&System.nanoTime()- timerY >100000000){
            if(Controls.down){
                layers++;
            } else if(layers>=2&&Controls.up){
                layers--;
            }
            timerY = System.nanoTime();
            setHeight(layers*60);
        }
    }

    @Override
    public Image[][] get2DSprites() {
        return new Image[0][];
    }

    public String toString() {
        return super.toString()+middleBlocks+","+layers+",";
    }

}
