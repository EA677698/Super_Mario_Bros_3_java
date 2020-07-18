package Graphics;

import Elements.Elements;
import Elements.Entities.Entity;
import Elements.Tiles.Interactables.Interactable;
import Elements.Tiles.LayeredTile;
import Elements.Tiles.Tile;
import Elements.Tiles.Tools.Clip;
import Elements.Tiles.Tools.Trigger;
import Main.Cards;
import Main.Global;
import Main.GameTick;
import Settings.Controls;
import Settings.Settings;
import Elements.Manager;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Screen extends JPanel {

    public int fps = 0;
    public String input = "";
    public int count = 0;
    public boolean error;
    public double timer = System.nanoTime();
    private static String debugMessages;
    public final CopyOnWriteArrayList<Elements> layer1 = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<Elements> layer2 = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<Elements> layer3 = new CopyOnWriteArrayList<>();
    Color clipColor = new Color(255,0,0,100);
    Color triggerColor = new Color(169, 169, 169, 100);
    Image background;
    Image board, speed, power, crt;
    public static HashMap<Integer,Image> nums;
    public Screen(){
        debugMessages = new String();
        nums = new HashMap<>();
        try {
            background = ImageIO.read(new File(Manager.level.getBackground().getURL()));
            board = ImageIO.read(new File(Global.localPath+"\\assets\\HUD\\main.png"));
            speed = ImageIO.read(new File(Global.localPath+"\\assets\\HUD\\speed.png"));
            power = ImageIO.read(new File(Global.localPath+"\\assets\\HUD\\p.png"));
            crt = ImageIO.read(new File(Global.localPath+"\\assets\\Tiles\\other\\crt2.png"));
            for(int i = 0; i<3; i++){
                Global.cards[i] = Cards.EMPTY_CARD;
            }
            for(int i = 0; i<10; i++) {
                nums.put(i, ImageIO.read(new File(Global.localPath + "\\assets\\HUD\\"+i+".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        scaling();
        count++;
        g.drawImage(background,0,0,(int)(1920*Window.scaleX),(int)(1080*Window.scaleY),this);
        paintElements(g,layer1);
        paintElements(g,layer2);
        paintElements(g,layer3);
        //HUD
        g.setColor(Color.BLACK);
        g.fillRect(0,(int)(840*Window.scaleY),(int)(1920*Window.scaleX),(int)(300*Window.scaleY));
        g.drawImage(board,(int)(50*Window.scaleX),(int)(850*Window.scaleY),(int)(1100*Window.scaleX),(int)(180*Window.scaleY),this);
        for(int i = 0; i<Global.active.length-1; i++){
            if(Global.active[i]){
                g.drawImage(speed, (int)((415+(i*55))*Window.scaleX),(int)(901*Window.scaleY),(int)(60*Window.scaleX),(int)(40*Window.scaleY),this);
            }
        }
        if(Global.active[Global.active.length-1]){
            g.drawImage(power,(int)(770*Window.scaleX),(int)(901*Window.scaleY),(int)(Window.scaleX*110),(int)(40*Window.scaleY),this);
        }
        for(int i = 0; i<7; i++){
            g.drawImage(Global.getScores()[i],(int)((425+(i*60))*Window.scaleX),(int)(945*Window.scaleY),(int)(50*Window.scaleX),(int)(50*Window.scaleY),this);
        }
        g.drawImage(Global.getMoney()[0],(int)(1070*Window.scaleX),(int)(890*Window.scaleY),(int)(50*Window.scaleX),(int)(50*Window.scaleY),this);
        g.drawImage(Global.getMoney()[1],(int)(1010*Window.scaleX),(int)(890*Window.scaleY),(int)(50*Window.scaleX),(int)(50*Window.scaleY),this);
        g.drawImage(Global.getLives()[0],(int)(250*Window.scaleX),(int)(945*Window.scaleY),(int)(50*Window.scaleX),(int)(50*Window.scaleY),this);
        g.drawImage(Global.getLives()[1],(int)(310*Window.scaleX),(int)(945*Window.scaleY),(int)(50*Window.scaleX),(int)(50*Window.scaleY),this);
        for(int i = 0; i<3; i++){
            g.drawImage(Global.getTime()[i],(int)((950+(i*60))*Window.scaleX),(int)(945*Window.scaleY),(int)(50*Window.scaleX),(int)(50*Window.scaleY),this);
            g.drawImage(Global.getCards()[i],(int)((1300+(i*150))*Window.scaleX),(int)(850*Window.scaleY),(int)(150*Window.scaleX),(int)(180*Window.scaleY),this);
        }
        //END HUD
        if(Settings.debug){
            g.setColor(Color.BLACK);
            for(int i = 0; i<19; i++){
                g.drawLine(0,(int)((i*60)*Window.scaleY),(int)(1920*Window.scaleX),(int)((i*60)*Window.scaleY));
            }
            for(int i = 0; i<33; i++){
                g.drawLine((int)((i*60)*Window.scaleX),0,(int)((i*60)*Window.scaleX),(int)(1080*Window.scaleY));
            }
            for(Tile tool: Manager.tiles){
                if(tool instanceof Clip){
                    g.setColor(clipColor);
                    g.fillRect(tool.getLocation().x,tool.getLocation().y,tool.getWidth(),tool.getHeight());
                }
                if(tool instanceof Trigger){
                    g.setColor(triggerColor);
                    g.fillRect(tool.getLocation().x,tool.getLocation().y,tool.getWidth(),tool.getHeight());
                }
            }
            g.setColor(Color.RED);
            g.drawString("Entity Count: "+ Manager.ents.size(),(int)(1000*Window.scaleX),(int)(20*Window.scaleY));
        }
        if(Settings.fps||Settings.debug){
            if(fps<=15){
                g.setColor(Color.RED);
            } else if(fps<=30){
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.GREEN);
            }
            g.setFont(new Font("Arial",Font.BOLD, (int)(25*((getSize().width*getSize().height)/(1920.0*1080.0)))));
            g.drawString("FPS: "+fps,(int)(100*Window.scaleX),(int)(100*Window.scaleY));
            g.drawString("TPS: "+ GameTick.tps,(int)(100*Window.scaleX),(int)(125*Window.scaleY));
        }
        if(Settings.hitBoxes){
            g.setColor(Color.GREEN);
            for(Entity ent : Manager.ents){
                g.drawRect((int)(ent.getLocation().x*Window.scaleX),(int)(ent.getLocation().y*Window.scaleY),(int)(ent.getWidth()*Window.scaleX),(int)(ent.getHeight()*Window.scaleY));
            }
            for(Tile tile : Manager.tiles){
                g.drawRect((int)(tile.getLocation().x*Window.scaleX),(int)(tile.getLocation().y*Window.scaleY),(int)(tile.getWidth()*Window.scaleX),(int)(tile.getHeight()*Window.scaleY));
            }
            g.drawRect((int)(Controls.mouseHitBox.x*Window.scaleX),(int)(Controls.mouseHitBox.y*Window.scaleY),(int)(Controls.mouseHitBox.width*Window.scaleX),(int)(Controls.mouseHitBox.height*Window.scaleY));
            g.setColor(Color.RED);
            if(Manager.selectedEntity!=null){
                g.drawString(Manager.selectedEntity.toString(),(int)(20*Window.scaleX),(int)(20*Window.scaleY));
                g.drawRect((int)(Manager.selectedEntity.getLocation().x*Window.scaleX), (int)(Manager.selectedEntity.getLocation().y*Window.scaleY), (int)(Manager.selectedEntity.getWidth()*Window.scaleX), (int)(Manager.selectedEntity.getHeight()*Window.scaleY));
            }
            if(Manager.selectedTile!=null){
                g.drawString(Manager.selectedTile.toString(),(int)(20*Window.scaleX),(int)(40*Window.scaleY));
                g.drawRect((int)(Manager.selectedTile.getLocation().x*Window.scaleX), (int)(Manager.selectedTile.getLocation().y*Window.scaleY), (int)(Manager.selectedTile.getWidth()*Window.scaleX), (int)(Manager.selectedTile.getHeight()*Window.scaleY));
            }
        }
       // g.drawLine(0,300,1920,300);
        if(System.nanoTime()-timer>1000000000){
            fps = count;
            count = 0;
            timer = System.nanoTime();
        }
        if(Controls.console){
            g.setColor(Color.GRAY);
            g.fillRect((int)(300*Window.scaleX),(int)(265*Window.scaleY),(int)(1000*Window.scaleX),(int)(50*Window.scaleY));
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial",Font.BOLD, (int)(25*((getSize().width*getSize().height)/(1920.0*1080.0)))));
            g.drawString(">"+input, (int)(300*Window.scaleX),(int)(300*Window.scaleY));
        } else {
            input = "";
        }
        if(Controls.enter){
            Controls.enter = false;
            Controls.console = false;
            input = input.replace("\n", "").replace("\r", "");
            error = Manager.commandInput(input.toLowerCase());
            input = "";
        }
        if(Settings.crt){
            g.drawImage(crt,0,0,(int)(1920*Window.scaleX),(int)(1080*Window.scaleY),this);
        }
            g.drawString(debugMessages,100,100);
        g.setColor(Color.GREEN);
    }

    public static void debugPrint(String message){
        debugMessages = message;
    }

    public void scaling(){
        if(!(Window.scaleX == (getSize().width/1980.0)&& Window.scaleY==(getSize().height/1080.0))){
            Window.scaleX = (getSize().width/1920.0);
            Window.scaleY = (getSize().height/1080.0);
        }
    }


    public void paintElements(Graphics g, CopyOnWriteArrayList<Elements> layer) {
        for(Elements elements : layer){
            if(!elements.isUnloaded()) {
                if (elements instanceof Tile) {
                    if (!(elements instanceof LayeredTile) && !(elements instanceof Interactable)) {
                        for (int i = 0; i < ((Tile) elements).getSprites().length; i++) {
                            g.drawImage(((Tile) elements).getSprites()[i], (int) ((elements.getLocation().x + (60 * i)) * Window.scaleX), (int) (elements.getLocation().y * Window.scaleY), (int) (60 * Window.scaleX), (int) (60 * Window.scaleY), this);
                        }
                    } else if (elements instanceof LayeredTile) {
                        for (int i = 0; i < ((LayeredTile) elements).get2DSprites().length; i++) {
                            for (int e = 0; e < ((LayeredTile) elements).get2DSprites()[0].length; e++) {
                                g.drawImage(((LayeredTile) elements).get2DSprites()[i][e], (int) ((elements.getLocation().x + (60 * e)) * Window.scaleX), (int) ((elements.getLocation().y + (i * 60)) * Window.scaleY), (int) (60 * Window.scaleX), (int) (60 * Window.scaleY), this);
                            }
                        }
                    } else {
                        g.drawImage(((Tile) elements).getSprites()[((Interactable) elements).getCurrentSprite()], (int) (elements.getLocation().x * Window.scaleX), (int) (elements.getLocation().y * Window.scaleY), (int) (60 * Window.scaleX), (int) (60 * Window.scaleY), this);
                    }
                }
                if (elements instanceof Entity) {
                    if (((Entity) elements).getDirection() == -1) {
                        g.drawImage(((Entity) elements).getSprite(), (int) ((elements.getLocation().x + ((Entity) elements).getWidth()) * Window.scaleX), (int) (elements.getLocation().y * Window.scaleY), (int) ((-1 * (((Entity) elements).getWidth())) * Window.scaleX), (int) (((Entity) elements).getHeight() * Window.scaleY), this);
                    } else {
                        g.drawImage(((Entity) elements).getSprite(), (int) (elements.getLocation().x * Window.scaleX), (int) (elements.getLocation().y * Window.scaleY), (int) (((Entity) elements).getWidth() * Window.scaleX), (int) (((Entity) elements).getHeight() * Window.scaleY), this);
                    }
                }
            }
        }
    }


}
