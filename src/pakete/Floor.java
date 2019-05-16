package pakete;

import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

// create floor boolean colliding with jump method
public class Floor extends Placeable{
  //  private PImage image;
  //  private float positionX;
  //  private float positionY;
   // private int t;
  //  private ArrayList<PVector> pixelList = new ArrayList<>();
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





    public Floor(PImage image, float posiX, float posiY) {
        super(image,posiX,posiY);
      //  this.image = image;
        //this.positionX = posiX;
        //this.positionY = posiY;
        getNonTransparentPixel();

    }
    public Floor(){

    }

    public Floor(float posiX, float posiY) {
        super(posiX,posiY);
       // this.positionX = posiX;
       // this.positionY = posiY;

    }
@Override
    public void getNonTransparentPixel () {

        super.getNonTransparentPixel();
/*
            System.out.println(getPositionX());
        System.out.println(getPositionY());
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
                    PVector p = new PVector((c+ getPositionX()), (i+ getPositionY()));

                    pixelList.add(p);

                System.out.println(pixelList.size());
                }

                //System.out.println("das ist C " + c + "\n" + "das ist i" + i);


            }
        } */

        // unsicher ob nötig
  //  for (PVector f : pixelList) {
 //       valueList.add(countValue,f);
 //      System.out.println("gucken");
  //      countValue ++;
   // }
    }
    }





