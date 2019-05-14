package pakete;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class Editor {
    private PVector veca;
    private PVector vecb;
    private PVector vecc;
    private PVector vecm;
    private ArrayList<Spike> removeSpikeList;
    private int objectIndex = 0;
    ArrayList<Integer> gridList;
    private ArrayList<Floor> removeFloorList;

    public ArrayList<Integer> getGridList() {
        return gridList;
    }

    public void setGridList(ArrayList<Integer> gridList) {
        this.gridList = gridList;
    }


    //  Spike spike = new Spike(700, 300, 500, 200, 300, 400);

    public void createObjects(float x, float y, ArrayList<Spike> spikeListe, ArrayList<Floor> floorList, PApplet p) {
        //PApplet p;

        switch (objectIndex) {

            case 0:
                PImage floorImage = p.loadImage("resources/floor.png");
               // floorList.add(new Floor(new PImage(),x,y));
                floorList.add(new Floor (floorImage,x,y));


                break;
            case 1:
                y = y -30;
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

    public void deleteSpike(ArrayList<Spike> spikeList,float x ,float y, ArrayList<Floor> floorList) {


            removeFloorList = new ArrayList<>();
            removeSpikeList = new ArrayList<>();


        for (Spike spike : spikeList) {
            veca = new PVector(spike.getPositionX(), spike.getPositionY());

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


        for(Floor floor : floorList){
            System.out.println("kommt an");
            if(floor.getPositionX() < x && x < (floor.getPositionX() + floor.getImage().width) && floor.getPositionY() < y && y < (floor.getPositionY() + floor.getImage().height)){
                removeFloorList.add(floor);
                System.out.println("test");
            }

        }
        for(Floor f : removeFloorList){
            floorList.remove(f);
        }


    }

    public void createGrid(PApplet p, int scale){
        gridList = new ArrayList<>();
        for(int i = 0; i < p.height; i += scale){
            gridList.add(i);
        }
        System.out.println(gridList.size());

    }

    public void showGrid(PApplet p){
        p.stroke(163,190,190,50);
        //strokeWeight(20);
        for(Integer i : getGridList()){

            p.line(0,i,i+p.width,i);
            p.line(i,0,i,i+p.height);
        }
    }
    }

