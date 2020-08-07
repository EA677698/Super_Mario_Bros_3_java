package Level;

import Elements.Elements;
import Main.Main;
import java.awt.*;
import java.io.File;
import java.io.Serializable;
import Elements.Layer;
import Sound.BGM;
import javax.imageio.ImageIO;

public class Level extends Elements implements Serializable {

    private Background background;
    private BGM bgm;

    public Level(Background background, BGM bgm){
        super(Layer.NONE, null);
        this.background = background;
        this.bgm = bgm;
    }

    public Background getBackground() {
        return background;
    }

    public Image getBackgroundSprite(){
        try {
            return ImageIO.read(new File(getBackground().getURL()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public void changeMusic(BGM bgm){
        this.bgm = bgm;
        Main.game.getBgmPlayer().changeBGM(bgm);
    }

    public BGM getBgm() {
        return bgm;
    }
}
