package pakete;

import processing.core.PApplet;

import java.util.ArrayList;

public class Spike extends Hindernis{

    private float groeßeX1;
    private float groeßeY1;
    private float groeßeX2;
    private float positionY2;
    private float getPositionX3;
    private float getPositionY3;
    private ArrayList<Spike> spikeListe;

    // Getter und Setter


    public ArrayList<Spike> getSpikeListe() {
        return spikeListe;
    }

    public void setSpikeListe(ArrayList<Spike> spikeListe) {
        this.spikeListe = spikeListe;
    }

    public float getGroeßeX1() {
        return groeßeX1;
    }

    public void setGroeßeX1(float groeßeX1) {
        this.groeßeX1 = groeßeX1;
    }

    public float getGroeßeY1() {
        return groeßeY1;
    }

    public void setGroeßeY1(float groeßeY1) {
        this.groeßeY1 = groeßeY1;
    }

    public float getGroeßeX2() {
        return groeßeX2;
    }

    public void setGroeßeX2(float groeßeX2) {
        this.groeßeX2 = groeßeX2;
    }

    public float getPositionY2() {
        return positionY2;
    }

    public void setPositionY2(float positionY2) {
        this.positionY2 = positionY2;
    }

    public float getGetPositionX3() {
        return getPositionX3;
    }

    public void setGetPositionX3(float getPositionX3) {
        this.getPositionX3 = getPositionX3;
    }

    public float getGetPositionY3() {
        return getPositionY3;
    }

    public void setGetPositionY3(float getPositionY3) {
        this.getPositionY3 = getPositionY3;
    }
// Constructoren


    public Spike(float groeßeX1, float groeßeY1, float groeßeX2, float groeßeY2, float groeßeX3, float groeßeY3) {
        this.groeßeX1 = groeßeX1;
        this.groeßeY1 = groeßeY1;
        this.groeßeX2 = groeßeX2;
        this.positionY2 = groeßeY2;
        this.getPositionX3 = groeßeX3;
        this.getPositionY3 = groeßeY3;
    }

    public Spike(){

    }

    public void erschaffeSpike(PApplet p){
        spikeListe = new ArrayList<>();
        int x = 300;
        int y = 400;
        int z = 250;
        for(int i = 0; i <5; i++){
            // arg 1 mitte arg 2 links arg 3 rechts
            Spike spike1 = new Spike(x,y,x-20,y+30,x+20,y+30);

            //Spike spike1 = new Spike(50+x+z,40+z,40+x+z,20+z,60+x+z,20+z);
            spikeListe.add(spike1);
           // System.out.println("erschaffe spike");
            //p.triangle(spike1.getGroeßeX1(), spike1.getGroeßeY1(), spike1.getGroeßeX2(), spike1.getPositionY2(), spike1.getGetPositionX3(), spike1.getPositionY3);
            x += 40;
        }
        for(Spike s : spikeListe){
            p.triangle(s.getGroeßeX1(), s.getGroeßeY1(), s.getGroeßeX2(), s.getPositionY2(), s.getGetPositionX3(), s.getPositionY3);

           // System.out.println("spike liste");
        }

    }

}
