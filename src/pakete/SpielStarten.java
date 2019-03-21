package pakete;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Vector;

public class SpielStarten extends  PApplet {
    Charakter held;
    Hindernis hindernis;
    Spike spike;
    SpielWelt welt;
    PVector veca;
    PVector vecb;
    PVector vecc;
    PVector vecp;
    boolean isColiding;


    public static void main(String[] args) {
        System.out.println("hier ist die main klasse");
        PApplet.main(SpielStarten.class, args);
        System.out.println("hier ist die main klasse zuende");

    }

    public SpielStarten() {
        System.out.println("hier ist der consstruktor");
    }

    public void ersschaffeObjekte(){
        erschaffeHeld();
       // erschaffeHindernisse();
        erschaffeSpike();
        erschaffeWelt();


    }
    public void erschaffeHeld() {
        System.out.println("hier ist erschaffe held");
        PImage heldImage = loadImage("resources/held.png");
        held = new Charakter(heldImage, 1, 1);
        //spike = new Spike();
    }
    public void respawn(PImage p){
        p = loadImage("resources/held.png");
        held.setImg(p);
    }

    public void sterben(PImage p){
        p = loadImage("resources/spike.png");
        held.setImg(p);
    }
    public void erschaffeWelt(){
        welt = new SpielWelt();
    }

    public void spielStarten() {
        System.out.println("Spielstarten erreicht");

        held.setPositionX(100);
    }

    public void setup() {
        super.setup();
        System.out.println("hier ist setup");
        ersschaffeObjekte();
      //  erschaffeHeld();
       // erschaffeHindernisse();
        System.out.println(held);
        background(0);
        frameRate(30);
        loop();


    }

    public void settings() {
        super.settings();
        ;
        size(800, 800);
        System.out.println("hier wird settings aufgerufen");

    }

    public void draw() {
        clear();

        image(held.getImg(), held.getPositionX(), held.getPositionY());

        //for(Hindernis c : hindernis.getHindernisListe()){
         //   image(c.getImg(),c.getPositionX(),c.getPositionY());
       // }




        spike.erschaffeSpike(this);
        gravity();
       // kollision();

       for(int i = 0; i < spike.getSpikeListe().size(); i++) {

            if (kollision(spike.getSpikeListe().get(i)) == true) {
                // System.out.println("es ist trueeeee");

            }
        }
       if(isColiding == true){

           sterben(held.getImg());

       }

        }

    //System.out.println(get(310,455));




    public boolean kollision(Spike spike){

        veca = new PVector(spike.getGroeßeX1(),spike.getGroeßeY1());

        vecb = new PVector(spike.getGroeßeX2(),spike.getPositionY2());

        vecc = new PVector(spike.getGetPositionX3(),spike.getGetPositionY3());

        vecp = new PVector(held.getPositionX(),held.getPositionY());

        float w1 = (veca.x*(vecc.y - veca.y) + (vecp.y - veca.y)*(vecc.x -veca.x) - vecp.x*(vecc.y - veca.y))/
                ((vecb.y - veca.y)*(vecc.x -veca.x) - (vecb.x -veca.x)*(vecc.y - veca.y));

        float w2 = (vecp.y - veca.y - w1*(vecb.y -veca.y)) /
                (vecc.y - veca.y);

      //  System.out.println("w1 "+ w1);
       // System.out.println("w2 " +w2);

        if(w1 >= 0 && w2 >= 0 && (w1+w2) <= 1){
            isColiding = true;
        }else{
            //sColiding = false;
        }
        System.out.println(isColiding);
        return isColiding;



        /*
            System.out.println(held.getPositionX());
            for(int i = 0; i <spike.getSpikeListe().size(); i++ ){
                if(held.getPositionX() == spike.getSpikeListe().get(i).getGetPositionX3()){
                    System.out.println("treffer");
                }*/
            }


    public void gravity(){
        if(held.getPositionY() <= 500){
            held.setPositionY(held.getPositionY() + welt.getGravity());

        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);

        if (keyPressed) {
            if(key =='r' || key =='R'){
                held.setPositionX(1);
                held.setPositionY(1);
                isColiding = false;
                respawn(held.getImg());
            }
            if (key == 'd' || key == 'D') {
                float x = held.getPositionX() + 10;
                float y = 20;
                held.setPositionX(x);

            }
            if (key == 'a' || key == 'A') {
                float x = held.getPositionX() - 10;
                float y = 20;
                held.setPositionX(x);

            }
            if (key == 'w' || key == 'W') {
                float x = held.getPositionY() - 20;
                float y = 20;
                held.setPositionY(x);

            }
            if (key == 's' || key == 'S') {
                float x = held.getPositionY() + 20;
                float y = 20;
                held.setPositionY(x);
            }
        }
    }
   /* public ArrayList<Hindernis> erschaffeHindernisse(){
        ArrayList<Hindernis> hindernisListe = new ArrayList<>();
        // ggf wirds hier fatal


        PImage hindernissBild = loadImage("resources/spike.png");
        for(int i =0 ; i < 5; i++) {
            hindernis = new Hindernis(hindernissBild, (float) Math.random() * 100, (float) Math.random() * 100);

            hindernisListe.add(hindernis);
        }
        hindernis.setHindernisListe(hindernisListe);
        return hindernisListe;
    } */

    public void erschaffeSpike(){
        spike = new Spike();
    }


}





