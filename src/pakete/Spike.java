package pakete;

import processing.core.PApplet;

import java.util.ArrayList;
// ggf extends hinderniss
public class Spike extends Placeable{

    private float triangleX2;
    private float triangleY2;
    private float triangleX3;
    private float triangleY3;
 //   private ArrayList<Spike> spikeListe;

    // Getter und Setter


   

  

    public float getTriangleX2() {
        return triangleX2;
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
    	super(valueX1,valueY1);

    	this.triangleX2 = valueX2;
    	this.triangleY2 = valueY2;
    	this.triangleX3 = valueX3;
    	this.triangleY3 = valueY3;
    }

    public Spike(){

    }
    public Spike(float valueX1, float valueY1) {
        this(valueX1,valueY1,valueX1 -20,valueY1 + 30,valueX1 + 20, valueY1 +30);
    }
    

	public void erschaffeSpike(PApplet p, ArrayList<Spike> spikeListe){
      //  spikeListe = new ArrayList<>();
        int x = 300;
        int y = 300;
        int z = 250;
        for(int i = 0; i <0; i++){
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
