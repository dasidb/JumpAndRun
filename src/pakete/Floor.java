package pakete;

import processing.core.PApplet;
import processing.core.PImage;

// create floor boolean colliding with jump method
public class Floor {
    private PImage image;
    private float posiX;
    private float posiY;

    public PImage getImage() {
        return image;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    public float getPosiX() {
        return posiX;
    }

    public void setPosiX(float posiX) {
        this.posiX = posiX;
    }

    public float getPosiY() {
        return posiY;
    }

    public void setPosiY(float posiY) {
        this.posiY = posiY;
    }

    public Floor(PImage image, float posiX, float posiY) {

        this.image = image;
        this.posiX = posiX;
        this.posiY = posiY;
    }

    public Floor(float posiX, float posiY) {

        this.posiX = posiX;
        this.posiY = posiY;
    }



    }

