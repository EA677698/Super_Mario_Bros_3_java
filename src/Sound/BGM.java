package Sound;

import Main.Global;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BGM {

    public static Clip grassland,level1,level2;

    public BGM() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String path = Global.localPath + "\\sounds\\BGM\\";
        AudioInputStream BGM0 = AudioSystem.getAudioInputStream(new File(path + "grassland.wav").getAbsoluteFile());
        AudioInputStream BGM1 = AudioSystem.getAudioInputStream(new File(path + "level1.wav").getAbsoluteFile());
        AudioInputStream BGM2 = AudioSystem.getAudioInputStream(new File(path + "level2.wav").getAbsoluteFile());
        grassland = AudioSystem.getClip();
        grassland.open(BGM0);
        level1 = AudioSystem.getClip();
        level1.open(BGM1);
        level2 = AudioSystem.getClip();
        level2.open(BGM2);
    }
}
