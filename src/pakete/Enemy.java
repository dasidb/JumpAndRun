package pakete;

import processing.core.PImage;

import java.util.ArrayList;

public class Enemy extends Charakter{
    private ArrayList<Enemy> enemyList;
    int setmovement = 0;
    public void sterben(){

    }
    public Enemy(){

    }
    public Enemy(PImage img, float positionX, float positionY){
        super(img,positionX,positionY);
        this.setLife(20);
    }


    public void springen(SpielWelt spielWelt){

    }
    @Override
    public void move(){
        super.move();
        if(setmovement == 1 ) {
            this.setMoveLeft(true);


            if (this.getPositionX() < 50) {
                setmovement = 0;
                this.setMoveLeft(false);
            }
        }
        if(setmovement == 0){
            this.setMoveRight(true);

            if(this.getPositionX() > 500){
                setmovement = 1;
                this.setMoveRight(false);
                // TODO: 06.06.2019 wenn move left dann right implizit verbieten

            }
        }
    }
    public void movePattern() {

        if(setmovement == 1 ) {
            this.setMoveLeft(true);

            if (this.getPositionX() < 50) {
                setmovement = 0;
            }
        }
            if(setmovement == 0){
                this.setMoveRight(true);

                if(this.getPositionX() > 700){
                    setmovement = 1;

                }
            }



    }
}
