package Main;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import Elements.Manager;
import Graphics.Screen;

import javax.imageio.ImageIO;

public class Global {

    public static int score,money,lives,time;
    public static Cards[] cards = new Cards[3];
    public static boolean[] active = new boolean[7];
    public static final String localPath = System.getProperty("user.dir");



    public static void HUDcheck(){
        if(Manager.player!=null){
            active = new boolean[7];
            int temp = (int)Manager.player.getVelocity();
            for(int i = 0; temp>5; i++){
                active[i] = true;
                temp--;
            }
        }
        if(money>=100){
            money = 0;
            lives++;
        }
        if(lives>99){
            lives = 99;
        }
    }

    public static Image[] getCards(){
        Image[] ret = new Image[3];
        ret[0] = cardToImage(cards[0]);
        ret[1] = cardToImage(cards[1]);
        ret[2] = cardToImage(cards[2]);
        return ret;
    }

    public static Image cardToImage(Cards card){
        try{
            switch (card){
                case STAR_CARD:
                    return ImageIO.read(new File(localPath+"\\assets\\HUD\\starCard.png"));
                case FLOWER_CARD:
                    return ImageIO.read(new File(localPath+"\\assets\\HUD\\flowerCard.png"));
                case MUSHROOM_CARD:
                    return ImageIO.read(new File(localPath+"\\assets\\HUD\\mushroomCard.png"));
                default:
                    return ImageIO.read(new File(localPath+"\\assets\\HUD\\emptyCard.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image[] getTime(){
        Image[] timer = new Image[3];
        timer[2] = Screen.nums.get((time % 10));
        timer[1] = Screen.nums.get(time / 10 % 10);
        timer[0] = Screen.nums.get(time / 100 % 10);
        return timer;
    }

    public static Image[] getLives(){
        Image[] live = new Image[2];
        live[0] = Screen.nums.get(lives / 10);
        live[1] = Screen.nums.get(lives % 10);
        return live;
    }

    public static Image[] getScores(){
        Image[] scores = new Image[7];
        scores[6] = Screen.nums.get((score % 10));
        scores[5] = Screen.nums.get(score / 10 % 10);
        scores[4] = Screen.nums.get(score / 100 % 10);
        scores[3] = Screen.nums.get(score / 1000 % 10);
        scores[2] = Screen.nums.get(score / 10000 % 10);
        scores[1] = Screen.nums.get(score / 100000 % 10);
        scores[0] = Screen.nums.get(score / 1000000);
        return scores;
    }

    public static Image[] getMoney(){
        Image[] moneys = new Image[2];
        moneys[1] = Screen.nums.get(money / 10);
        moneys[0] = Screen.nums.get(money % 10);
        return moneys;
    }

}