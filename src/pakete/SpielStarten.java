package pakete;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Vector;

public class SpielStarten extends PApplet {
	private Charakter held;
	private Hindernis hindernis;
	private Spike spike;
	private SpielWelt welt;
	private PVector veca;
	private PVector vecb;
	private PVector vecc;
	private PVector vecp;
	private boolean isColiding;
	private ArrayList<Spike> spikeListe;
	private static final float VELOCITY = 5;
	private boolean aKey;
	private boolean dKey;
	private boolean sKey;
	private boolean wKey;
	private float gravity = 8F;

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public ArrayList<Spike> getSpikeListe() {
		return spikeListe;
	}

	public void setSpikeListe(ArrayList<Spike> spikeListe) {
		this.spikeListe = spikeListe;
	}

	public static void main(String[] args) {
		System.out.println("hier ist die main klasse");
		PApplet.main(SpielStarten.class, args);
		System.out.println("hier ist die main klasse zuende");

	}

	public SpielStarten() {
		System.out.println("hier ist der construktor");
	}

	public void ersschaffeObjekte() {
		erschaffeHeld();
		// erschaffeHindernisse();
		erschaffeSpike();
		erschaffeWelt();

	}

	public void erschaffeHeld() {
		System.out.println("hier ist erschaffe held");
		PImage heldImage = loadImage("resources/90.png");
		held = new Charakter(heldImage, 1, 1);
	}

	public void respawn(PImage p) {
		held.setImg(p);
	}

	public void sterben(PImage p) {
		p = loadImage("resources/spike.png");
		held.setImg(p);
	}

	public void erschaffeWelt() {
		welt = new SpielWelt();
	}

	public void spielStarten() {
		System.out.println("Spielstarten erreicht");

		held.setPositionX(100);
	}

	public void setup() {
		super.setup();
		System.out.println("hier ist setup");
		ersschaffeObjekte();
		spike.erschaffeSpike(this, spikeListe);

		System.out.println(held);
		held.getNonTransparentPixel();
		background(0);
		frameRate(30);
		loop();

	}

	public void settings() {
		super.settings();

		size(800, 800);
		System.out.println("hier wird settings aufgerufen");

	}

	public void draw() {
		background(0);
		image(held.getImg(), held.getPositionX(), held.getPositionY());
		//gravity();
		move();
		gravitation();
		held.springen(this);

		for (Spike s : spikeListe) {
			triangle(s.getTriangleX1(), s.getTriangleY1(), s.getTriangleX2(), s.getTriangleY2(), s.getTriangleX3(),
					s.getTriangleY3());
		}
		for (Spike c : getSpikeListe()) {
			int kollisionDistance = 90;

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

				if (kollision(c) == true) {
				}
			}

		}

		if (isColiding == true) {

			held.setPositionX(1);
			held.setPositionY(1);
			isColiding = false;

		}

	}



	public boolean kollision(Spike spike) {

		veca = new PVector(spike.getTriangleX1(), spike.getTriangleY1());

		vecb = new PVector(spike.getTriangleX2(), spike.getTriangleY2());

		vecc = new PVector(spike.getTriangleX3(), spike.getTriangleY3());

		vecp = new PVector(held.getPositionX(), held.getPositionY());

		for (PVector p : held.getPixelList()) {
			float w1 = (veca.x * (vecc.y - veca.y) + ((vecp.y + p.y) - veca.y) * (vecc.x - veca.x)
					- (vecp.x + p.x) * (vecc.y - veca.y))
					/ ((vecb.y - veca.y) * (vecc.x - veca.x) - (vecb.x - veca.x) * (vecc.y - veca.y));

			float w2 = ((vecp.y + p.y) - veca.y - w1 * (vecb.y - veca.y)) / (vecc.y - veca.y);


			if (w1 >= 0 && w2 >= 0 && (w1 + w2) <= 1) {
				isColiding = true;
			} else {

			}
		}

		return isColiding;

	}

	public void gravitation(){
		if(held.getTest() == 1 && held.isJumping() == false && held.isMaxJump() == false){
			gravity = 0;
			held.setTest(0);
		}
		if(gravity !=8){
			gravity += 0.5F;
			if(gravity >8){
				gravity = 8;
			}
		}
		if(held.getPositionY() <300) {
			// System.out.println(getHeldY());
			held.setPositionY(held.getPositionY() + gravity);


		}
		if(held.getPositionY() > 300){
			held.setJumpCount(2);
		}

	}
		public void erschaffeSpike () {
			spike = new Spike();
			spikeListe = new ArrayList<>();
		}


	public void move() {
		if (aKey == true) {
			held.setPositionX(held.getPositionX() - VELOCITY);
		}
		if (dKey == true) {
			held.setPositionX(held.getPositionX() + VELOCITY);
		}
		if (wKey == true) {
			held.setPositionY(held.getPositionY() - VELOCITY);
		}
		if (sKey == true) {
			held.setPositionY(held.getPositionY() + VELOCITY);
		}

	}
	@Override
	public void keyPressed(){
		if(keyPressed){
			if(key == 'a'){
				aKey = true;
			}
			if(key == 'd'){
				dKey = true;
			}
			if(key == 'w'){
				held.setJumping(true);
			}

			if(key == 's'){
				sKey = true;
			}
		}
	}
@Override
	public void keyReleased(){
	if(key == 'a'){
		aKey = false;
	}
	if(key == 'd'){
		dKey = false;
	}
	if(key == 'w') {
		held.setJumping(false);
		held.setJumpTime(1/30F);
		held.setMaxJump(false);
		held.setJumpCount(held.getJumpCount() -1);
	}
	if(key == 's'){
		sKey = false;
	}
	}
	}


