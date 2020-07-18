package Settings;

import Elements.Manager;
import Graphics.Window;
import java.awt.*;
import java.awt.event.*;

public class Controls implements KeyListener, MouseMotionListener, MouseListener {

    static final boolean[] keys = new boolean[256];
    public static boolean up,down,left,right,jump,select,mousePressed,six,delete,dragged,shift,slash,console,enter, alt;
    public static final Rectangle mouseHitBox = new Rectangle(0,0,30,30);
    private static double timer = System.nanoTime();

    public Controls(){}

    public static void tick(){
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        jump = keys[KeyEvent.VK_C];
        select = keys[KeyEvent.VK_ENTER];
        six = keys[KeyEvent.VK_F];
        delete = keys[KeyEvent.VK_BACK_SPACE];
        shift = keys[KeyEvent.VK_SHIFT];
        slash = keys[KeyEvent.VK_BACK_QUOTE];
        enter = keys[KeyEvent.VK_ENTER];
        alt = keys[KeyEvent.VK_X];
        if(System.nanoTime()-timer>100000000){
            if(slash){
                console = !console;
            }
            timer = System.nanoTime();
        }
    }

    public void letterInput(String letter){
        if(!letter.equals("`")) {
            Window.screen.input += letter;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (console) {
            if (delete) {
                Window.screen.input = Window.screen.input.substring(0, Window.screen.input.length() - 1);
            } else if (e.getKeyCode() != KeyEvent.VK_SHIFT) {
                letterInput(String.valueOf(e.getKeyChar()));
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(Settings.debug) {
            mouseHitBox.setLocation(e.getX()-20,e.getY()-45);
            if (Manager.selectedEntity != null) {
                    Manager.selectedEntity.setLocation(new Point(((mouseHitBox.getLocation().x / 60) * 60), ((mouseHitBox.getLocation().y / 60) * 60)));
            }
            if (Manager.selectedTile != null) {
                    Manager.selectedTile.setLocation(new Point(((mouseHitBox.getLocation().x / 60) * 60), ((mouseHitBox.getLocation().y / 60) * 60)));
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(Settings.debug){
            mouseHitBox.setLocation(e.getX()-20,e.getY()-45);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(Settings.debug){
            Manager.select();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
        dragged = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }




}
