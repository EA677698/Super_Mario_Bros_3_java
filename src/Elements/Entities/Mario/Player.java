package Elements.Entities.Mario;

import Elements.Entities.Entity;
import Elements.Layer;
import Main.Main;
import Settings.Controls;
import java.awt.*;

public class Player extends Entity {

    private Powers power;
    private static final int WALK_SPEED = 4;
    private static final int MAX_SPEED = 10;
    private static final int MAX_JUMP_SPEED = -11;
    private int previousDirection;
    private double movementTimer = System.nanoTime();
    private double slowDownTimer = System.nanoTime();
    private double spriteTimer = System.nanoTime();
    private double speedTimer = System.nanoTime();
    private double runTimer = System.nanoTime();
    private double deathTimer = System.nanoTime();
    private double jumpTimer = System.nanoTime();
    private boolean flag, middle, start, middleH, startH, initialJump;

    public Player(Layer layer, Point coordinates, int width, int height, boolean hasCollision) {
        super(layer, coordinates, width, height, hasCollision);
        power = Powers.SMALL;
        this.setEntityName("Mario");
        this.setXVelocity(0);
        this.setYVelocity(0);
    }

    public Player(Layer layer, Point coordinates, int width, int height, int life, int damage, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, hasGravity, hasCollision);
        power = Powers.SMALL;
        this.setEntityName("Mario");
        this.setXVelocity(0);
        this.setYVelocity(0);
    }

    public Player(Layer layer, Point coordinates, int width, int height, int life, int damage, double speed, double gravity, boolean hasGravity, boolean hasCollision) {
        super(layer, coordinates, width, height, life, damage, speed, gravity, hasGravity, hasCollision);
        power = Powers.SMALL;
        this.setEntityName("Mario");
        this.setXVelocity(0);
        this.setYVelocity(0);
    }

    @Override
    public void tick() {
        super.tick();
        if(!isDead()){
            sideScrolling();
            if(System.nanoTime()-movementTimer>10000000){
                addX((int)(getXVelocity()*getDirection()));
                if(Controls.jump){
                    if(initialJump){
                        setYVelocity(MAX_JUMP_SPEED);
                        setHasGravity(false);
                        setSprite(3);
                        initialJump = false;
                    }
                    jump();
                } else {
                    setHasGravity(true);
                }
                movementTimer = System.nanoTime();
            }
            if(!isOffGround()){
                initialJump = true;
            }
            if(!isOffGround()&&getXVelocity()>0){
                changeSprite();
            } else if(!isOffGround()&&getXVelocity()==0) {
                setSprite(0);
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
            }
        } else {
            death();
        }
    }

    private void jump(){
        addY((int) getYVelocity());
        if(System.nanoTime()-jumpTimer>37000000){
            if(getYVelocity()<0){
                setYVelocity(getYVelocity()+1);
            } else {
                setHasGravity(true);
            }
            jumpTimer = System.nanoTime();
        }
    }

    private void walk(){
        if (!Controls.alt) {
            if(System.nanoTime()-runTimer>300000000) {
                if (getXVelocity() < WALK_SPEED) {
                    setXVelocity(getXVelocity() + 1);
                } else if (getXVelocity() > WALK_SPEED) {
                    setXVelocity(getXVelocity() - 1);
                }
                runTimer = System.nanoTime();
            }
        } else if(System.nanoTime()-speedTimer>100000000 &&!isOffGround()) {
            if(getXVelocity()<MAX_SPEED){
                setXVelocity(getXVelocity()+1);
            }
            speedTimer = System.nanoTime();
        }
    }

    private void slowDown(){
        if(System.nanoTime()-slowDownTimer>200000000){
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
            return Main.game.getSpritesLoader().getSmallMario()[getCurrentSprite()];
        } else if(getPower() == Powers.BIG){
            //setHeight(getHeight()+20);
            if(!flag){
                setHeight(120);
                addY(-40);
                flag = true;
            }
            return Main.game.getSpritesLoader().getBigMario()[getCurrentSprite()];
        }
        return null;
    }

    private void changeSprite(){
        if (System.nanoTime() - spriteTimer > 100000000*(5/(getXVelocity()+.01))) {
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
        Image[] images = new Image[Main.game.getSpritesLoader().getSmallMario().length+Main.game.getSpritesLoader().getBigMario().length];
        System.arraycopy(Main.game.getSpritesLoader().getSmallMario(), 0, images, 0, Main.game.getSpritesLoader().getSmallMario().length);
        System.arraycopy(Main.game.getSpritesLoader().getBigMario(), 0, images, Main.game.getSpritesLoader().getSmallMario().length, Main.game.getSpritesLoader().getBigMario().length);
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
