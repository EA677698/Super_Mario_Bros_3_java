package Main;

import Elements.Entities.Entity;
import Elements.Manager;
import Graphics.Window;
import Settings.Controls;
import Settings.Settings;
import Sound.BGM;
import Elements.Tiles.Tile;


public class GameTick implements Runnable {

    private Thread thread;
    public static int cps = 0;
    public static int count = 0;
    double timer = System.nanoTime();

    private boolean running;

    public GameTick(){
        Window window = new Window();
    }

    @Override
    public void run() {
        while(running){
            count++;
            Controls.tick();
            Settings.tick();
            Manager.tick();
            Global.HUDcheck();

            if(BGM.level2.getFramePosition()>1625000){
                BGM.level2.setFramePosition(150000);
            }
            for(Entity ent:Manager.ents){
                if(!ent.isUnloaded()){
                    ent.tick();
                }
            }
            for(Tile tile:Manager.tiles){
                if(!tile.isUnloaded()){
                    tile.tick();
                }
            }
            Window.screen.repaint();
            if(System.nanoTime()-timer>1000000000){
                cps = count;
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

}
