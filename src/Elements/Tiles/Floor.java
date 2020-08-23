package Elements.Tiles;

import Elements.Layer;
import Main.Main;
import Settings.Controls;

import java.awt.*;

public class Floor extends Tile implements Adjustables, LayeredTile{

    private double timerX = System.nanoTime();
    private double timerY = System.nanoTime();
    private int middleBlocks, layers;

    public Floor(Layer layer, Point location, boolean collision, int middleBlocks, int layers) {
        super(layer, location, collision);
        this.middleBlocks = middleBlocks;
        this.layers = layers;
        this.setTileName("Platform");
        this.setWidth(120+(middleBlocks*60));
        setHitBox(new Rectangle(getWidth(),getHeight()));
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
        if(Controls.shift&&System.nanoTime()- timerX >100000000){
            if(Controls.right){
                middleBlocks++;
            } else if(middleBlocks>=1&&Controls.left){
                middleBlocks--;
            }
            timerX = System.nanoTime();
            this.setWidth(120+(middleBlocks*60));
            calculateTileLayers();
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
            calculateTileLayers();
        }
    }

    @Override
    public Image[] getSprites() {
        return null;
    }


    @Override
    public void calculateTileLayers() {
        Image[][] tile = new Image[1+layers][2+middleBlocks];
        tile[0][0] = Main.game.getSpritesLoader().getFloor()[0];
        for(int i = 1; i<tile[0].length-1; i++){
            tile[0][i] = Main.game.getSpritesLoader().getFloor()[1];
        }
        tile[0][tile[0].length-1] = Main.game.getSpritesLoader().getFloor()[2];
        for(int i = 1; i<tile.length; i++){
            for(int e = 0; e<tile[0].length; e++){
                if(e==0){
                    tile[i][e] = Main.game.getSpritesLoader().getFloor()[3];
                } else if(e==tile[0].length-1){
                    tile[i][e] = Main.game.getSpritesLoader().getFloor()[5];
                } else {
                    tile[i][e] = Main.game.getSpritesLoader().getFloor()[4];
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
        return super.toString()+middleBlocks+","+layers+",";
    }
}
