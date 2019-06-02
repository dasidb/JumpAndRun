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
        int testi= 0;
        System.out.println(jsonArray.size() + "json array Size save ");


        JSONArray tempJArray = new JSONArray();
        for(int i = 0 ; i < jsonArray.size() ; i++){
            if(!jsonArray.isNull(i)){


                tempJArray.setJSONObject(testi, jsonArray.getJSONObject(i));
               // System.out.println(tempJArray.size()+ "tempArraySize");
                testi ++;
            }


        }
       // jsonArray = tempJArray;


        p.saveJSONArray(jsonArray, "C:\\Users\\lukas.kapust\\Desktop\\java test\\possession\\testen\\LevelFolder\\level.json");
        //system.out.println("gespeichert");
    }

    public void removeFromJson(Placeable placeable){
        System.out.println("json array size  remove" + jsonArray.size());

        for(int i = 0 ; i < jsonArray.size(); i++){

            if(!jsonArray.isNull(i)){
           if( jsonArray.getJSONObject(i).getFloat("posiX")  == placeable.getPositionX() && jsonArray.getJSONObject(i).getFloat("posiY") == placeable.getPositionY()) {
               System.out.println("kommt an");
               jsonArray.remove(i);
               System.out.println("kommt an 2");
               // break;
           }
           }
        }

    }

    public void loadJsonLevel(){
        if(!loaded) {
            jsonArray = p.loadJSONArray("C:\\Users\\lukas.kapust\\Desktop\\java test\\possession\\testen\\LevelFolder\\level.json");


            editor.createObjectFromJson(jsonArray);

            loaded = true;
        }



    }

    public void addJsonObject(Placeable placeable){
        int i = jsonArray.size();
        if(placeable instanceof Spike) {
            bezeichner = "Spike";
        }
        if(placeable instanceof Floor){
            bezeichner = "Floor";
        }
        if(placeable instanceof ShortFloor){
            bezeichner = "ShortFloor";
        }
        if(placeable instanceof Restart){
            bezeichner = "Restart";
        }


            posiX = "posiX";
            posiY = "posiY";
            jsonObject = new JSONObject();
            jsonObject.setString("bezeichner",bezeichner);
            jsonObject.setFloat(posiX,placeable.getPositionX());
            jsonObject.setFloat(posiY,placeable.getPositionY());
            jsonArray.setJSONObject(i,jsonObject);
            counter ++;



    }
    public void test(){
        //system.out.println("test kommt an");
    }



}
