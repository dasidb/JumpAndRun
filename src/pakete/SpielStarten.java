package pakete;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SpielStarten extends  PApplet {
    Charakter held;
    Hindernis hindernis;

    public static void main(String[] args) {
        System.out.println("hier ist die main klasse");
        PApplet.main(SpielStarten.class, args);
        System.out.println("hier ist die main klasse zuende");

    }

    public SpielStarten() {
        System.out.println("hier ist der consstruktor");
    }

    public void erschaffeHeld() {
        System.out.println("hier ist erschaffe held");
        PImage heldImage = loadImage("resources/held.png");
        held = new Charakter(heldImage, 1, 1);

    }

    public void spielStarten() {
        System.out.println("Spielstarten erreicht");

        held.setPositionX(100);
    }

    public void setup() {
        super.setup();
        System.out.println("hier ist setup");

        erschaffeHeld();
        erschaffeHindernisse();
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
        for(Hindernis c : hindernis.getHindernisListe()){
            image(c.getImg(),c.getPositionX(),c.getPositionY());
        }

    }


    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (keyPressed) {
            if (key == 'd' || key == 'D') {
                float x = held.getPositionX() + 20;
                float y = 20;
                held.setPositionX(x);

            }
            if (key == 'a' || key == 'A') {
                float x = held.getPositionX() - 20;
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
    public ArrayList<Hindernis> erschaffeHindernisse(){
        ArrayList<Hindernis> hindernisListe = new ArrayList<>();
        // ggf wirds hier fatal


        PImage hindernissBild = loadImage("resources/spike.png");
        for(int i =0 ; i < 5; i++) {
            hindernis = new Hindernis(hindernissBild, (float) Math.random() * 100, (float) Math.random() * 100);

            hindernisListe.add(hindernis);
        }
        hindernis.setHindernisListe(hindernisListe);
        return hindernisListe;
    }


}





