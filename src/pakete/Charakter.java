package pakete;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class Charakter {

    private PImage img;
    private float positionX;
    private float positionY;
    private ArrayList<PVector> pixelList;
    int t;


    public PImage getImg() {
        return img;
    }

    public void setImg(PImage img) {
        this.img = img;
    }

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

    public Charakter(PImage img, float positionX, float positionY) {
    	
    	
        this.img = img;
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public Charakter(){

    }
    public void sterben(Charakter held){
        held = null;
    }
    
    public void getNonTransparentPixel() {
    	
    	pixelList = new ArrayList<PVector>();
    	for(int i = 0; i < img.height; i++) {
    		
    		for(int c = 0; c < img.width; c++) {
    			
    			
    			
    			t = img.get(89, 89);
    			int alpha = ( t >> 24) & 255;
    			//16777215
    				
    			PVector p = new PVector(c,i);
        		pixelList.add(p);
        		System.out.println("das ist C " + c + "\n" + "das ist i" + i);
    		}
    	}
    	
    	}
    	System.out.println("das ist t " + t);
    }
}

