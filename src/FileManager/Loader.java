package FileManager;

import Elements.Elements;
import Elements.Entities.Enemies.Goomba;
import Elements.Entities.Enemies.HammerBro;
import Elements.Entities.Enemies.KoopaTroopa;
import Elements.Entities.Entity;
import Elements.Entities.Mario.Player;
import Elements.Entities.NotLiving.Coin;
import Elements.Entities.NotLiving.GreenMushroom;
import Elements.Entities.NotLiving.RedMushroom;
import Elements.Layer;
import Elements.Manager;
import Elements.Tiles.*;
import Elements.Tiles.Interactables.Bricks;
import Elements.Tiles.Interactables.LuckyBlock;
import Elements.Tiles.Interactables.Pipes;
import Elements.Tiles.Tools.Clip;
import Elements.Tiles.Tools.Trigger;
import Level.Level;
import Main.GameTick;
import Main.Global;
import Graphics.Screen;
import Main.Main;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Loader {

    public static void loadLevel(String fileName) {
        try {
            FileInputStream saveFile = new FileInputStream(Global.localPath + "\\levels\\" + fileName + ".mb3");
            ObjectInputStream load = new ObjectInputStream(saveFile);
            Main.game.getManager().setSavedObjects((CopyOnWriteArrayList<Elements>) load.readObject());
            for (Elements obj : Main.game.getManager().getSavedObjects()) {
                obj.reloadLayer();
                if (obj instanceof Entity) {
                    Main.game.getManager().getEnts().add((Entity) obj);
                } else if (obj instanceof Tile) {
                    Main.game.getManager().getTiles().add((Tile) obj);
                } else if (obj instanceof Level) {
                    Main.game.getManager().setLevel((Level) obj);
                    Main.game.getManager().getLevel().changeMusic(Main.game.getManager().getLevel().getBgm());
                }
            }
            load.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
