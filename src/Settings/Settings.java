package Settings;

import Main.Main;

public class Settings {

    public static boolean debug = false;
    public static boolean hitBoxes = false;
    public static boolean crt = false;
    public static boolean fps = false;
    public static boolean muted = false;

    public static void tick(){
        if(!debug){
            if(Main.game.getManager().getSelectedEntity()!=null){
                Main.game.getManager().getSelectedEntity().setSelected(false);
                Main.game.getManager().getSelectedEntity().setDirection(Main.game.getManager().getPreviousDirection());
                Main.game.getManager().setSelectedEntity(null);
            }
            if(Main.game.getManager().getSelectedTile()!=null){
                Main.game.getManager().setSelectedTile(null);
            }
        }
    }
}
