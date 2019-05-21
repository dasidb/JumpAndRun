package pakete;

import processing.core.PVector;

import java.util.ArrayList;

public class CollisionHandler {
    private Charakter held;
    private Floor floor;
    private Spike spike;
    private SpielWelt welt;
    private ArrayList<Spike> spikeListe;
    private ArrayList<Floor> floorList;
    private ArrayList<Placeable> placeableList;
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
        this.spikeListe = spikeList;
    }

    public ArrayList<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(ArrayList<Floor> floorList) {
        this.floorList = floorList;
    }

    public CollisionHandler(Charakter held, ArrayList<Spike> spikeListe, ArrayList<Floor> floorList, SpielWelt welt, ArrayList<Placeable> placeableList) {
        this.held = held;
        this.spikeListe = spikeListe;
        this.floorList = floorList;
        this.welt = welt;
        this.placeableList= placeableList;
    }


    public void spikeCollision() {
        for (Placeable c : placeableList) {
            if (c instanceof Spike) {

                int kollisionDistance = 65;

                if (held.getPositionX() - c.getPositionX() < kollisionDistance
                        && (held.getPositionX() - c.getPositionX() > -kollisionDistance
                        && held.getPositionY() - c.getPositionY() < kollisionDistance
                        && held.getPositionY() - c.getPositionY() > -kollisionDistance)
                        || (held.getPositionX() - ((Spike) c).getTriangleX2() < kollisionDistance
                        && held.getPositionX() - ((Spike) c).getTriangleX2() > -kollisionDistance
                        && held.getPositionY() - ((Spike) c).getTriangleY2() < kollisionDistance
                        && held.getPositionY() - ((Spike) c).getTriangleY2() > -kollisionDistance)
                        || (held.getPositionX() - ((Spike) c).getTriangleX3() < kollisionDistance
                        && held.getPositionX() - ((Spike) c).getTriangleX3() > -kollisionDistance
                        && held.getPositionY() - ((Spike) c).getTriangleY3() < kollisionDistance
                        && held.getPositionY() - ((Spike) c).getTriangleY3() > -kollisionDistance)) {
                    System.out.println("test");
                    if (spikeCollisionCheck(((Spike) c)) == true) {
                    }
                }

            }
        }
    }

    public boolean spikeCollisionCheck(Spike spike) {

        PVector veca = new PVector(spike.getPositionX(), spike.getPositionY());

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

    public void stopGrav() {
        if (isColiding() == true) {
            held.sterben(placeableList);
            setColiding(false);

        }

    }

    public void kollisionFloor() {
        PVector vecp = new PVector((int) held.getPositionX(), (int) held.getPositionY());

        // wenn w key gedrückt boolean setzen so das es nicht durchfällt!
        for (PVector p : held.getPixelListBottom()) {


            for (Floor floortest : floorList) {

                if (floortest.getPositionY() - held.getPositionY() < 50 && floortest.getPositionY() - held.getPositionY() > 10 && floortest.getPositionX() - held.getPositionX() < 20 && (floortest.getPositionX() + floortest.getImage().width) - held.getPositionX() > -20) {
                    if (((p.x + held.getPositionX()) >= floortest.getPositionX() && (p.x + held.getPositionX()) <= (floortest.getPositionX() + floortest.getImage().width)) && ((p.y + held.getPositionY()) >= floortest.getPositionY()) && (p.y + held.getPositionY()) <= (floortest.getPositionY() + 10)) {

                        held.setCooliding(true);
                        held.setJumpCount(2);
                        welt.setGravity(1.5F);
                        held.setPositionY(floortest.getPositionY() - held.getImg().height);

                        //warum köst es das problem?
                        //if(held.isJumping()){
                        //	held.setJumping(true);
                        //}


                    } //else if (((p.x + held.getPositionX()) < floortest.getPositionX() || ((p.x + held.getPositionX()) > (floortest.getPositionX() + floortest.getImage().width)))) {
                    //else if (( floortest.getPositionX() - (p.x + held.getPositionX()) < 10 || ((p.x + held.getPositionX()) > (floortest.getPositionX() + floortest.getImage().width)))) {
                    else {
                        held.setCooliding(false);


                    }

                }
                if ((p.x + held.getPositionX()) > floortest.getPositionX() && (p.x + held.getPositionX()) < (floortest.getPositionX() + 20) && (p.y + held.getPositionY()) > floortest.getPositionY() && (p.y + held.getPositionY()) < (floortest.getImage().height + floortest.getPositionY())) {
                    System.out.println("berührt");
                    held.setMoveRight(false);
                    held.setPositionX(floortest.getPositionX() - 20);
                }
                if ((p.x + held.getPositionX()) < (floortest.getPositionX() + floortest.getImage().width) && (p.x + held.getPositionX()) > ((floortest.getPositionX() + floortest.getImage().width) - 20) && (p.y + held.getPositionY()) > floortest.getPositionY() && (p.y + held.getPositionY()) < (floortest.getImage().height + floortest.getPositionY())) {

                    System.out.println("berührt");
                    held.setMoveLeft(false);
                    held.setPositionX(floortest.getPositionX() + floortest.getImage().width);
                }


            }
        }
        for (PVector p : held.getPixelListTop()) {


            for (Floor floortest : floorList) {
                if ((p.x + held.getPositionX()) > floortest.getPositionX() && (p.x + held.getPositionX()) < ((floortest.getPositionX() + floortest.getImage().width)) && (p.y + held.getPositionY()) < (floortest.getPositionY() + floortest.getImage().height) && (p.y + held.getPositionY()) > (floortest.getImage().height + floortest.getPositionY() - 20)) {
                    held.setPositionY(floortest.getImage().height + floortest.getPositionY());
                    held.setJumping(false);
                  //  held.setTest(1);
                }
            }
        }
    }
}

