package Elements.Tiles;

import Elements.Layer;
import Main.Main;
import Settings.Controls;
import java.awt.*;

public class BigBlocks extends Tile implements Adjustables, LayeredTile {

    private int type;
    private double timerX = System.nanoTime();
    private double timerY = System.nanoTime();
    private int extraYLayers;
    private int extraXLayers;

    public BigBlocks(Layer layer, Point location, boolean collision, int type, int rows, int columns) {
        super(layer, location, collision);
        this.type = type;
        extraYLayers = columns;
        extraXLayers = rows;
        setTileName("BigBlock");
        calculateTileLayers();
    }

    @Override
    public void tick() {
        super.tick();
        if(Main.game.getManager().getSelectedTile()==this){
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
            setWidth(180+(60*extraYLayers));
            calculateTileLayers();
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
            setHeight(180+(60*extraXLayers));
            calculateTileLayers();
            timerY = System.nanoTime();
        }
    }

    private Image[] getTypeTextures(){
        switch (type){
            case 1: return Main.game.getSpritesLoader().getBb();
            case 2: return Main.game.getSpritesLoader().getBp();
            case 3: return Main.game.getSpritesLoader().getBw();
            default: return Main.game.getSpritesLoader().getBg();
        }
    }

    @Override
    public Image[] getSprites() {
        return null;
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
    public void calculateTileLayers() {
        Image[][] ret = new Image[3+extraXLayers][3];
        int index = 1;
        ret[0] = new Image[]{getTypeTextures()[0], getTypeTextures()[1], getTypeTextures()[2]};
        for(int i = 0; i<extraXLayers+1; i++){
            ret[index] = new Image[]{getTypeTextures()[3], getTypeTextures()[4], getTypeTextures()[5]};
            index++;
        }
        ret[ret.length-1] = new Image[]{getTypeTextures()[6], getTypeTextures()[7], getTypeTextures()[8]};;
        Image[][] tile = new Image[ret.length][ret[0].length+extraYLayers];
        for(int i = 0; i<ret.length; i++){
            for(int e = tile[0].length-1; e>-1; e--){
                if(e == tile[0].length-1){
                    tile[i][tile[0].length-1] = ret[i][ret[0].length-1];
                } else {
                    tile[i][e] = ret[i][1];
                }
                if(e == 0){
                    tile[i][e] = ret[i][0];
                }
            }
        }
        Main.game.getManager().getTileLayouts().put(getUUID(),tile);
    }

    @Override
    public Image[][] get2DSprites() {
        if (!Main.game.getManager().getTileLayouts().containsKey(getUUID())) {
            calculateTileLayers();
        }
        return Main.game.getManager().getTileLayouts().get(getUUID());
    }

    public String toString() {
        return super.toString()+type+","+getRows()+","+getColumns()+",";
    }

}
