package Elements;

import Graphics.Window;
import Main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public abstract class Elements implements Serializable {

    private Layer layer;
    private Point location;
    private boolean unloaded;

    public Elements(Layer layer, Point location){
        this.layer = layer;
        this.location = location;
        switch (layer){
            case BACK_LAYER:
                Window.screen.layer1.add(this);
                break;
            case MIDDLE_LAYER:
                Window.screen.layer2.add(this);
                break;
            case FRONT_LAYER:
                Window.screen.layer3.add(this);
                break;
        }
        Main.game.getManager().getSavedObjects().add(this);
    }

    public Image createSprite(String url){
        try {
            return ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reloadLayer(){
        switch (layer){
            case BACK_LAYER:
                Window.screen.layer1.add(this);
                break;
            case MIDDLE_LAYER:
                Window.screen.layer2.add(this);
                break;
            case FRONT_LAYER:
                Window.screen.layer3.add(this);
                break;
        }
    }

    public void removeFromLayer(){
        switch (this.layer){
            case BACK_LAYER:
                Window.screen.layer1.remove(this);
                break;
            case MIDDLE_LAYER:
                Window.screen.layer2.remove(this);
                break;
            case FRONT_LAYER:
                Window.screen.layer3.remove(this);
                break;
        }
    }

    public void changeLayer(Layer layer){
        switch (this.layer){
            case BACK_LAYER:
                Window.screen.layer1.remove(this);
                break;
            case MIDDLE_LAYER:
                Window.screen.layer2.remove(this);
                break;
            case FRONT_LAYER:
                Window.screen.layer3.remove(this);
                break;
        }
        this.layer = layer;
        switch (layer){
            case BACK_LAYER:
                Window.screen.layer1.add(this);
                break;
            case MIDDLE_LAYER:
                Window.screen.layer2.add(this);
                break;
            case FRONT_LAYER:
                Window.screen.layer3.add(this);
                break;
        }
    }

    public Layer getLayer() {
        return layer;
    }

    public boolean isUnloaded() {
        return unloaded;
    }

    public void setUnloaded(boolean unloaded) {
        this.unloaded = unloaded;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
