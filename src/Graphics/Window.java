package Graphics;

import Settings.Controls;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel {

    JFrame frame = new JFrame();
    public static final Screen screen = new Screen();
    static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenHeight,screenWidth;
    public static double scaleX,scaleY;

    public Window(){
        screenHeight = screenSize.height;
        screenWidth = screenSize.width;
        scaleX = (getSize().width/800.0);
        scaleY = (getSize().height/600.0);
        frame.setTitle("Super Mario Bros 3");
        frame.setSize(screenWidth,screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Controls controls = new Controls();
        frame.addKeyListener(controls);
        frame.addMouseMotionListener(controls);
        frame.addMouseListener(controls);
        frame.add(screen);
        frame.setVisible(true);
    }

    public JFrame getFrame(){
        return frame;
    }

    public void setFrame(JFrame frame){
        this.frame = frame;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        Window.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        Window.screenHeight = screenHeight;
    }

}
