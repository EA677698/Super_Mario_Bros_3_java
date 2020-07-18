package Level;

import Elements.Elements;
import Main.Main;

import java.io.Serializable;
import Elements.Layer;

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
