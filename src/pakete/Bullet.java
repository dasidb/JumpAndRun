package pakete;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

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
    private ArrayList<Bullet> bulletIDList = new ArrayList<>();
    private ArrayList<PVector> bulletPixxelList = new ArrayList<>();

    public ArrayList<PVector> getBulletPixxelList() {
        return bulletPixxelList;
    }

    public void setBulletPixxelList(ArrayList<PVector> bulletPixxelList) {
        this.bulletPixxelList = bulletPixxelList;
    }

    public ArrayList<Bullet> getBulletIDList() {
        return bulletIDList;
    }

    public void setBulletIDList(ArrayList<Bullet> bulletIDList) {
        this.bulletIDList = bulletIDList;
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

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
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
        bulletPixxelList();

    }

    public void bulletMove(Bullet b) {

        b.setPositionX(b.getPositionX() + b.getVelocity());
    }

    public void bulletPixxelList(){

        for(int i = 0; i < this.getImg().height; i++){
            for(int v = 0; v < this.getImg().width; v++){
                PVector p = new PVector(i,v);
                bulletPixxelList.add(p);
            }
        }

    }

}
