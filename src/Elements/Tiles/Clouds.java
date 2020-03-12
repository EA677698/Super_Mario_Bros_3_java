package Elements.Tiles;

import Elements.Layer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Clouds extends Tile implements LayeredTile {

    private Image[] clouds;
    private final int type;

    public Clouds(Layer layer, Point location, boolean collision, int type) {
        super(layer, location, collision);
        this.type = type;
        setTileName("Cloud");
        initializeImages();
    }

    @Override
    public void initializeImages() {
        try{
            if(type==1){
                clouds = new Image[6];
                for(int i = 0; i<6; i++){
                    clouds[i] = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\clouds\\"+type+"-"+(i+1)+".png"));
                    setHeight(120);
                    setWidth(180);
                }
            }
            if(type==2){
                clouds = new Image[9];
                for(int i = 0; i<9; i++){
                    clouds[i] = ImageIO.read(new File(getLocalPath()+"\\assets\\Tiles\\clouds\\"+type+"-"+(i+1)+".png"));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Image[] getSprites() {
        return clouds;
    }

    @Override
    public Image[][] get2DSprites() {
        Image[][] ret = new Image[2][3];
        int index = 0;
        for(int i = 0; i<ret.length; i++){
            for(int e = 0; e<ret[0].length; e++){
                ret[i][e] = clouds[index];
                index++;
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        return super.toString()+type+",";
    }
}
