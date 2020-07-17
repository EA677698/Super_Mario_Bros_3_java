package Graphics;

import Main.Global;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class SpritesLoader {

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

 public SpritesLoader(){
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
   mushrooms[0] = ImageIO.read(new File(Global.localPath+"\\assets\\Entities\\PowerUps\\1up.png"));
   mushrooms[1] = ImageIO.read(new File(Global.localPath + "\\assets\\Entities\\PowerUps\\bigMushroom.png"));
  } catch (Exception e){
   e.printStackTrace();
  }
 }

 public Image[] getSmallMario(){
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

}
