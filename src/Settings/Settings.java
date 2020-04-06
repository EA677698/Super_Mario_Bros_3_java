package Settings;

import Elements.Manager;

public class Settings {

    public static boolean debug = false;
    public static boolean hitBoxes = false;
    public static boolean crt = false;
    public static boolean fps = false;
    public static boolean muted = false;

    public static void tick(){
        if(!debug){
            if(Manager.selectedEntity!=null){
                Manager.selectedEntity.setSelected(false);
                Manager.selectedEntity.setDirection(Manager.previousDirection);
                Manager.selectedEntity = null;
            }
            if(Manager.selectedTile!=null){
                Manager.selectedTile = null;
            }
        }
    }
}
