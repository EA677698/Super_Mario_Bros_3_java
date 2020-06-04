package Elements;
import Elements.Entities.Enemies.*;
import Elements.Entities.Entity;
import Elements.Entities.Mario.Player;
import Elements.Entities.Mario.Powers;
import Elements.Entities.NotLiving.*;
import Elements.Tiles.*;
import Elements.Tiles.Interactables.Pipes;
import FileManager.Loader;
import FileManager.Saver;
import Main.GameTick;
import Main.Global;
import Settings.Controls;
import Settings.Settings;
import Sound.BGM;
import Sound.SFX;
import Graphics.Window;
import Elements.Tiles.Interactables.Bricks;
import Elements.Tiles.Interactables.LuckyBlock;
import Graphics.Screen;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Manager {

    public static Entity selectedEntity;
    public static Tile selectedTile;
    public static int previousDirection;
    public static final CopyOnWriteArrayList<Entity> ents = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<Tile> tiles = new CopyOnWriteArrayList<>();
    public static final HashMap<String, Entity> customEntities = new HashMap<>();
    public static Player player;
    public static Rectangle screen = new Rectangle(0,0,1920,1080);
    private static double timer1 = System.nanoTime();
    private static double hitTimer = System.nanoTime();
    private static double debugScroll = System.nanoTime();

    public static void registerEntity(String command, Entity entity){
        customEntities.put(command,entity);
        entity.removeFromLayer();
    }


    public static boolean commandInput(String input){
        if(input.contains(" ")) {
            String end = input.substring(input.indexOf(" ") + 1);
            switch (input.substring(0, input.indexOf(" "))) {
                case "spawn":
                    return spawnElement(end);
                case "remove":
                    return removeElement(end);
                case "add":
                    return addStats(end);
                case "freeze":
                    return freezeElements(end);
                case "background":
                    try {
                        Screen.background = ImageIO.read(new File(Global.localPath+"\\assets\\Tiles\\background\\"+end+".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                case "save":
                    Saver.world = Integer.parseInt(end.substring(0,end.indexOf(" ")));
                    Saver.level = Integer.parseInt(end.substring(end.indexOf(" ")+1));
                    Saver.createLevel();
                    return true;
                case "load":
                    Loader.loadLevel(end);
                    return true;
                case "bgm":
                    GameTick.clipReset = false;
                    GameTick.soundManager(end);
                    return true;
            }
        } else {
            switch (input){
                case "debug":
                    Settings.debug = !Settings.debug;
                    return true;
                case "fps":
                    Settings.fps = !Settings.fps;
                    return true;
                case "hitbox":
                    Settings.hitBoxes = !Settings.hitBoxes;
                    return true;
                case "crt":
                    Settings.crt = !Settings.crt;
                    return true;
                case "quit":
                    System.exit(0);
                    return true;
                case "unload":
                    Loader.unloadLevel();
                    return true;
                case "mute":
                    Settings.muted = !Settings.muted;
            }
        }
        return false;
    }

    public static boolean addStats(String stat){
        int end = Integer.parseInt(stat.substring(stat.indexOf(" ")+1));
        switch (stat.substring(0,stat.indexOf(" "))){
            case "score":
                Global.score += end;
                return true;
            case "money":
                Global.money += end;
                return true;
            case "lives":
                Global.lives += end;
                return true;
        }
        return false;
    }

    public static boolean freezeElements(String element){
        switch (element){
            case "all":
                for(Entity entity: ents){
                    entity.setAllowedMoving(!entity.isAllowedMoving());
                }
                return true;
            case "goomba":
                for(Entity entity: ents){
                    if(entity instanceof Goomba){
                        entity.setAllowedMoving(!entity.isAllowedMoving());
                    }
                }
                return true;
            case "enemies":
                for(Entity entity: ents){
                    if(entity instanceof Enemy){
                        entity.setAllowedMoving(!entity.isAllowedMoving());
                    }
                }
                return true;
            case "koopatroopa":
                for(Entity entity: ents){
                    if(entity instanceof KoopaTroopa){
                        entity.setAllowedMoving(!entity.isAllowedMoving());
                    }
                }
                return true;
            case "fireball":
                for(Entity entity: ents){
                    if(entity instanceof Fireball){
                        entity.setAllowedMoving(!entity.isAllowedMoving());
                    }
                }
                return true;
            case "hammerbro":
                for(Entity entity: ents){
                    if(entity instanceof HammerBro){
                        entity.setAllowedMoving(!entity.isAllowedMoving());
                    }
                }
                return true;
        }
        return false;
    }

    public static void resetEntities(){
        for(Entity ent: ents){
            ent.removeFromLayer();
            ents.remove(ent);
        }
    }

    public static void resetTiles(){
        for(Tile tile: tiles){
            tile.removeFromLayer();
            tiles.remove(tile);
        }
    }

    public static void select(){
        for(Entity ent: ents){
            if(ent.getHitBox().intersects(Controls.mouseHitBox)){
                if(selectedEntity!=null){
                    selectedEntity.setDirection(previousDirection);
                    selectedEntity.setSelected(false);
                }
                selectedEntity = ent;
                selectedEntity.setSelected(true);
                selectedTile = null;
                previousDirection = selectedEntity.getDirection();
                selectedEntity.setDirection(0);
                return;
            }
        }
        for(Tile tile: tiles){
            if(tile.getHitBox().intersects(Controls.mouseHitBox)){
                selectedTile = tile;
                if(selectedEntity!=null){
                    selectedEntity.setDirection(previousDirection);
                    selectedEntity.setSelected(false);
                    return;
                }
            }
        }
    }

    public static void freeEntity(){
        if(selectedEntity!=null){
             if(System.nanoTime()-timer1>100000000){
                if(Controls.six&&selectedEntity.getDirection()==0){
                    selectedEntity.setDirection(previousDirection);
                } else{
                    if(Controls.six&&selectedEntity.getDirection()!=0){
                        selectedEntity.setDirection(0);
                    }
                }
                timer1 = System.nanoTime();
            }
        }
    }

    public static void culling(){
        //Checks to see if the element is within the screen's boundaries otherwise it unloads it
        for(Entity entity: Manager.ents){
            if(!screen.intersects(entity.getHitBox())&&!screen.contains(entity.getHitBox())){
                entity.setUnloaded(true);
            } else {
             entity.setUnloaded(false);
            }
        }
        for(Tile tile: Manager.tiles){
            if(!screen.intersects(tile.getHitBox())&&!screen.contains(tile.getHitBox())){
                tile.setUnloaded(true);
            } else {
                tile.setUnloaded(false);
            }
        }
    }


    public static void tick(){
        if(Settings.debug){
            freeEntity();
        }
        sideScroll();
        collision();
        culling();
        if(Settings.debug&&!Controls.console){
            deleteSelected();
        }
    }

    private static void marioCheck(){
        if(player!=null){
            player.removeFromLayer();
            ents.remove(player);
            player = null;
        }
    }

    public static boolean removeElement(String element){
        if(element.equals("entities")){
            resetEntities();
            return true;
        } else if(element.equals("tiles")){
            resetTiles();
            return true;
        }
        return false;
    }

    public static boolean spawnElement(String element){
        switch (element){
            case "mario":
                marioCheck();
                ents.add(new Player(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),60,60, true));
                return true;
            case "goomba":
                ents.add(new Goomba(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),60,60,1, true));
                return true;
            case "koopatroopa":
                ents.add(new KoopaTroopa(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),60,98,1, true));
                return true;
            case "redmushroom":
                ents.add(new RedMushroom(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),60,60,1,true));
                return true;
            case "greenmushroom":
                ents.add(new GreenMushroom(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),60,60,1,true));
                return true;
            case "coin":
                ents.add(new Coin(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),60,60,0,true));
                return true;
            case "floor":
                tiles.add(new Floor(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),true,8,1));
                return true;
            case "woodenblock":
                tiles.add(new WoodenBlock(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),true));
                return true;
            case "luckyblock":
                tiles.add(new LuckyBlock(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),true,new Coin(Layer.NONE, new Point(0,0),70,80,0,true)));
                return true;
            case "hill1":
                tiles.add(new Hill(Layer.BACK_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),false,2,8));
                return true;
            case "hill2":
                tiles.add(new Hill(Layer.BACK_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),false,1,8));
                return true;
            case "brick":
                tiles.add(new Bricks(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),true,true));
                return true;
            case "firehammerbro":
                ents.add(new HammerBro(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),60,98,-1,true,3));
                return true;
            case "pipe":
                tiles.add(new Pipes(Layer.MIDDLE_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),true,4));
                return true;
            case "cloud1":
                tiles.add(new Clouds(Layer.BACK_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2), true ,1));
                return true;
            case "bg":
                tiles.add(new BigBlocks(Layer.BACK_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),true,0,0,0));
                return true;
            case  "bp":
                tiles.add(new BigBlocks(Layer.BACK_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),true,2,0,0));
                return true;
            case  "bb":
                tiles.add(new BigBlocks(Layer.BACK_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),true,1,0,0));
                return true;
            case  "bw":
                tiles.add(new BigBlocks(Layer.BACK_LAYER, new Point(Window.screenWidth/2,Window.screenHeight/2),true,3,0,0));
                return true;
            case  "shrub":
                tiles.add(new Shrub(Layer.BACK_LAYER,new Point(Window.screenWidth/2,Window.screenHeight/2),true));
                return true;
        }
        if(customEntities.containsKey(element)){ ;
            try {
                try {
                    ents.add(customEntities.get(element).getClass().getDeclaredConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void deleteSelected(){
        if(selectedEntity!=null){
            if(Controls.delete){
                selectedEntity.removeFromLayer();
                ents.remove(selectedEntity);
                selectedEntity = null;
            }
        }
        if(selectedTile!=null){
            if(Controls.delete){
                selectedTile.removeFromLayer();
                tiles.remove(selectedTile);
                selectedTile = null;
            }
        }
    }

    public static void sideScroll(){
        if(Settings.debug&&!Controls.shift){
            if(System.nanoTime()-debugScroll>70000000){
                if(Controls.right){
                    for(Entity ent: ents){
                        if(ent!=player){
                            ent.addX(-60);
                        }
                    }
                    for(Tile tile : tiles){
                        tile.addX(-60);
                    }
                } else if(Controls.left){
                    for(Entity ent: ents){
                        if(ent!=player){
                            ent.addX(60);
                        }
                    }
                    for(Tile tile : tiles){
                        tile.addX(60);
                    }
                }
                debugScroll = System.nanoTime();
            }
            return;
        }
        if(player == null){
            for(Entity ent: ents){
                if(ent instanceof Player){
                    player = (Player) ent;
                }
            }
        }
        if(player == null){
            return;
        }
        if(!player.isDead()){
            if(player.isMiddleH()&&Controls.jump){
                for(Entity ent: ents){
                    if(ent!=player){
                        ent.addY(5);
                    }
                }
                for(Tile tile : tiles){
                    tile.addY(5);
                }
            } else {
                if(player.isStartH()){
                    for(Entity ent: ents){
                        if(ent!=player){
                            ent.addY(-5);
                        }
                    }
                    for(Tile tile : tiles){
                        tile.addY(-5);
                    }
                }
            }
            if(player.isMiddle()){
                for(Entity ent: ents){
                    if(ent!=player){
                        ent.addX((int)(-1*player.getXVelocity()));
                    }
                }
                for(Tile tile : tiles){
                    tile.addX((int)(-1*player.getXVelocity()));
                }
            } else {
                if(player.isStart()){
                    for(Entity ent: ents){
                        if(ent!=player){
                            ent.addX((int)player.getXVelocity());
                        }
                    }
                    for(Tile tile : tiles){
                        tile.addX((int)player.getXVelocity());
                    }
                }
            }
        }
    }


    public static void collision(){
        for(Entity ent : Manager.ents){
            if(ent.isCollision()&&!ent.isUnloaded()){
                if(player!=null){
                    if(ent instanceof Enemy){
                        if(System.nanoTime()-hitTimer>2000000000){
                            // If an enemy hits mario from his sides
                            int side = ent.getHitBox().outcode(player.getHitBox().getCenterX(),player.getHitBox().getCenterY());
                            if(player.getHitBox().intersects(ent.getHitBox())){
                                if(side==1||side==4){
                                    switch (player.getPower()){
                                        case SMALL: player.setDead(true);
                                            BGM.two.stop();
                                            SFX.down1.setFramePosition(0);
                                            SFX.down1.start();
                                            break;
                                        case BIG: player.setPower(Powers.SMALL);
                                            SFX.pipe.setFramePosition(0);
                                            SFX.pipe.start();
                                            break;
                                        default: player.setPower(Powers.BIG);
                                    }
                                    hitTimer = System.nanoTime();
                                } else {
                                    if(side==2){
                                        ent.death();
                                    }
                                }
                            }
                        }
                        //Activates Non Living Entity Special Action towards Mario
                    } else if(ent instanceof NotLiving){
                        if(ent.getHitBox().intersects(player.getHitBox())){
                            ((NotLiving) ent).executeUponTouch(player);
                        }
                    }
                }
                //If two entities collide
                for(Entity ent2: Manager.ents){
                    if(ent!=ent2) {
                        if (ent2.isCollision() && !ent2.isUnloaded()) {
                            if (ent instanceof Enemy && ent2 instanceof Enemy) {
                                if (ent.getHitBox().intersects(ent2.getHitBox())) {
                                    ent.setDirection(ent.getDirection() * -1);
                                    ent2.setDirection(ent2.getDirection() * -1);
                                }
                            }
                        }
                    }
                }
                for(Tile tile : tiles){
                    if(tile.isCollision()&&!tile.isUnloaded()){
                        if(tile.getHitBox().intersects(ent.getHitBox())){
                            //If an entity hits something from his sides while moving
                            int side = tile.getHitBox().outcode(ent.getHitBox().getCenterX(),ent.getHitBox().getCenterY());
                            if(!(tile instanceof BigBlocks)&&(side==1||side==4||side==9||side==12)){
                                if(ent.isFacingTile(tile)){
                                    if(ent instanceof Player){
                                        if(ent.getDirection()==1){
                                            player.setXVelocity(0);
                                            Controls.right = false;
                                        } else {
                                            player.setXVelocity(0);
                                            Controls.left = false;
                                        }
                                    } else {
                                        ent.setDirection(ent.getDirection()*-1);
                                    }
                                } //If Mario Hits something above him while jumping
                            } else if(side==8||side==9||side==12){
                                if(ent instanceof Player){
                                    ent.setYVelocity(0);
                                    ent.setHasGravity(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
