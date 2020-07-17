package FileManager;


import Elements.Entities.Entity;
import Elements.Manager;
import Elements.Tiles.Tile;
import Graphics.Screen;
import Main.GameTick;
import Main.Global;

import java.io.*;

public class Saver {

    public static int world;
    public static int level;

    public static void createLevel(){
        try {
            File file = new File(Global.localPath+"\\levels\\"+world+"-"+level+".mb3");
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println("BGM,"+GameTick.clipID+",");
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

    public static void createALevel(){
        try {
        FileOutputStream fileOut = new FileOutputStream(Global.localPath+"\\levels\\"+world+"-"+level+".mb3");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for(Entity entity : Manager.ents){
                objectOut.writeObject(entity);
            }
            for(Tile tile : Manager.tiles){
                objectOut.writeObject(tile);
            }
        objectOut.close();
        System.out.println("Successfully saved");

    } catch (Exception ex) {
        ex.printStackTrace();
    }}

}
