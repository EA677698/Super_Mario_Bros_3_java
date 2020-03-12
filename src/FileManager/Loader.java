package FileManager;

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
import Main.Global;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {

    public static void loadLevel(String FileName){
        try {
            File myObj = new File(Global.localPath+"\\levels\\"+FileName+".mb3");
            Scanner myReader = new Scanner(myObj);
            unloadLevel();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int count = 0;
                for(int i = 0; i<data.length(); i++){
                    if(data.substring(i,i+1).equals(",")){
                        count++;
                    }
                }
                String[] parameters = new String[count];
                for(int i = 0; i<parameters.length; i++){
                        parameters[i] = data.substring(0,data.indexOf(","));
                        data = data.substring(data.indexOf(",")+1);
                }
                spawnElements(parameters);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void spawnElements(String[] parameters){
        switch (parameters[0]){
            case "Mario":
                Manager.ents.add(new Player(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Integer.parseInt(parameters[4]),Integer.parseInt(parameters[5]),Integer.parseInt(parameters[6]),Integer.parseInt(parameters[7]),
                        Double.parseDouble(parameters[8]),Double.parseDouble(parameters[9]),Boolean.parseBoolean(parameters[10]),
                        Boolean.parseBoolean(parameters[11])));
                break;
            case "Goomba":
                Manager.ents.add(new Goomba(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Integer.parseInt(parameters[4]),Integer.parseInt(parameters[5]),Integer.parseInt(parameters[6]),Integer.parseInt(parameters[7]),
                        Double.parseDouble(parameters[8]),Double.parseDouble(parameters[9]),Integer.parseInt(parameters[10]),Boolean.parseBoolean(parameters[11]),
                        Boolean.parseBoolean(parameters[12])));
                break;
            case "KoopaTroopa":
                Manager.ents.add(new KoopaTroopa(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Integer.parseInt(parameters[4]),Integer.parseInt(parameters[5]),Integer.parseInt(parameters[6]),Integer.parseInt(parameters[7]),
                        Double.parseDouble(parameters[8]),Double.parseDouble(parameters[9]),Integer.parseInt(parameters[10]),Boolean.parseBoolean(parameters[11]),
                        Boolean.parseBoolean(parameters[12])));
                break;
            case "HammerBro":
                Manager.ents.add(new HammerBro(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Integer.parseInt(parameters[4]),Integer.parseInt(parameters[5]),Integer.parseInt(parameters[6]),Integer.parseInt(parameters[7]),
                        Double.parseDouble(parameters[8]),Double.parseDouble(parameters[9]),Integer.parseInt(parameters[10]),Boolean.parseBoolean(parameters[11]),
                        Boolean.parseBoolean(parameters[12]),Integer.parseInt(parameters[13])));
                break;
            case "Coin":
                Manager.ents.add(new Coin(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Integer.parseInt(parameters[4]),Integer.parseInt(parameters[5]),Integer.parseInt(parameters[6]),Integer.parseInt(parameters[7]),
                        Double.parseDouble(parameters[8]),Double.parseDouble(parameters[9]),Integer.parseInt(parameters[10]),Boolean.parseBoolean(parameters[11]),
                        Boolean.parseBoolean(parameters[12])));
                break;
            case "RedMushroom":
                Manager.ents.add(new RedMushroom(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Integer.parseInt(parameters[4]),Integer.parseInt(parameters[5]),Integer.parseInt(parameters[6]),Integer.parseInt(parameters[7]),
                        Double.parseDouble(parameters[8]),Double.parseDouble(parameters[9]),Integer.parseInt(parameters[10]),Boolean.parseBoolean(parameters[11]),
                        Boolean.parseBoolean(parameters[12])));
                break;
            case "GreenMushroom":
                Manager.ents.add(new GreenMushroom(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Integer.parseInt(parameters[4]),Integer.parseInt(parameters[5]),Integer.parseInt(parameters[6]),Integer.parseInt(parameters[7]),
                        Double.parseDouble(parameters[8]),Double.parseDouble(parameters[9]),Integer.parseInt(parameters[10]),Boolean.parseBoolean(parameters[11]),
                        Boolean.parseBoolean(parameters[12])));
                break;
            case "WoodenBlock":
                Manager.tiles.add(new WoodenBlock(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Boolean.parseBoolean(parameters[4])));
                break;
            case "Platform":
                Manager.tiles.add(new Floor(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Boolean.parseBoolean(parameters[4]),Integer.parseInt(parameters[5]),Integer.parseInt(parameters[6])));
                break;
            case "BigBlock":
                Manager.tiles.add(new BigBlocks(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Boolean.parseBoolean(parameters[4]),Integer.parseInt(parameters[5]),Integer.parseInt(parameters[6]),Integer.parseInt(parameters[7])));
                break;
            case "Pipe":
                Manager.tiles.add(new Pipes(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Boolean.parseBoolean(parameters[4]),Integer.parseInt(parameters[5])));
                break;
            case "Brick":
                Manager.tiles.add(new Bricks(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Boolean.parseBoolean(parameters[4]),Boolean.parseBoolean(parameters[5])));
                break;
            case "LuckyBlock":
                Manager.tiles.add(new LuckyBlock(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Boolean.parseBoolean(parameters[4]),null));
                break;
            case "Cloud":
                Manager.tiles.add(new Clouds(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Boolean.parseBoolean(parameters[4]), Integer.parseInt(parameters[5])));
                break;
            case "Hill":
                Manager.tiles.add(new Hill(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Boolean.parseBoolean(parameters[4]), Integer.parseInt(parameters[5]), Integer.parseInt(parameters[6])));
                break;
            case "Shrub":
                Manager.tiles.add(new Shrub(Layer.valueOf(parameters[1]),new Point(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])),
                        Boolean.parseBoolean(parameters[4])));
                break;
        }
    }

    public static void unloadLevel(){
        for(Entity entity: Manager.ents){
            entity.removeFromLayer();
            Manager.ents.remove(entity);
        }
        for(Tile tile: Manager.tiles){
            tile.removeFromLayer();
            Manager.tiles.remove(tile);
        }
    }


}
