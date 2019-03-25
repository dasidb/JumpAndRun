package pakete;

//import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

public class SpielWelt {
    private float gravity = 1f;
    private PImage welt;

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

    public void erschaffeWelt(PApplet p){
        PImage welt = p.loadImage("resources/hintergrund.png");
        p.image(welt,0,0);
    }

}
