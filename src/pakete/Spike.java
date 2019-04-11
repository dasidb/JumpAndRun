package pakete;

import processing.core.PApplet;

import java.util.ArrayList;

public class Spike extends Hindernis{

    private float triangleX1;
    private float triangleY1;
    private float triangleX2;
    private float triangleY2;
    private float triangleX3;
    private float triangleY3;
 //   private ArrayList<Spike> spikeListe;

    // Getter und Setter


   

  

    public float getTriangleX2() {
        return triangleX2;
    }

    public float getTriangleX1() {
		return triangleX1;
	}

	public void setTriangleX1(float valueX1) {
		this.triangleX1 = valueX1;
	}

	public float getTriangleY1() {
		return triangleY1;
	}

	public void setTriangleY1(float valueY1) {
		this.triangleY1 = valueY1;
	}

	public void setTriangleX2(float groeßeX2) {
        this.triangleX2 = groeßeX2;
    }

    public float getTriangleY2() {
        return triangleY2;
    }

    public void setTriangleY2(float positionY2) {
        this.triangleY2 = positionY2;
    }

    public float getTriangleX3() {
        return triangleX3;
    }

    public void setTriangleX3(float getPositionX3) {
        this.triangleX3 = getPositionX3;
    }

    public float getTriangleY3() {
        return triangleY3;
    }

    public void setTriangleY3(float getPositionY3) {
        this.triangleY3 = getPositionY3;
    }
// Constructoren


    public Spike(float valueX1, float valueY1, float valueX2, float valueY2, float valueX3, float valueY3) {
    	super();
    	this.triangleX1 = valueX1;
    	this.triangleY1 = valueY1;
    	this.triangleX2 = valueX2;
    	this.triangleY2 = valueY2;
    	this.triangleX3 = valueX3;
    	this.triangleY3 = valueY3;
    }
    
    
    

    public Spike(){

    }

    

	public void erschaffeSpike(PApplet p, ArrayList<Spike> spikeListe){
      //  spikeListe = new ArrayList<>();
        int x = 300;
        int y = 300;
        int z = 250;
        for(int i = 0; i <5; i++){
            // arg 1 mitte arg 2 links arg 3 rechts
            Spike spike1 = new Spike(x,y,x-20,y+30,x+20,y+30);
            if(i == 3){
                spike1 = new Spike(x-100,y-40,x-120,y-70,x-80,y-70);
            }
            //Spike spike1 = new Spike(50+x+z,40+z,40+x+z,20+z,60+x+z,20+z);
            spikeListe.add(spike1);
           // System.out.println("erschaffe spike");
            //p.triangle(spike1.getGroeßeX1(), spike1.getGroeßeY1(), spike1.getGroeßeX2(), spike1.getPositionY2(), spike1.getGetPositionX3(), spike1.getPositionY3);
            x += 40;
        }
       

    }

}
