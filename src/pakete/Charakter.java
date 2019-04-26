package pakete;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class Charakter {

    private PImage img;
    private float positionX;
    private float positionY;
    private ArrayList<PVector> pixelList;
    private int t;
    private int jumpCount = 2;
    private float jumpTime = 1 / 30F;
    private float velocity = 20;
    private boolean jumping;
    private boolean maxJump;
    private float jumpHeight;
    private boolean falling;
    private int test;
    private boolean cooliding = false;

    public boolean isCooliding() {
        return cooliding;
    }

    public void setCooliding(boolean cooliding) {
        this.cooliding = cooliding;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public float getJumpTime() {
        return jumpTime;
    }

    public void setJumpTime(float jumpTime) {
        this.jumpTime = jumpTime;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isMaxJump() {
        return maxJump;
    }

    public void setMaxJump(boolean maxJump) {
        this.maxJump = maxJump;
    }

    public void setJumpHeight(float jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int getJumpCount() {
        return jumpCount;
    }

    public void setJumpCount(int jumpCount) {
        this.jumpCount = jumpCount;
    }

    public float getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public ArrayList<PVector> getPixelList() {
        return pixelList;
    }

    public void setPixelList(ArrayList<PVector> pixelList) {
        this.pixelList = pixelList;
    }


    public PImage getImg() {
        return img;
    }

    public void setImg(PImage img) {
        this.img = img;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public Charakter(PImage img, float positionX, float positionY) {


        this.img = img;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Charakter() {

    }

    public void sterben(Charakter held) {
        held = null;
    }

    public void springen(SpielStarten s) {

        if (jumping == true && maxJump == false && jumpCount > 0) {
            setCooliding(false);
            s.setGravity(8F);
            test = 1;
            if (jumpTime + velocity > s.getGravity()) {
                setPositionY(getPositionY() - velocity - jumpTime);
                jumpTime -= 0.5F;

                if (jumpTime + velocity <= s.getGravity()) {
                    maxJump = true;
                    s.setGravity(0.1F);
                    test = 0;
                }
            }
            falling = true;


        }

        falling = false;


    }

    // rest jump hinzufügen ggf jumptime/90
    public void restJump() {
        if (isMaxJump() == false) {

        }
    }
        public void getNonTransparentPixel () {

            pixelList = new ArrayList<PVector>();
            for (int i = 0; i < img.height; i++) {

                for (int c = 0; c < img.width; c++) {


                    t = img.get(c, i);

                    int alpha = (t >>> 24);

                    //int alpha = ( t >> 24) & 255;
                    //                        11111111
                    //System.out.println(alpha);
                    //16777215
                    if (alpha > 100) {
                        PVector p = new PVector(c, i);
                        pixelList.add(p);

                    }

                    //System.out.println("das ist C " + c + "\n" + "das ist i" + i);


                }
            }

        } //11111111101001010100111101001111
        //11111111001100111000000000100111

    }

    	
    


