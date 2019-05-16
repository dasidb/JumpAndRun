package pakete;

import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public abstract class Placeable {
    private float positionX;
    private float positionY;
    public ArrayList<PVector> pixelList = new ArrayList<>();
    public int alphaValue;
    public PImage image;
   // private ArrayList<Placeable> placebleList = new ArrayList<>();



    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public ArrayList<PVector> getPixelList() {
        return pixelList;
    }

    public void setPixelList(ArrayList<PVector> pixelList) {
        this.pixelList = pixelList;
    }

    public PImage getImage() {
        return image;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    public Placeable(){

    }

    public Placeable(float positionX, float positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public Placeable(PImage image, float positionX, float positionY){
        this.image = image;
        this.positionX = positionX;
        this.positionY = positionY;
    }


    public void getNonTransparentPixel() {


        for (int i = 0; i < 1; i++) {

            for (int c = 0; c < image.width; c++) {


                alphaValue = image.get(c, i);

                int alpha = (alphaValue >>> 24);

                //int alpha = ( t >> 24) & 255;
                //                        11111111
                //System.out.println(alpha);
                //16777215
                if (alpha > 100) {
                    PVector p = new PVector((c + getPositionX()), (i + getPositionY()));

                    pixelList.add(p);

                //    System.out.println(pixelList.size());
                }


                //System.out.println("das ist C " + c + "\n" + "das ist i" + i);


            }
        }
    }
}


