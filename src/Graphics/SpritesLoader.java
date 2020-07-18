package Graphics;

import Main.Global;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class SpritesLoader {

 //ENTITIES
    private final Image[] bigMario = new Image[23];
    private final Image[] smallMario = new Image[18];
    private final Image[] goomba = new Image[3];
    private final Image[] hammerFireBro = new Image[3];
    private final Image[] hammerBro = new Image[4];
    private final Image[] hammerBoomerangBro = new Image[4];
    private final Image[] koopaTroopa = new Image[7];
    private final Image[] coins = new Image[3];
    private final Image[] fireballs = new Image[4];
    private final Image[] mushrooms = new Image[2];
 //TILES
    private final Image[] bricks = new Image[5];
    private final Image[] luckyBlocks = new Image[5];
    private final Image[] pipes = new Image[4];
    private final Image[] bb = new Image[9];
    private final Image[] bg = new Image[9];
    private final Image[] bp = new Image[9];
    private final Image[] bw = new Image[9];
    private final Image[] clouds = new Image[6];
    private final Image[] floor = new Image[6];
    private final Image[] hill1 = new Image[4];
    private final Image[] hill2 = new Image[4];
    private final Image[] shrub = new Image[1];
    private final Image[] woodenBlock = new Image[1];

    public SpritesLoader() {
        loadEntitySprites();
        loadTileSprites();
    }

    public void loadTileSprites() {
        try {
            woodenBlock[0] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\blocks\\block1.png"));
            shrub[0] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\other\\shrub.png"));
            for (int i = 1; i < 7; i++) {
                floor[i - 1] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\platforms\\1-" + i + ".png"));
                clouds[i - 1] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\clouds\\1-" + i + ".png"));
            }
            for (int i = 1; i < 6; i++) {
                if(i==5){
                    bricks[i-1] = ImageIO.read(new File(Global.localPath+"\\assets\\Tiles\\blocks\\lb5.png"));
                } else {
                    bricks[i - 1] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\blocks\\brick" + i + ".png"));
                }
                luckyBlocks[i - 1] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\blocks\\lb" + i + ".png"));
            }
            for (int i = 1; i < 5; i++) {
                hill2[i - 1] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\hills\\2-" + i + ".png"));
                hill1[i - 1] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\hills\\1-" + i + ".png"));
                pipes[i - 1] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\pipes\\" + i + ".png"));
            }
            int index = 0;
                for (int i = 1; i < 4; i++) {
                    for (int e = 1; e < 4; e++) {
                        bb[index] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\blocks\\bb" + e + "-" + i + ".png"));
                        bg[index] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\blocks\\bg" + e + "-" + i + ".png"));
                        bp[index] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\blocks\\bp" + e + "-" + i + ".png"));
                        bw[index] = ImageIO.read(new File(Global.localPath + "\\assets\\Tiles\\blocks\\bw" + e + "-" + i + ".png"));
                        index++;
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEntitySprites() {
        try {
            for (int i = 0; i < smallMario.length; i++) {
                smallMario[i] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\Mario\\smallMario\\" + (i + 1) + ".png"));
            }
            for (int i = 0; i < bigMario.length; i++) {
                bigMario[i] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\Mario\\bigMario\\" + (i + 1) + ".png"));
            }
            for (int i = 0; i < goomba.length; i++) {
                goomba[i] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\Minions\\Goomba\\" + (i + 1) + ".png"));
            }
            for (int i = 0; i < 4; i++) {
                hammerBro[i] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\Minions\\HammerBro\\" + 1 + "-" + (i + 1) + ".png"));
                hammerBoomerangBro[i] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\Minions\\HammerBro\\" + 2 + "-" + (i + 1) + ".png"));
            }
            for (int i = 0; i < 3; i++) {
                hammerFireBro[i] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\Minions\\HammerBro\\" + 3 + "-" + (i + 1) + ".png"));
            }
            for (int i = 0; i < koopaTroopa.length; i++) {
                koopaTroopa[i] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\Minions\\Koopatroopa\\" + (i + 1) + ".png"));
            }
            for (int i = 0; i < coins.length; i++) {
                coins[i] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\Coin\\" + (i + 1) + ".png"));
            }
            for (int i = 0; i < 4; i++) {
                fireballs[i] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\Minions\\Fireball\\" + (i + 1) + ".png"));
            }
            mushrooms[0] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\PowerUps\\1up.png"));
            mushrooms[1] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\PowerUps\\bigMushroom.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image[] getSmallMario() {
        return smallMario;
    }

    public Image[] getBigMario() {
        return bigMario;
    }

    public Image[] getGoomba() {
        return goomba;
    }

    public Image[] getHammerFireBro() {
        return hammerFireBro;
    }

    public Image[] getHammerBro() {
        return hammerBro;
    }

    public Image[] getHammerBoomerangBro() {
        return hammerBoomerangBro;
    }

    public Image[] getKoopaTroopa() {
        return koopaTroopa;
    }

    public Image[] getCoins() {
        return coins;
    }

    public Image[] getFireballs() {
        return fireballs;
    }

    public Image[] getMushrooms() {
        return mushrooms;
    }

    public Image[] getBricks() {
        return bricks;
    }

    public Image[] getLuckyBlocks() {
        return luckyBlocks;
    }

    public Image[] getPipes() {
        return pipes;
    }

    public Image[] getBb() {
        return bb;
    }

    public Image[] getBg() {
        return bg;
    }

    public Image[] getBp() {
        return bp;
    }

    public Image[] getBw() {
        return bw;
    }

    public Image[] getClouds() {
        return clouds;
    }

    public Image[] getFloor() {
        return floor;
    }

    public Image[] getHill1() {
        return hill1;
    }

    public Image[] getHill2() {
        return hill2;
    }

    public Image[] getShrub() {
        return shrub;
    }

    public Image[] getWoodenBlock() {
        return woodenBlock;
    }

}
