package FileManager;


import Elements.Entities.Entity;
import Elements.Manager;
import Elements.Tiles.Tile;
import Graphics.Screen;
import Main.Global;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Write {

    public static int world;
    public static int level;

    public static void createLevel(){
        try {
            File file = new File(Global.localPath+"\\levels\\"+world+"-"+level+".mb3");
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println("Background,"+Screen.currentBackground+",");
            for(Entity entity : Manager.ents){
                writer.println(entity.toString());
            }
            for(Tile tile : Manager.tiles){
                writer.println(tile.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
