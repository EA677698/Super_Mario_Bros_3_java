package Sound;

import Main.Global;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BGM {

    public static Clip one, two, three;

    public BGM() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String path = Global.localPath + "\\sounds\\BGM\\";
        AudioInputStream BGM0 = AudioSystem.getAudioInputStream(new File(path + "grassland.wav").getAbsoluteFile());
        AudioInputStream BGM1 = AudioSystem.getAudioInputStream(new File(path + "level1.wav").getAbsoluteFile());
        AudioInputStream BGM2 = AudioSystem.getAudioInputStream(new File(path + "level2.wav").getAbsoluteFile());
        one = AudioSystem.getClip();
        one.open(BGM0);
        two = AudioSystem.getClip();
        two.open(BGM1);
        three = AudioSystem.getClip();
        three.open(BGM2);
    }

    public static Clip integerToClip(int clipID){
        switch (clipID){
            case 2: return two;
            case 3: return three;
            default: return one;
        }
    }
}
