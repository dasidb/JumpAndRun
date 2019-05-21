package pakete;

//import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

public class SpielWelt {
    private float gravity = 8f;
    private PImage welt;
    private Charakter held;
    private PApplet p;
    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public PImage getWelt() {
        return welt;
    }

    public void setWelt(PImage welt) {
        this.welt = welt;
    }


    public SpielWelt(Charakter held, PApplet p){
        this.held = held;
        this.p = p;
    }





    public void erschaffeWelt(PApplet p){
        PImage welt = p.loadImage("resources/hintergrund.png");
        p.image(welt,0,0);

    }

    public void gravitation() {
        if (held.getTest() == 1 && held.isJumping() == false && held.isMaxJump() == false) {
            gravity = 0;
            held.setTest(0);
        }
        if (gravity != 8) {
            gravity += 0.5F;
            if (gravity > 8) {
                gravity = 8;
            }
        }
        if (held.getPositionY() < 300) {
            // System.out.println(getHeldY());
            held.setPositionY(held.getPositionY() + gravity);


        }
        if (held.getPositionY() > 300) {
            held.setJumpCount(2);
        }

    }


}
