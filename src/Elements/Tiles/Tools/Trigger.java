package Elements.Tiles.Tools;

import Elements.Layer;
import Elements.Manager;

import java.awt.*;

public class Trigger extends Tool {

    String Command;
    int MAX_ACTIVATIONS;
    int activations = 0;
    boolean stillInContact;
    double timer = System.nanoTime();

    public Trigger(Layer layer, Point location, int middleBlocks, int layers, String command, int activations) {
        super(layer, location, false, middleBlocks, layers);
        setTileName("Trigger");
        MAX_ACTIVATIONS = activations;
        Command = command;
    }


    @Override
    public void tick() {
        super.tick();
        if(MAX_ACTIVATIONS!=0&&activations>=MAX_ACTIVATIONS){
            Manager.tiles.remove(this);
        }
        if(System.nanoTime()-timer>80000000){
            if(Manager.player!=null&&Manager.player.getHitBox().intersects(getHitBox())){
                if(!stillInContact){
                    Manager.commandInput(Command.toLowerCase());
                    activations++;
                    stillInContact = true;
                }
            } else {
                stillInContact = false;
            }
            timer = System.nanoTime();
        }
    }

    @Override
    public String toString() {
        return super.toString()+Command+","+MAX_ACTIVATIONS+",";
    }
}
