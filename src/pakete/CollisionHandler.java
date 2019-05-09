package pakete;

import processing.core.PVector;

import java.util.ArrayList;

public class CollisionHandler {
    private Charakter held;
    private Floor floor;
    private Spike spike;
    private ArrayList<Spike> spikeListe;
    private ArrayList<Floor> floorList;
 //   private boolean spikeCollisionCheck;
    private boolean coliding;

    public boolean isColiding() {
        return coliding;
    }

    public void setColiding(boolean coliding) {
        this.coliding = coliding;
    }

    public ArrayList<Spike> getSpikeListe() {
        return spikeListe;
    }

    public void setSpikeListe(ArrayList<Spike> spikeList) {
        this.spikeListe= spikeList;
    }

    public ArrayList<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(ArrayList<Floor> floorList) {
        this.floorList = floorList;
    }

    public CollisionHandler(Charakter held, ArrayList<Spike> spikeListe, ArrayList<Floor> floorList){
    this.held = held;
    this.spikeListe = spikeListe;
    this.floorList = floorList;
    }



    public void spikeCollision(){
        for (Spike c : getSpikeListe()) {
            int kollisionDistance = 65;

            if (held.getPositionX() - c.getTriangleX1() < kollisionDistance
                    && (held.getPositionX() - c.getTriangleX1() > -kollisionDistance
                    && held.getPositionY() - c.getTriangleY1() < kollisionDistance
                    && held.getPositionY() - c.getTriangleY1() > -kollisionDistance)
                    || (held.getPositionX() - c.getTriangleX2() < kollisionDistance
                    && held.getPositionX() - c.getTriangleX2() > -kollisionDistance
                    && held.getPositionY() - c.getTriangleY2() < kollisionDistance
                    && held.getPositionY() - c.getTriangleY2() > -kollisionDistance)
                    || (held.getPositionX() - c.getTriangleX3() < kollisionDistance
                    && held.getPositionX() - c.getTriangleX3() > -kollisionDistance
                    && held.getPositionY() - c.getTriangleY3() < kollisionDistance
                    && held.getPositionY() - c.getTriangleY3() > -kollisionDistance)) {
                    System.out.println("test");
                if (spikeCollisionCheck(c) == true) {
                }
            }

        }
    }

    public boolean spikeCollisionCheck(Spike spike) {

        PVector veca = new PVector(spike.getTriangleX1(), spike.getTriangleY1());

        PVector vecb = new PVector(spike.getTriangleX2(), spike.getTriangleY2());

        PVector vecc = new PVector(spike.getTriangleX3(), spike.getTriangleY3());

        PVector vecp = new PVector(held.getPositionX(), held.getPositionY());

        for (PVector p : held.getPixelList()) {
            float w1 = (veca.x * (vecc.y - veca.y) + ((vecp.y + p.y) - veca.y) * (vecc.x - veca.x)
                    - (vecp.x + p.x) * (vecc.y - veca.y))
                    / ((vecb.y - veca.y) * (vecc.x - veca.x) - (vecb.x - veca.x) * (vecc.y - veca.y));

            float w2 = ((vecp.y + p.y) - veca.y - w1 * (vecb.y - veca.y)) / (vecc.y - veca.y);


            if (w1 >= 0 && w2 >= 0 && (w1 + w2) <= 1) {
                coliding = true;
            } else {

            }
        }

        return coliding;

    }

    public void stopGrav(){
        if (isColiding() == true) {

            held.setPositionX(1);
            held.setPositionY(1);
            setColiding(false);

        }

    }
    }

