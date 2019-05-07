package pakete;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

// create floor boolean colliding with jump method
public class Floor implements Placeable{
    private PImage image;
    private float posiX;
    private float posiY;
    private int t;
    private ArrayList<PVector> pixelList = new ArrayList<>();
    boolean cooliding;
    private ArrayList<PVector> valueList = new ArrayList<>();
    private int countValue = 0;

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

    public float getPosiX() {
        return posiX;
    }

    public void setPosiX(float posiX) {
        this.posiX = posiX;
    }

    public float getPosiY() {
        return posiY;
    }

    public void setPosiY(float posiY) {
        this.posiY = posiY;
    }

    public Floor(PImage image, float posiX, float posiY) {

        this.image = image;
        this.posiX = posiX;
        this.posiY = posiY;
        getNonTransparentPixel();
    }

    public Floor(float posiX, float posiY) {

        this.posiX = posiX;
        this.posiY = posiY;

    }
    public void getNonTransparentPixel () {

            System.out.println(getPosiX());
        System.out.println(getPosiY());
        System.out.println(pixelList.size());
        for (int i = 0; i < 1; i++) {

            for (int c = 0; c < image.width; c++) {


                t = image.get(c, i);

                int alpha = (t >>> 24);

                //int alpha = ( t >> 24) & 255;
                //                        11111111
                //System.out.println(alpha);
                //16777215
                if (alpha > 100) {
                    PVector p = new PVector((c+getPosiX()), (i+getPosiY()));

                    pixelList.add(p);

                System.out.println(pixelList.size());
                }

                //System.out.println("das ist C " + c + "\n" + "das ist i" + i);


            }
        }
    for (PVector f : pixelList) {
        valueList.add(countValue,f);
      // System.out.println(valueList.size());
        countValue ++;
    }
    }
    }





