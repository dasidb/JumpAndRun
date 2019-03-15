package pakete;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

public class Charakter {

    private PImage img;
    private float positionX;
    private float positionY;


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
    public Charakter(){

    }
    public void sterben(Charakter held){
        held = null;
    }
}

