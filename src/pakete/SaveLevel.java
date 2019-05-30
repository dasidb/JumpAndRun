package pakete;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class SaveLevel {
    private  PApplet p;
    private JSONArray jsonArray = new JSONArray();
    private JSONObject jsonObject;
    private String bezeichner;
    private String posiX;
   private  String posiY;
    static int counter = 0;
    private Editor editor;
    private boolean loaded;


    public SaveLevel(PApplet p, Editor editor) {
        this.p = p;
        this.editor = editor;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public void saveJsonLevel(){

        p.saveJSONArray(jsonArray, "C:\\Users\\lukas.kapust\\Desktop\\java test\\possession\\testen\\LevelFolder\\level.json");
        System.out.println("gespeichert");
    }

    public void loadJsonLevel(){
        if(!loaded) {
            jsonArray = p.loadJSONArray("C:\\Users\\lukas.kapust\\Desktop\\java test\\possession\\testen\\LevelFolder\\level.json");

            System.out.println("test");
            editor.createObjectFromJson(jsonArray);
            System.out.println("test2");
            loaded = true;
        }



    }

    public void addJsonObject(Placeable placeable){
        System.out.println("kommt an methode" );
        if(placeable instanceof Spike) {
            bezeichner = "Spike " + counter;
        }
        if(placeable instanceof Floor){
            bezeichner = "Floor " + counter;
        }


            posiX = "posiX " +counter;
            posiY = "posiY " + counter;
            jsonObject = new JSONObject();
            jsonObject.setString("bezeichner",bezeichner);
            jsonObject.setFloat(posiX,placeable.getPositionX());
            jsonObject.setFloat(posiY,placeable.getPositionY());
            jsonArray.setJSONObject(counter,jsonObject);
            counter ++;



    }
    public void test(){
        System.out.println("test kommt an");
    }



}
