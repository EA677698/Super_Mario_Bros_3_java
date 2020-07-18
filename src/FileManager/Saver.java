package FileManager;

import Elements.Manager;
import Main.Global;

import java.io.*;

public class Saver {

    public static int world;
    public static int level;

    public static void createLevel(){
        try {
            FileOutputStream file = new FileOutputStream(Global.localPath + "\\levels\\" + world + "-" + level + ".mb3");
            ObjectOutputStream writer = new ObjectOutputStream(file);
            writer.writeObject(Manager.saveObjects);
            writer.close();
            System.out.println("Successfully saved");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
