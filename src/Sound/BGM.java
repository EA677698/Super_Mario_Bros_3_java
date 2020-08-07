package Sound;

import Main.Global;

public enum BGM {

    GRASS_LAND( Global.localPath + "\\sounds\\BGM\\grassland.wav"),
    LEVEL_1(Global.localPath + "\\sounds\\BGM\\level1.wav"),
    LEVEL_2(Global.localPath + "\\sounds\\BGM\\level2.wav");

    private String URL;

    BGM(String URL){
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }
}
