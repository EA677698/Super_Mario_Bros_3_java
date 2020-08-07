package Elements.Tiles.Interactables;

import Elements.Layer;
import Elements.Manager;
import Elements.Tiles.Adjustables;
import Elements.Tiles.LayeredTile;
import Main.Main;
import Settings.Controls;
import java.awt.*;

public class Pipes extends Interactable implements LayeredTile, Adjustables {

    private int blocks;
    private double timer = System.nanoTime();
    private Pipes connectionPipe;

    public Pipes(Layer layer, Point location, boolean collision, int blocks) {
        super(layer, location, collision);
        this.blocks = blocks;
        setTileName("Pipe");
        this.setWidth(120);
        this.setHeight(((blocks/2)+1)*60);
    }

    @Override
    public void tick() {
        super.tick();
        executeOnAction();
        if(Main.game.getManager().getSelectedTile()==this){
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
            if(Main.game.getManager().getPlayer().getHitBox().intersects(this.getHitBox())){
                if(Main.game.getManager().getPlayer().getLocation().y<this.getLocation().y){
                    if(Controls.down){
                        Main.game.getManager().getPlayer().setLocation(new Point(connectionPipe.getLocation().x,connectionPipe.getLocation().y));
                    }
                }
            }
        }
    }

    @Override
    public Image[] getSprites() {
        Image[] ret = new Image[2+blocks];
        ret[0] = Main.game.getSpritesLoader().getPipes()[0];
        ret[1] = Main.game.getSpritesLoader().getPipes()[1];
        for(int i = 2; i+1<ret.length; i++){
            ret[i] = Main.game.getSpritesLoader().getPipes()[2];
            ret[i+1] = Main.game.getSpritesLoader().getPipes()[3];
        }

        return ret;
    }

    @Override
    public Image[][] get2DSprites() {
        Image[][] ret = new Image[(2+blocks)/2][2];
        ret[0][0] = Main.game.getSpritesLoader().getPipes()[0];
        ret[0][1] = Main.game.getSpritesLoader().getPipes()[1];
        for(int i = 1; i<ret.length; i++){
            ret[i][0] = Main.game.getSpritesLoader().getPipes()[2];
            ret[i][1] = Main.game.getSpritesLoader().getPipes()[3];
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
