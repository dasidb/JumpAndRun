package pakete;

import processing.core.PImage;

import java.util.ArrayList;

public class Enemy extends Charakter{
    private ArrayList<Enemy> enemyList;
    public void sterben(){

    }
    public Enemy(){

    }
    public Enemy(PImage img, float positionX, float positionY){
        super(img,positionX,positionY);
    }


    public void springen(SpielWelt spielWelt){

    }
}
