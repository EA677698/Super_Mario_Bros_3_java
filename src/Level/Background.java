package Level;

import Main.Global;

public enum Background {


    AQUA_BACKGROUND(Global.localPath+"\\assets\\Tiles\\background\\"+2+".png"),
    BLUE_BACKGROUND(Global.localPath+"\\assets\\Tiles\\background\\"+4+".png"),
    PINK_BACKGROUND(Global.localPath+"\\assets\\Tiles\\background\\"+3+".png"),
    BLACK_BACKGROUND(Global.localPath+"\\assets\\Tiles\\background\\"+6+".png"),
    ICE_BACKGROUND(Global.localPath+"\\assets\\Tiles\\background\\"+7+".png"),
    CARAMEL_BACKGROUND(Global.localPath+"\\assets\\Tiles\\background\\"+5+".png"),
    PORTAGE_BACKGROUND(Global.localPath+"\\assets\\Tiles\\background\\"+1+".png");

    private String URL;
    Background(String URL){
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }
}
