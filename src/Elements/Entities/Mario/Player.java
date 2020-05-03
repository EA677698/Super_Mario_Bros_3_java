package Elements.Entities.Mario;

import Elements.Entities.Entity;
import Elements.Layer;
import Settings.Controls;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    private Powers power;
    private static final int OG_SPEED = 4;
    private static final int MAX_SPEED = 10;
    private static final int MAX_JUMP_HEIGHT = 300;
    private int previousDirection, totalYCount;
    private double movementTimer = System.nanoTime();
    private double slowDownTimer = System.nanoTime();
    private double spriteTimer = System.nanoTime();
    private double speedTimer = System.nanoTime();
    private double runTimer = System.nanoTime();
    private double deathTimer = System.nanoTime();
    private boolean flag, middle, start, middleH, startH, heightCheck, offGround;
    private Image[] smallMario, bigMario;

    public Player(Layer layer, Point coordinates, int width, int height, boolean hasCollision) {
        super(layer, coordinates, width, height, hasCollision);
        power = Powers.SMALL;
        this.setEntityName("Mario");
        this.setXVelocity(5);
        this.setYVelocity(0);
        totalYCount = 0;
    }

    public Player(Layer layer, Point coordinates, int width, int height, int life, int damage, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, hasGravity, hasCollision);
        power = Powers.SMALL;
        this.setEntityName("Mario");
        this.setXVelocity(5);
        this.setYVelocity(0);
        totalYCount = 0;
    }

    public Player(Layer layer, Point coordinates, int width, int height, int life, int damage, double speed, double gravity, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, speed, gravity, hasGravity, hasCollision);
        power = Powers.SMALL;
        this.setEntityName("Mario");
        this.setXVelocity(5);
        this.setYVelocity(0);
        totalYCount = 0;
    }

    public void initializeImages(){
        smallMario = new Image[18];
        for(int i = 0; i<smallMario.length; i++){
            try {
                smallMario[i] = ImageIO.read(new File(getLocalPath()+"\\assets\\Entities\\Mario\\smallMario\\"+(i+1)+".png"));
            } catch (IOException e) {e.printStackTrace();}
        }
        bigMario = new Image[23];
        for(int i = 0; i<bigMario.length; i++){
            try {
                bigMario[i] = ImageIO.read(new File(getLocalPath()+"\\assets\\Entities\\Mario\\bigMario\\"+(i+1)+".png"));
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(!isDead()){
            sideScrolling();
            if(System.nanoTime()-movementTimer>10000000){
                addX((int)(getXVelocity()*getDirection()));
                if(!offGround &&Controls.jump){
                    jump();
                    setSprite(3);
                } else {
                    setHasGravity(true);
                    offGround = true;
                }
                movementTimer = System.nanoTime();
            }
            if(Controls.right){
                setDirection(1);
                walk();
                previousDirection = getDirection();
            } else if(Controls.left){
                setDirection(-1);
                walk();
                previousDirection = getDirection();
            } else {
                slowDown();
                setSprite(0);
            }
            if(getYVelocity()>-1){
                totalYCount = 0;
            }
            if(getTouchingGround()>0){
                heightCheck = false;
                offGround = false;
            } else {
                setSprite(3);
            }
        } else {
            death();
        }
    }

    private void jump(){
        addY((int)getYVelocity());
        if(getXVelocity()>MAX_SPEED-2&&!heightCheck&&totalYCount<(MAX_JUMP_HEIGHT*2)){
            setYVelocity(-12);
            totalYCount += 12;
            setHasGravity(false);
        } else if(!heightCheck&&totalYCount<MAX_JUMP_HEIGHT){
            setYVelocity(-12);
            totalYCount += 12;
            setHasGravity(false);
        } else {
            heightCheck = true;
            setYVelocity(getYVelocity()+1);
            if(getYVelocity()<0){
                setHasGravity(true);
            }
        }
    }
// INVESTIGATE WALK AND SLOW DOWN AND SIDE SCROLLING METHOD
// MOVEMENT PROBLEMS ARE IN THERE
    private void walk(){
        changeSprite();
        if (!Controls.alt) {
            if(System.nanoTime()-runTimer>300000000) {
                if (getXVelocity() < OG_SPEED) {
                    setXVelocity(getXVelocity() + 1);
                } else if (getXVelocity() > OG_SPEED) {
                    setXVelocity(getXVelocity() - 1);
                }
                runTimer = System.nanoTime();
            }
        } else if(System.nanoTime()-speedTimer>100000000) {
            if(getXVelocity()<MAX_SPEED){
                setXVelocity(getXVelocity()+1);
            }
            speedTimer = System.nanoTime();
        }
    }

    private void slowDown(){
        if(System.nanoTime()-slowDownTimer>300000000){
            if(getXVelocity()>0.0){
                setXVelocity(getXVelocity()-1);
                slowDownTimer = System.nanoTime();
            }
        }
    }

    private void sideScrolling(){
        middle = false;
        start = false;
        middleH = false;
        startH = false;
        if(getLocation().y>850){
            setDead(true);
            return;
        }
        if(getLocation().y<300){
            addY(5);
            middleH = true;
        }
        if(getLocation().y>720){
            addY(-5);
            startH = true;
        }
        if(getLocation().x>=(1920/2)){
            addX((int)(getXVelocity()*-1));
            middle = true;
        }
        if(getLocation().x<=860){
            addX((int) getXVelocity());
            start = true;
        }
    }

    @Override
    public Image getSprite() {
        if(getPower()==Powers.SMALL){
            flag = false;
            setHeight(80);
            return smallMario[getCurrentSprite()];
        } else if(getPower() == Powers.BIG){
            //setHeight(getHeight()+20);
            if(!flag){
                setHeight(120);
                addY(-40);
                flag = true;
            }
            return bigMario[getCurrentSprite()];
        }
        return null;
    }

    private void changeSprite(){
        if (System.nanoTime() - spriteTimer > 100000000) {
            if (getCurrentSprite() == 1) {
                setSprite(0);
            } else {
                setSprite(1);
            }
            spriteTimer = System.nanoTime();
        }
    }

    @Override
    public Image[] getImages() {
        Image[] images = new Image[smallMario.length+bigMario.length];
        System.arraycopy(smallMario, 0, images, 0, smallMario.length);
        System.arraycopy(bigMario, 0, images, smallMario.length, bigMario.length);
        return images;
    }

    @Override
    public void death() {
        setSprite(9);
        if(System.nanoTime()-deathTimer>10000000){
            addY(10);
            deathTimer = System.nanoTime();
        }
    }

    public Powers getPower() {
        return power;
    }

    public void setPower(Powers power) {
        this.power = power;
    }

    public boolean isMiddle() {
        return middle;
    }

    public void setMiddle(boolean middle) {
        this.middle = middle;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isMiddleH() {
        return middleH;
    }

    public void setMiddleH(boolean middleH) {
        this.middleH = middleH;
    }

    public boolean isStartH() {
        return startH;
    }

    public void setStartH(boolean startH) {
        this.startH = startH;
    }
}
