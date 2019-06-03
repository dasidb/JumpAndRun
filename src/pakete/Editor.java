package pakete;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.data.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Editor {

    int restartCounter = 0;
    private PVector veca;
    private PVector vecb;
    private PVector vecc;
    private PVector vecm;
    Placeable previewObject;
    private PApplet pApplet;
    private SpielStarten spielStarten;
    private ArrayList<Spike> removeSpikeList;
    private int objectIndex = 0;
    ArrayList<Integer> gridList;
    private SpielWelt welt;
    private ArrayList<Floor> removeFloorList;
    private ArrayList<Placeable> placeableList;
    private ArrayList<Placeable> removePlacableList = new ArrayList<>();
    private boolean editorMode;
    private boolean respawn;
    private Restart restart;
    private Charakter held;
    private SaveLevel saveLevel;

    public SaveLevel getSaveLevel() {
        return saveLevel;
    }

    public void setSaveLevel(SaveLevel saveLevel) {
        this.saveLevel = saveLevel;
    }

    public boolean isRespawn() {
        return respawn;
    }

    public void setRespawn(boolean respawn) {
        this.respawn = respawn;
    }

    public boolean isEditorMode() {
        return editorMode;
    }

    public void setEditorMode(boolean editorMode) {
        this.editorMode = editorMode;
    }

    public ArrayList<Integer> getGridList() {
        return gridList;
    }

    public Editor(PApplet pApplet,ArrayList<Placeable> placeableList,SpielWelt welt,SpielStarten spielStarten, Charakter held, SaveLevel saveLevel){
        this.pApplet = pApplet;
        this.placeableList = placeableList;
       // placeableList.add(previewObject);
        this.welt = welt;
        this.spielStarten = spielStarten;
        this.held = held;
        this.saveLevel = saveLevel;
    }
    public Editor(){

    }
    public void setGridList(ArrayList<Integer> gridList) {
        this.gridList = gridList;
    }


    //  Spike spike = new Spike(700, 300, 500, 200, 300, 400);

    public void createObjectFromJson(JSONArray jsonArray){
        //system.out.println("test3");
        int tempObjectIndex = objectIndex;
        //system.out.println("size " + jsonArray.size());
        //system.out.println(jsonArray.getJSONObject(0).getFloat("posiX " + 0) + "das ist der float");
        for(int i = 0; i < jsonArray.size(); i++) {

            if (jsonArray.getJSONObject(i).getString("bezeichner").equals("Floor") ) {
                objectIndex = 1;
            }
            if (jsonArray.getJSONObject(i).getString("bezeichner").equals("Spike") ) {
                objectIndex = 2;
            }

            if (jsonArray.getJSONObject(i).getString("bezeichner").equals("ShortFloor") ) {
                objectIndex = 3;
            }
                //system.out.println("kommt an endloss");
                this.createObjects(jsonArray.getJSONObject(i).getFloat("posiX"),jsonArray.getJSONObject(i).getFloat("posiY"));

            }

        }


    public void createObjects(float x, float y) {
        //PApplet p;

        switch (objectIndex) {

            case 0:
                setRespawn(true);
                if(restartCounter == 1){
                    System.out.println(removePlacableList+ "das ist rst");
                    removePlacableList.add(restart);
                    placeableList.remove(restart);

                    restartCounter -= 1;

                    /* todo
                    * aktuell wird nach einem restart nicht gecleart erst wenn ein anderes objekt removed wird
                    * muss geändert werden
                    *
                    *
                    * */
                }

                if(restartCounter == 0){

                    restart = new Restart(x,y);
                    saveLevel.addJsonObject(restart);
                    placeableList.add(restart);

                restartCounter += 1;
                }

            break;

            case 1:
                PImage floorImage = pApplet.loadImage("resources/floor.png");
               // floorList.add(new Floor(new PImage(),x,y));
                Floor floor = new Floor (floorImage,x,y);
                saveLevel.addJsonObject(floor);
                placeableList.add(floor);
                //system.out.println("CASE 0 FLOOR CREATE OBJECT " + objectIndex);


                break;
            case 2:
                //y = y -30;
          //  spikeListe.add(new Spike(x, y, x - 20, y + 30, x + 20, y + 30))

                //system.out.println("kommt an vor erstellen");
                Placeable spike = new Spike(x,y);
                placeableList.add(spike);
                //system.out.println("kommt an erstellen" + saveLevel);
                saveLevel.addJsonObject(spike);
                //system.out.println("kommt an nach erstellen");

          //  placeableList.add(new Spike(x,y));
            //system.out.println("CASE 1 SPIKE CREATE OBJECT " + objectIndex);
            break;

            case 3:
               PImage shortImage = pApplet.loadImage("resources/floor.png");
                shortImage.resize(50,20);
                ShortFloor shortfloor = new ShortFloor(shortImage,x,y);
                saveLevel.addJsonObject(shortfloor);
                placeableList.add(shortfloor);
                //system.out.println("CASE 1 SPIKE CREATE OBJECT " + objectIndex);


                break;
        }
        saveLevel.saveJsonLevel();



    }
    public void changeIndex(char key){
       // if(objectIndex >= 0 && objectIndex < 10) {

            if (key == 'e' && objectIndex <= 8) {
                objectIndex++;
                //system.out.println("das ist objektindex" + objectIndex);
            } if(key == 'q' && objectIndex >=1){
                objectIndex--;
                //system.out.println(objectIndex);
            }
        previewObjectshow(200,300);
        }
  //  }

    public void deleteSpike(float x ,float y) {



         //   removePlacableList = new ArrayList<>();


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

                //system.out.println("wird ausgeführt \n Das ist w1 " + w1 + " das ist W2 " + w2 + " \n Das ist w1+w2 " + (w1 + w2));
                if (w1 >= 0 && w2 >= 0 && (w1 + w2) <= 1) {
                    removePlacableList.add(spike);

                    //system.out.println("wird ausgeführt(remove)");
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
         //          //system.out.println(removePlacableList.size());
         //      }
        //    }


     //   for(Floor floor : floorList){
            for(Placeable floor : placeableList){
                if(floor instanceof Floor) {
                    //system.out.println("kommt an");
                    if (floor.getPositionX() < x && x < (floor.getPositionX() + floor.getImage().width) && floor.getPositionY() < y && y < (floor.getPositionY() + floor.getImage().height)) {
                        removePlacableList.add(floor);
                        //system.out.println("test1");
                    }
                }

        }
     //   for(Floor f : removeFloorList){
      //      floorList.remove(f);
       // }

        for (Placeable s : removePlacableList) {
            if(s != previewObject) {
                saveLevel.removeFromJson(s);
                placeableList.remove(s);

            }
        }


    }

    public void createGrid(int scale){
        gridList = new ArrayList<>();
        for(int i = 0; i < pApplet.height; i += scale){
            gridList.add(i);
        }
        //system.out.println(gridList.size());

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
            System.out.println(previewObject);
        switch(objectIndex) {

            case 0:
                System.out.println(previewObject);
                placeableList.remove(previewObject);
                previewObject = new Restart(800,800);
                placeableList.add(previewObject);

                break;


            case 1:
                placeableList.remove(previewObject);
                PImage floorImage = pApplet.loadImage("resources/floor.png");
                previewObject = new Floor(floorImage,800,800);
                //system.out.println(placeableList.size() + "Size");
                placeableList.add(previewObject);
                //system.out.println(placeableList.size() + "Size");
                //system.out.println("CASE 0 FLOOR PREVIEW OBJECT "+ objectIndex);


                break;

            case 2:
                placeableList.remove(previewObject);
                previewObject = new Spike();
                placeableList.add(previewObject);
                //system.out.println("CASE 1 SPIKE PREVIEW OBJECT " + objectIndex);

                break;

            case 3:
                placeableList.remove(previewObject);
                PImage shortImage = pApplet.loadImage("resources/floor.png");
                shortImage.resize(50,20);
                previewObject = new ShortFloor(shortImage,800,800);
                placeableList.add(previewObject);






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
    public ArrayList<Float> respawn(float x, float y){
        ArrayList<Float> respawnList = new ArrayList<>();
        respawnList.add(x);
        respawnList.add(y);
    return respawnList;

    }
    }

