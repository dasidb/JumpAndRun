package pakete;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Bullet {
    private PImage img;
    private float positionX;
    private float positionY;
    private int schaden;
    private float travelDistance;
    private boolean maxBullets;
    private boolean shoot;
    private float velocity;
    private boolean hit;

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

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public int getSchaden() {
        return schaden;
    }

    public void setSchaden(int schaden) {
        this.schaden = schaden;
    }

    public Bullet() {

    }

    public Bullet(PImage img, float positionX, float positionY, int schaden, float velocity, PApplet p) {
        this.img = img;
        this.positionX = positionX;
        this.positionY = positionY;
        this.schaden = schaden;
        this.velocity = velocity;
       // p.image(this.img, this.positionX, this.positionY);
    }

    public void bulletMove(Bullet b) {

        b.setPositionX(b.getPositionX() + b.getVelocity());
    }
}
