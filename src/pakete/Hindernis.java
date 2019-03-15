package pakete;

import processing.core.PImage;

import java.util.ArrayList;

public class Hindernis {
    private PImage img;
    private float positionX;
    private float positionY;
    private ArrayList<Hindernis> hindernisListe;

    public ArrayList<Hindernis> getHindernisListe() {
        return hindernisListe;
    }

    public void setHindernisListe(ArrayList<Hindernis> hindernisListe) {
        this.hindernisListe = hindernisListe;
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

    public Hindernis(PImage img, float positionX, float positionY) {
        this.img = img;
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
