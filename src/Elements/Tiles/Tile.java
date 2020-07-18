package Elements.Tiles;

import Elements.Elements;
import Main.Global;
import Elements.Layer;
import java.awt.*;

public abstract class Tile extends Elements {

    private int width = 60;
    private int height = 60;
    private Point location;
    private boolean collision;
    private final String localPath = Global.localPath;
    private Rectangle hitBox;
    private String tileName;
    private int blocksPerLayer;

    public Tile(Layer layer, Point location, boolean collision){
        super(layer, location);
        this.location = location;
        this.collision = collision;
        hitBox = new Rectangle(location.x,location.y,width,height);
    }

    public void tick(){
    }

    public void callingException(){
        hitBox.setLocation(getLocation().x,getLocation().y);
        hitBox.setSize(width,height);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public String getLocalPath() {
        return localPath;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void addX(int x){
        setLocation(new Point(getLocation().x+x,getLocation().y));
    }

    public void addY(int y){
        setLocation(new Point(getLocation().x,getLocation().y+y));
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public String toString(){
        return getTileName()+","+getLayer()+","+getLocation().x+","+getLocation().y+","+collision+",";
    }

    public abstract Image[] getSprites();

    public String getTileName() {
        return tileName;
    }

    public void setTileName(String tileName) {
        this.tileName = tileName;
    }

    public int getBlocksPerLayer() {
        return blocksPerLayer;
    }

    public void setBlocksPerLayer(int blocksPerLayer) {
        this.blocksPerLayer = blocksPerLayer;
    }
}
