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
    Placeable previewObject;
    private PApplet pApplet;
    private ArrayList<Spike> removeSpikeList;
    private int objectIndex = 0;
    ArrayList<Integer> gridList;
    private ArrayList<Floor> removeFloorList;
    private ArrayList<Placeable> placeableList;
    private ArrayList<Placeable> removePlacableList;

    public ArrayList<Integer> getGridList() {
        return gridList;
    }

    public Editor(PApplet pApplet,ArrayList<Placeable> placeableList){
        this.pApplet = pApplet;
        this.placeableList = placeableList;
       // placeableList.add(previewObject);
    }
    public Editor(){

    }
    public void setGridList(ArrayList<Integer> gridList) {
        this.gridList = gridList;
    }


    //  Spike spike = new Spike(700, 300, 500, 200, 300, 400);

    public void createObjects(float x, float y) {
        //PApplet p;

        switch (objectIndex) {

            case 0:
                PImage floorImage = pApplet.loadImage("resources/floor.png");
               // floorList.add(new Floor(new PImage(),x,y));
                placeableList.add(new Floor (floorImage,x,y));
                System.out.println("CASE 0 FLOOR CREATE OBJECT " + objectIndex);


                break;
            case 1:
                //y = y -30;
          //  spikeListe.add(new Spike(x, y, x - 20, y + 30, x + 20, y + 30));
            placeableList.add(new Spike(x,y));
            System.out.println("CASE 1 SPIKE CREATE OBJECT " + objectIndex);
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
        previewObjectshow(200,300);
        }
  //  }

    public void deleteSpike(ArrayList<Spike> spikeList,float x ,float y, ArrayList<Floor> floorList) {


            removeFloorList = new ArrayList<>();
            removeSpikeList = new ArrayList<>();
            removePlacableList = new ArrayList<>();


        for (Placeable spike : placeableList) {
            if (spike instanceof Spike) {
                veca = new PVector(spike.getPositionX(), spike.getPositionY());

                vecb = new PVector(((Spike) spike).getTriangleX2(), ((Spike) spike).getTriangleY2());

                vecc = new PVector(((Spike) spike).getTriangleX3(), ((Spike) spike).getTriangleY3());

                vecm = new PVector(x, y);


                float w1 = (veca.x * (vecc.y - veca.y) + ((vecm.y) - veca.y) * (vecc.x - veca.x)
                        - (vecm.x) * (vecc.y - veca.y))
                        / ((vecb.y - veca.y) * (vecc.x - veca.x) - (vecb.x - veca.x) * (vecc.y - veca.y));

                float w2 = ((vecm.y) - veca.y - w1 * (vecb.y - veca.y)) / (vecc.y - veca.y);

                System.out.println("wird ausgeführt \n Das ist w1 " + w1 + " das ist W2 " + w2 + " \n Das ist w1+w2 " + (w1 + w2));
                if (w1 >= 0 && w2 >= 0 && (w1 + w2) <= 1) {
                    removePlacableList.add(spike);
                    System.out.println("wird ausgeführt(remove)");
                } else {

                }
            }
        }
            // ggf in einer abfrage mit floor
       //     for (Spike s : removeSpikeList) {
         //       spikeList.remove(s);
           // }

        //ggf wichtig herstellen
    //        for (Placeable s : removePlacableList) {
      //         if(s != previewObject) {
        //           placeableList.remove(s);
         //          System.out.println(removePlacableList.size());
         //      }
        //    }


     //   for(Floor floor : floorList){
            for(Placeable floor : placeableList){
                if(floor instanceof Floor) {
                    System.out.println("kommt an");
                    if (floor.getPositionX() < x && x < (floor.getPositionX() + floor.getImage().width) && floor.getPositionY() < y && y < (floor.getPositionY() + floor.getImage().height)) {
                        removePlacableList.add(floor);
                        System.out.println("test1");
                    }
                }

        }
     //   for(Floor f : removeFloorList){
      //      floorList.remove(f);
       // }

        for (Placeable s : removePlacableList) {
            if(s != previewObject) {
                placeableList.remove(s);
                System.out.println(removePlacableList.size());
            }
        }


    }

    public void createGrid(int scale){
        gridList = new ArrayList<>();
        for(int i = 0; i < pApplet.height; i += scale){
            gridList.add(i);
        }
        System.out.println(gridList.size());

    }

    public void showGrid(){
        pApplet.stroke(163,190,190,50);
        //strokeWeight(20);
        for(Integer i : getGridList()){

            pApplet.line(0,i,i+pApplet.width,i);
            pApplet.line(i,0,i,i+pApplet.height);
        }
    }
    public void previewObjectshow(float x, float y){

        switch(objectIndex) {



            case 0:
                placeableList.remove(previewObject);
                PImage floorImage = pApplet.loadImage("resources/floor.png");
                previewObject = new Floor(floorImage,800,800);
                System.out.println(placeableList.size() + "Size");
                placeableList.add(previewObject);
                System.out.println(placeableList.size() + "Size");
                System.out.println("CASE 0 FLOOR PREVIEW OBJECT "+ objectIndex);


                break;

            case 1:
                placeableList.remove(previewObject);
                previewObject = new Spike();
                placeableList.add(previewObject);
                System.out.println("CASE 1 SPIKE PREVIEW OBJECT " + objectIndex);






                break;
        }
    }
    public void testdraw(float x, float y){
        if(previewObject != null) {
            previewObject.setPositionX(x);
            previewObject.setPositionY(y);

        }
        if(previewObject instanceof Spike){
            ((Spike)previewObject).setTriangleX2(previewObject.getPositionX()-20);
            ((Spike)previewObject).setTriangleY2(previewObject.getPositionY()+30);
            ((Spike)previewObject).setTriangleX3(previewObject.getPositionX()+20);
            ((Spike)previewObject).setTriangleY3(previewObject.getPositionY()+30);
        }

    }
    }

