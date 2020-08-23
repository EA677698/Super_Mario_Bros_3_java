package Elements.Tiles;

import Elements.Layer;
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
            calculateTileLayers();
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
        return null;
    }

    @Override
    public void calculateTileLayers() {
        Image[][] tile = new Image[(2+blocks)/2][2];
        tile[0][0] = getTypeTextures()[2];
        tile[0][1] = getTypeTextures()[3];
        for(int i = 1; i<tile.length; i++){
            tile[i][0] = getTypeTextures()[0];
            tile[i][1] = getTypeTextures()[1];
        }
        Main.game.getManager().getTileLayouts().put(getUUID(),tile);
    }

    public Image[][] get2DSprites(){
        if (!Main.game.getManager().getTileLayouts().containsKey(getUUID())) {
            calculateTileLayers();
        }
        return Main.game.getManager().getTileLayouts().get(getUUID());
    }

    public String toString() {
        return super.toString()+type+","+blocks+",";
    }

}
