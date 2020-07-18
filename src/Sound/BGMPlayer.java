package Sound;

import Level.BGM;

import javax.sound.sampled.*;
import java.io.File;

public class BGMPlayer {

    private Clip music;
    private BGM bgm;
    private AudioInputStream BGM;

    public BGMPlayer(BGM bgm){
        try {
            this.bgm = bgm;
            BGM = AudioSystem.getAudioInputStream(new File(bgm.getURL()).getAbsoluteFile());
            music = AudioSystem.getClip();
            music.open(BGM);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeBGM(BGM bgm){
        try {
            this.bgm = bgm;
            music.close();
            BGM = AudioSystem.getAudioInputStream(new File(bgm.getURL()).getAbsoluteFile());
            music = AudioSystem.getClip();
            music.open(BGM);
            music.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Clip getMusic() {
        return music;
    }

}
