package Sound;

import Main.Global;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SFX {

    public static Clip down1;
    public static Clip up1;
    public static Clip coin;
    public static Clip gameOver;
    public static Clip hurry;
    public static Clip pause;
    public static Clip pipe;
    public static Clip powerUp;

    public SFX() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        String path = Global.localPath + "\\sounds\\SFX\\";
        AudioInputStream SFX0 = AudioSystem.getAudioInputStream(new File(path + "1down.wav").getAbsoluteFile());
        AudioInputStream SFX1 = AudioSystem.getAudioInputStream(new File(path + "1up.wav").getAbsoluteFile());
        AudioInputStream SFX2 = AudioSystem.getAudioInputStream(new File(path + "coin.wav").getAbsoluteFile());
        AudioInputStream SFX3 = AudioSystem.getAudioInputStream(new File(path + "gameover.wav").getAbsoluteFile());
        AudioInputStream SFX4 = AudioSystem.getAudioInputStream(new File(path + "hurry.wav").getAbsoluteFile());
        AudioInputStream SFX5 = AudioSystem.getAudioInputStream(new File(path + "pause.wav").getAbsoluteFile());
        AudioInputStream SFX6 = AudioSystem.getAudioInputStream(new File(path + "pipe.wav").getAbsoluteFile());
        AudioInputStream SFX7 = AudioSystem.getAudioInputStream(new File(path + "powerup.wav").getAbsoluteFile());
        down1 = AudioSystem.getClip();
        down1.open(SFX0);
        up1 = AudioSystem.getClip();
        up1.open(SFX1);
        coin = AudioSystem.getClip();
        coin.open(SFX2);
        gameOver = AudioSystem.getClip();
        gameOver.open(SFX3);
        hurry = AudioSystem.getClip();
        hurry.open(SFX4);
        pause = AudioSystem.getClip();
        pause.open(SFX5);
        pipe = AudioSystem.getClip();
        pipe.open(SFX6);
        powerUp = AudioSystem.getClip();
        powerUp.open(SFX7);

    }

}
