package Main;

import java.awt.*;
import Graphics.Screen;

public class Global {

    public static int score,money,lives,time;
    public static Cards[] cards = new Cards[3];
    public static boolean[] active = new boolean[7];
    public static final String localPath = System.getProperty("user.dir");


    public static void HUDcheck(){
        if(Main.game.getManager().getPlayer()!=null){
            active = new boolean[7];
            int temp = (int)Main.game.getManager().getPlayer().getXVelocity();
            for(int i = 1; temp>4; i++){
                active[i] = true;
                temp--;
// FIX SPEED METER VVVVV TEMP SOLUTION!!!!!
                if(active[1]){
                    active[0] = true;
                }
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

    public static void addCard(Cards card){
        int totals = 0;
        for(Cards selected : cards){
            if(selected!=null){
                totals++;
            } else {
                selected = card;
                return;
            }
        }
        if(totals==3){
            lives++;
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
        switch (card){
            case STAR_CARD:
                return Main.game.getSpritesLoader().getCards()[3];
            case FLOWER_CARD:
                return Main.game.getSpritesLoader().getCards()[2];
            case MUSHROOM_CARD:
                return Main.game.getSpritesLoader().getCards()[1];
            default:
                return Main.game.getSpritesLoader().getCards()[0];
        }
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
