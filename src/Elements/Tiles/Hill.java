package Elements.Tiles;

import Elements.Layer;
import Elements.Manager;
import Main.Main;
import Settings.Controls;
import java.awt.*;

public class Hill extends Tile implements LayeredTile,Adjustables {

    private final int type;
    private int blocks;
    private double timer = System.nanoTime();

    public Hill(Layer layer, Point location, boolean collision, int type, int blocks) {
        super(layer ,location, collision);
        if(blocks%2==1){
            blocks++;
        }
        this.type = type;
        this.blocks = blocks;
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

    private Image[] getTypeTextures(){
        if(type==1){
            return Main.game.getSpritesLoader().getHill1();
        } else {
            return Main.game.getSpritesLoader().getHill2();
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
        ret[0] = getTypeTextures()[2];
        ret[1] = getTypeTextures()[3];
        for(int i = 2; i+1<ret.length; i++){
            ret[i] = getTypeTextures()[0];
            ret[i+1] = getTypeTextures()[1];
        }

        return ret;
    }

    public Image[][] get2DSprites(){
        Image[][] ret = new Image[(2+blocks)/2][2];
        ret[0][0] = getTypeTextures()[2];
        ret[0][1] = getTypeTextures()[3];
        for(int i = 1; i<ret.length; i++){
            ret[i][0] = getTypeTextures()[0];
            ret[i][1] = getTypeTextures()[1];
        }
        return ret;
    }

    public String toString() {
        return super.toString()+type+","+blocks+",";
    }

}
