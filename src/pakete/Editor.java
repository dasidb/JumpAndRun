package pakete;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.security.Key;
import java.util.ArrayList;

public class Editor {
    private PVector veca;
    private PVector vecb;
    private PVector vecc;
    private PVector vecm;
    private ArrayList<Spike> removeSpikeList;
    private int objectIndex = 0;


    //  Spike spike = new Spike(700, 300, 500, 200, 300, 400);

    public void createObjects(float x, float y, ArrayList<Spike> spikeListe, ArrayList<Floor> floorList, PApplet p) {
        //PApplet p;

        switch (objectIndex) {

            case 0:
                PImage floorImage = p.loadImage("resources/floor.png");
               // floorList.add(new Floor(new PImage(),x,y));
                floorList.add(new Floor(floorImage,x,y));
                break;
            case 1:
            spikeListe.add(new Spike(x, y, x - 20, y + 30, x + 20, y + 30));
            break;
        }

    }
    public void changeIndex(char key){
       // if(objectIndex >= 0 && objectIndex < 10) {
            if (key == 'e' && objectIndex <= 8) {
                objectIndex++;
                System.out.println("das ist objektindex" + objectIndex);
            } if(key == 'q' && objectIndex >=1){
                objectIndex--;
                System.out.println(objectIndex);
            }
            System.out.println(key);
        }
  //  }

    public void deleteSpike(ArrayList<Spike> spikeList,float x ,float y) {
            removeSpikeList = new ArrayList<>();
        for (Spike spike : spikeList) {
            veca = new PVector(spike.getTriangleX1(), spike.getTriangleY1());

            vecb = new PVector(spike.getTriangleX2(), spike.getTriangleY2());

            vecc = new PVector(spike.getTriangleX3(), spike.getTriangleY3());

            vecm = new PVector(x, y);



            float w1 = (veca.x * (vecc.y - veca.y) + ((vecm.y) - veca.y) * (vecc.x - veca.x)
                    - ( vecm.x) * (vecc.y - veca.y))
                    / ((vecb.y - veca.y) * (vecc.x - veca.x) - (vecb.x - veca.x) * (vecc.y - veca.y));

            float w2 = (( vecm.y) - veca.y - w1 * (vecb.y - veca.y)) / (vecc.y - veca.y);

            System.out.println("wird ausgeführt \n Das ist w1 " + w1 + " das ist W2 " + w2 +" \n Das ist w1+w2 " + (w1 +w2));
            if (w1 >= 0 && w2 >= 0 && (w1 + w2) <= 1) {
               removeSpikeList.add(spike);
               System.out.println("wird ausgeführt(remove)");
           } else {

            }
        }
        for(Spike s : removeSpikeList){
            spikeList.remove(s);
        }
    }
    }

