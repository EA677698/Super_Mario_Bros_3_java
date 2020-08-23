package FileManager;

import Main.Global;
import Main.Main;

import java.io.*;

public class Saver {

    public static int world;
    public static int level;

    public static void createLevel(){
        try {
            FileOutputStream file = new FileOutputStream(Global.localPath + "\\levels\\" + world + "-" + level + ".mb3");
            ObjectOutputStream writer = new ObjectOutputStream(file);
            writer.writeObject(Main.game.getManager().getSavedObjects());
            writer.close();
            System.out.println("Successfully saved");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
