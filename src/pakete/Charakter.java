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
    private int t;
    private int jumpCount = 2;
    private int jumpHeight = -50;
    
    
    
    
    
    public int getJumpCount() {
		return jumpCount;
	}

	public void setJumpCount(int jumpCount) {
		this.jumpCount = jumpCount;
	}

	public int getJumpHeight() {
		return jumpHeight;
	}

	public void setJumpHeight(int jumpHeight) {
		this.jumpHeight = jumpHeight;
	}

	public ArrayList<PVector> getPixelList() {
		return pixelList;
	}

	public void setPixelList(ArrayList<PVector> pixelList) {
		this.pixelList = pixelList;
	}


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
    
    public void springen() {
    	if(jumpCount >0) {
    		setPositionY(getPositionY() + jumpHeight);
    		jumpCount -= 1;
    	}
    	
    	
    }
    
    public void getNonTransparentPixel() {
    	
    	pixelList = new ArrayList<PVector>();
    	for(int i = 0; i < img.height; i++) {
    		
    		for(int c = 0; c < img.width; c++) {
    			
    			
    			
    			t = img.get(c, i);
    			//System.out.println(Integer.toBinaryString(t));
    			int alpha = (t >>> 24); 
    			//System.out.println(alpha);
    			//int alpha = ( t >> 24) & 255;
    			//                        11111111
    			//System.out.println(alpha);
    			//16777215
    			if(alpha > 100) {	
    			PVector p = new PVector(c,i);
        		pixelList.add(p);
        		
    			}
    			
        		//System.out.println("das ist C " + c + "\n" + "das ist i" + i);
    			
    				
    			}
    		}
    		
    	} //11111111101001010100111101001111
    	  //11111111001100111000000000100111
    	
    	}
    	
    


