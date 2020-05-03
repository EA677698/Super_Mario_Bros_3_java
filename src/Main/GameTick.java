package Main;

import Elements.Entities.Entity;
import Elements.Manager;
import Graphics.Window;
import Settings.Controls;
import Settings.Settings;
import Sound.BGM;
import Elements.Tiles.Tile;
import Sound.SFX;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class GameTick implements Runnable {

    private Thread thread;
    static final int DEFAULT_CLIP = 1;
    public static int tps = 0;
    public static int count = 0;
    double timer = System.nanoTime();
    public static boolean clipReset;
    static SFX sfx;
    static BGM bgm;

    static {
        try {
            sfx = new SFX();
            bgm = new BGM();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static Clip currentClip;
    public static int clipID;

    private boolean running;

    public GameTick(){
        Window window = new Window();
    }

    @Override
    public void run() {
        currentClip = BGM.integerToClip(1);
        currentClip.start();
        clipID = 1;
        while(running){
            count++;
            Controls.tick();
            Settings.tick();
            Manager.tick();
            Global.HUDcheck();

            for(Entity ent:Manager.ents){
                if(!ent.isUnloaded()){
                    ent.tick();
                }
                ent.callingException();
            }
            for(Tile tile:Manager.tiles){
                if(!tile.isUnloaded()){
                    tile.tick();
                }
                tile.callingException();
            }
            Window.screen.repaint();
            if(System.nanoTime()-timer>1000000000){
                tps = count;
                count = 0;
                timer = System.nanoTime();
            }
        }
        stop();
    }

    public synchronized void start(){
        if(running){return;}
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running){return;}
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void soundManager(String bgmID){
        if(!Settings.muted&&!clipReset){
            if(currentClip!=null) {
                currentClip.setFramePosition(0);
                currentClip.stop();
            }
            currentClip = BGM.integerToClip(Integer.parseInt(bgmID));
            clipID = Integer.parseInt(bgmID);
            currentClip.start();
        } else if(Settings.muted){
            clipReset = false;
        }
    }

}
