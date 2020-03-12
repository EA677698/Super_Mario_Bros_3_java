package Elements.Tiles;

import Elements.Layer;
import Elements.Manager;
import Settings.Controls;

import java.awt.*;

public class BigBlocks extends Tile implements Adjustables, LayeredTile {

    private int type;
    private double timerX = System.nanoTime();
    private double timerY = System.nanoTime();
    private int extraYLayers;
    private int extraXLayers;
    Image[] begin;
    Image[] middle;
    Image[] end;

    public BigBlocks(Layer layer, Point location, boolean collision, int type, int rows, int columns) {
        super(layer, location, collision);
        this.type = type;
        extraYLayers = columns;
        extraXLayers = rows;
        setTileName("BigBlock");
        initializeImages();
    }

    @Override
    public void tick() {
        super.tick();
        if(Manager.selectedTile==this){
            changeHeight();
            changeLength();
        }
    }

    @Override
    public void changeLength() {
        if(Controls.shift&&System.nanoTime()-timerX>100000000){
            if(Controls.right){
                extraYLayers++;
            } else if(Controls.left&&extraYLayers>-1){
                extraYLayers--;
            }
            timerX = System.nanoTime();
        }
    }

    @Override
    public void changeHeight() {
        if(Controls.shift&&System.nanoTime()-timerY>100000000){
            if(Controls.down){
                extraXLayers++;
            } else if(Controls.up){
                if(extraXLayers>-1){
                    extraXLayers--;
                }
            }
            timerY = System.nanoTime();
        }
    }

    @Override
    public void initializeImages() {
        String color;
        switch (type){
            case 1: color = "b";
            break;
            case 2: color = "p";
            break;
            case 3: color = "w";
            break;
            default: color = "g";
        }
        begin = new Image[3];
        middle = new Image[3];
        end = new Image[3];
        for(int i = 0; i<3; i++){
            begin[i] = createSprite(getLocalPath()+"\\assets\\Tiles\\blocks\\b"+color+(i+1)+"-"+1+".png");
            middle[i] = createSprite(getLocalPath()+"\\assets\\Tiles\\blocks\\b"+color+(i+1)+"-"+2+".png");
            end[i] = createSprite(getLocalPath()+"\\assets\\Tiles\\blocks\\b"+color+(i+1)+"-"+3+".png");
        }
    }

    @Override
    public Image[] getSprites() {
        return new Image[0];
    }

    public int getType(){
        return type;
    }

    public int getRows(){
        return extraXLayers;
    }

    public int getColumns(){
        return extraYLayers;
    }

    @Override
    public Image[][] get2DSprites() {
        setWidth(180+(60*extraYLayers));
        setHeight(180+(60*extraXLayers));
        Image[][] ret = new Image[3+extraXLayers][3];
        int index = 1;
        ret[0] = begin;
        for(int i = 0; i<extraXLayers+1; i++){
            ret[index] = middle;
            index++;
        }
        ret[ret.length-1] = end;
        Image[][] retFinal = new Image[ret.length][ret[0].length+extraYLayers];
        for(int i = 0; i<ret.length; i++){
            for(int e = retFinal[0].length-1; e>-1; e--){
                if(e == retFinal[0].length-1){
                    retFinal[i][retFinal[0].length-1] = ret[i][ret[0].length-1];
                } else {
                    retFinal[i][e] = ret[i][1];
                }
                if(e == 0){
                    retFinal[i][e] = ret[i][0];
                }
            }
        }
        return retFinal;
    }

    public String toString() {
        return super.toString()+type+","+getRows()+","+getColumns()+",";
    }

}
